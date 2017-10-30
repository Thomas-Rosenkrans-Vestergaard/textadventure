package textadventure;

import textadventure.actions.Action;
import textadventure.combat.Faction;
import textadventure.combat.Factions;
import textadventure.doors.*;
import textadventure.items.chest.*;
import textadventure.items.weapons.Shotgun;
import textadventure.lock.*;
import textadventure.rooms.*;
import textadventure.ui.GameInterface;
import textadventure.ui.characterSelection.*;

import java.util.*;

import static textadventure.doors.Door.State.CLOSED;
import static textadventure.doors.Door.State.OPEN;
import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class Game
{

	/**
	 * The {@link GameInterface} used for input-output.
	 */
	private GameInterface gameInterface;

	/**
	 * The minimum amount of {@link Character}s controlled by a {@link Player} in the {@link Game}.
	 */
	private final int numberOfCharacters;

	private StartingRoom startingRoom;
	private EndingRoom   endingRoom;

	/**
	 * The {@link Player}s in the {@link Game}.
	 */
	private List<Player> players = new ArrayList<>();

	/**
	 * The {@link Player}s in the {@link Game} mapped to the {@link Faction} they control.
	 */
	private Map<Faction, Player> factions = new HashMap<>();

	/**
	 * The {@link Character}s in {@link Game} mapped to the {@link Player} who controls them.
	 */
	private Map<Player, List<Character>> characters;

	/**
	 * Returns the {@link Set} of the names in the {@link Game}.
	 */
	private Set<String> characterNames = new HashSet<>();

	/**
	 * The {@link GameInterface} to use for input-output.
	 *
	 * @param gameInterface      The {@link GameInterface} to use for input-output.
	 * @param numberOfCharacters The number of {@link Character}s controlled by a {@link Player}.
	 */
	public Game(GameInterface gameInterface, int numberOfCharacters)
	{
		if (numberOfCharacters < 1)
			throw new IllegalArgumentException("numberOfCharacters must be positive.");

		this.gameInterface = gameInterface;
		this.numberOfCharacters = numberOfCharacters;
		this.characters = new HashMap<>();
		gameInterface.onInit(this);
		generateState();
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param player  The {@link Player} to put to the {@link Game}.
	 * @param faction The {@link Faction} the {@link Player} plays for.
	 */
	public void addPlayer(Player player, Faction faction) throws FactionAlreadyTakenException
	{
		if (factions.containsKey(faction))
			throw new FactionAlreadyTakenException(player, faction);

		factions.put(faction, player);
		players.add(player);
		gameInterface.onPlayerJoin(this, player);
		List<Character> characters = new ArrayList<>();

		CharacterCreationCallback characterCreationCallback = (characterCreationTemplate) -> {
			if (characters.size() == numberOfCharacters)
				throw new TooManyCharactersException(numberOfCharacters);
			approveCharacterCreationTemplate(characterCreationTemplate);
			Room      currentLocation = faction == Factions.ESCAPEES ? startingRoom : endingRoom;
			Character character       = BaseCharacter.fromTemplate(player, gameInterface, characterCreationTemplate, faction, currentLocation);
			faction.addCharacter(character);
			characters.add(character);
		};

		FinishCharacterCreationCallback finishCharacterCreationCallback = () -> {
			if (characters.size() < numberOfCharacters)
				throw new TooFewCharactersException(numberOfCharacters, characters.size());
			this.characters.put(player, characters);
			characters.forEach(character -> player.onCharacterCreate(character));
		};

		player.createCharacters(gameInterface, numberOfCharacters, characterCreationCallback,
				finishCharacterCreationCallback);
	}

	/**
	 * Throws an exception if the provided {@link CharacterCreationCallback} is illegal.
	 *
	 * @param characterCreationTemplate The {@link CharacterCreationTemplate} to approve.
	 * @throws CharacterNameTakenException  When the name of the {@link CharacterCreationTemplate} is taken.
	 * @throws IncompleteCharacterException When information is missing from the {@link CharacterCreationTemplate}.
	 */
	private void approveCharacterCreationTemplate(CharacterCreationTemplate characterCreationTemplate) throws CharacterNameTakenException, IncompleteCharacterException
	{
		if (characterNames.contains(characterCreationTemplate.getName()))
			throw new CharacterNameTakenException(characterCreationTemplate);

		if (characterCreationTemplate.getName() == null)
			throw new IncompleteCharacterException(characterCreationTemplate);
	}

	/**
	 * Starts the {@link Game}.
	 */
	public void start()
	{
		gameInterface.onGameStart(this);
		handleTurn(players.get(0));
	}

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param player The {@link Player}
	 */
	private void handleTurn(Player player)
	{
		gameInterface.onTurnStart(this, player);
		handleCharacterTurn(player, characters.get(player).get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext(Player player)
	{
		gameInterface.onTurnEnd(this, player);
		int index = players.indexOf(player);
		if (index + 1 == players.size()) {
			handleTurn(players.get(0));
			return;
		}

		handleTurn(players.get(index + 1));
	}

	/**
	 * Requests an {@link Action} from the provided {@link Player}.
	 */
	private void handleCharacterTurn(Player player, Character character)
	{
		player.playCharacter(gameInterface, character, ((action, arguments) -> {
			action.perform(gameInterface, character, arguments);
			List<Character> characters   = this.characters.get(player);
			int             currentIndex = characters.indexOf(character);
			if (currentIndex == characters.size() - 1)
				handleNext(player);
			else
				handleCharacterTurn(player, characters.get(currentIndex + 1));
		}));
	}

	/**
	 * Returns the {@link GameInterface} used as input-output for the game.
	 *
	 * @return The {@link GameInterface} used as input-output for the game.
	 */
	public GameInterface getGameInterface()
	{
		return this.gameInterface;
	}

	/**
	 * Returns the number of {@link Character} in each {@link Faction}.
	 *
	 * @return The number of {@link Character} in each {@link Faction}.
	 */
	public int getNumberOfCharacters()
	{
		return this.numberOfCharacters;
	}

	public StartingRoom getStartingRoom()
	{
		return this.startingRoom;
	}

	public EndingRoom getEndingRoom()
	{
		return this.endingRoom;
	}

	/**
	 * Creates the initial state of the game. This includes {@link Door}s, {@link Room}s and
	 * {@link textadventure.items.Item}s.
	 */
	private void generateState()
	{
		try {
			Room[][] rooms = new Room[6][6];

			/*
			 * Generate rooms.
			 */

			rooms[1][0] = new BaseRoom("Room (1,0)", "This small chamber seems divided into three parts. The first has " +
					"several hooks on the walls from which hang dusty robes. An open curtain separates that space from the next, which has a dry basin set in the floor. In the northern part of the room is a door.");
			rooms[3][0] = new StartingRoom("Room (3,0)", "A horrendous, overwhelming stench wafts from the room before you. Small cages containing small animals and large insects line the walls. Some of the creatures look sickly and alive but most are clearly dead. Their rotting corpses and the unclean cages no doubt result in the zoo's foul odor. A cat mews weakly from its cage, but the other creatures just silently shrink back into their filthy prisons. A dusty military sits in the corner of the room. In the northern part of the room is a door.");
			Chest chest = chestFactory(10, Chest.State.CLOSED, lockFactory("KLY4SW", LOCKED, getGameInterface()),
					getGameInterface());
			chest.addItem(new Key("KZSE6X"));
			rooms[3][0].addProperty("chest", chest);
			rooms[3][0].getRoomFloor().addItem(new Key("KLY4SW"));

			rooms[1][1] = new BaseRoom("Room (1,1)", "Corpses and pieces of corpses hang from hooks that dangle from chains attached to thick iron rings. You don't see any heads, hands, or feet -- all seem to have been chopped or torn off. Neither do you see any guts in the horrible array, but several thick leather sacks hang from hooks in the walls, and they are suspiciously wet and the leather looks extremely taut -- as if it' under great strain. In the northern, eastern and southern side of the room is a door.");

			rooms[2][1] = new BaseRoom("Room (2,1)", "You've opened the door to a torture chamber. Several devices of degradation, pain, and death stand about the room, all of them showing signs of regular use. The wood of the rack is worn smooth by struggling bodies, and the iron maiden appears to be occupied by a corpse. In the western, northern and eastern side of the room is a door.");

			rooms[3][1] = new BaseRoom("Room (3,1)", "You catch a whiff of the unmistakable metallic tang of blood as you open the door. The floor is covered with it, and splashes of blood spatter the walls. Some drops even reach the ceiling. It looks fresh, but you don't see any bodies or footprints leaving the chamber. In the eastern, southern and western path of the room is a door.");

			rooms[4][1] = new BaseRoom("Room (4,1)", "You smelled smoke as you moved down the hall, and rounding the corner into this room you see why. Every surface bears scorch marks and ash piles on the floor. The room reeks of fire and burnt flesh. Either a great battle happened here or the room bears some fire danger you cannot see for no flames light the room anymore. In the western, northern and eastern side of the room is a door.");

			rooms[5][1] = new BaseRoom("Room (5,1)", "This tiny room holds a curious array of machinery. Winches and levers project from every wall, and chains with handles dangle from the ceiling. On a nearby wall, you note a pictogram of what looks like a scythe on a chain. In the western side of the room is a door.");

			rooms[1][2] = new BaseRoom("Room (1,2)", "Rats inside the room shriek when they hear the door open, then they run in all directions from a putrid corpse lying in the center of the floor. As these creatures crowd around the edges of the room, seeking to crawl through a hole in one corner, they fight one another. In the northern, eastern and southern side of the room is a door.");

			rooms[2][2] = new BaseRoom("Room (2,2)", "A flurry of bats suddenly flaps through the doorway, their screeching barely audible as they careen past your heads. They flap past you into the rooms and halls beyond. The room from which they came seems barren at first glance. In the western, eastern and southern side of the room is a door.");

			rooms[3][2] = new BaseRoom("Room (3,2)", "A huge iron cage lies on its side in this room, and its gate rests open on the floor. A broken chain lies under the door, and the cage is on a rotting corpse. Another corpse lies a short distance away from the cage. It lacks a head. In the western and eastern side of the room is a door.");

			rooms[4][2] = new BaseRoom("Room (4,4)", "This chamber served as an armory. Armor and weapon racks line the walls and rusty and broken weapons litter the floor. It hasn't been used in a long time. In the western, southern and eastern side of the room is a door.");

			rooms[5][2] = new BaseRoom("Room (5,2)", " This chamber is clearly a prison. Small barred cells line the walls, leaving a 15-foot-wide pathway for a guard to walk. Channels run down either side of the path next to the cages, probably to allow the prisoners' waste to flow through the grates on the other side of the room. The cells appear empty but your vantage point doesn't allow you to see the full extent of them all. In the western side of the room is a door.");

			rooms[0][3] = new BaseRoom("Room (0,3)", "You open the door to what must be a combat training room. Rough fighting circles are scratched into the surface of the floor. Wooden fighting dummies stand waiting for someone to attack them. A few punching bags hang from the ceiling. There's something peculiar about it all though. In the eastern side of the room is a door.");

			rooms[1][3] = new BaseRoom("Room (1,3)", "This small room contains several pieces of well-polished wood furniture. Eight ornate, high-backed chairs surround a long oval table, and a side table stands next to the far exit. All bear delicate carvings of various shapes. One bears carvings of skulls and bones, another is carved with shields and magic circles, and a third is carved with shapes like flames and lightning strokes. In the western, southern and western side of the room is a door.");

			rooms[2][3] = new BaseRoom("Room (2,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western, northern and eastern side of the room is a door.");

			rooms[3][3] = new BaseRoom("Room (3,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western, northern and eastern side of the room is a door.");

			rooms[4][3] = new BaseRoom("Room (4,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western and eastern side of the room is a door.");

			rooms[5][3] = new BaseRoom("Room (5,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.");

			rooms[2][4] = new BaseRoom("Room (2,4)", "This room is of much higher quality that the other rooms. In the " +
					"room is a desk lined with medals. In the southern side of the room is a door.");

			rooms[3][4] = new EndingRoom("Room (3,4)", "Ending room description. In the southern side of the room is a door.");

			this.startingRoom = (StartingRoom) rooms[3][0];
			this.endingRoom = (EndingRoom) rooms[3][4];
			this.endingRoom.getRoomFloor().addItem(new Shotgun());

			/*
			 * Generate doors.
			 */

			final String NORTH_DOOR_NAME = "north";
			final String SOUTH_DOOR_NAME = "south";
			final String EAST_DOOR_NAME  = "east";
			final String WEST_DOOR_NAME  = "west";

			Door door;

			// Vertical

			GameInterface gameInterface = getGameInterface();

			door = doorFactory(OPEN, lockFactory("K5YUZB", UNLOCKED, gameInterface), rooms[1][0], rooms[1][1], gameInterface);
			rooms[1][0].addProperty(NORTH_DOOR_NAME, door);
			rooms[1][1].addProperty(SOUTH_DOOR_NAME, door);


			door = doorFactory(CLOSED, lockFactory("KZSE6X", LOCKED, gameInterface), rooms[3][0], rooms[3][1], gameInterface);
			rooms[3][0].addProperty(NORTH_DOOR_NAME, door);
			rooms[3][1].addProperty(SOUTH_DOOR_NAME, door);

			// Horizontal

			door = doorFactory(OPEN, lockFactory("KN68LL", UNLOCKED, gameInterface), rooms[1][1], rooms[2][1], gameInterface);
			rooms[1][1].addProperty(EAST_DOOR_NAME, door);
			rooms[2][1].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KXICTX", UNLOCKED, gameInterface), rooms[2][1], rooms[3][1], gameInterface);
			rooms[2][1].addProperty(EAST_DOOR_NAME, door);
			rooms[3][1].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KMV3F1", UNLOCKED, gameInterface), rooms[3][1], rooms[4][1], gameInterface);
			rooms[3][1].addProperty(EAST_DOOR_NAME, door);
			rooms[4][1].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KTJX8C", UNLOCKED, gameInterface), rooms[4][1], rooms[5][1], gameInterface);
			rooms[4][1].addProperty(EAST_DOOR_NAME, door);
			rooms[5][1].addProperty(WEST_DOOR_NAME, door);

			// Vertical

			door = doorFactory(OPEN, lockFactory("K1WIWL", UNLOCKED, gameInterface), rooms[1][1], rooms[1][2], gameInterface);
			rooms[1][1].addProperty(NORTH_DOOR_NAME, door);
			rooms[1][2].addProperty(SOUTH_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K8BGK7", UNLOCKED, gameInterface), rooms[2][1], rooms[2][2], gameInterface);
			rooms[2][1].addProperty(NORTH_DOOR_NAME, door);
			rooms[2][2].addProperty(SOUTH_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K3DNE6", UNLOCKED, gameInterface), rooms[4][1], rooms[4][2], gameInterface);
			rooms[4][1].addProperty(NORTH_DOOR_NAME, door);
			rooms[4][2].addProperty(SOUTH_DOOR_NAME, door);

			// Horizontal

			door = doorFactory(OPEN, lockFactory("KT6UH3", UNLOCKED, gameInterface), rooms[1][2], rooms[2][2], gameInterface);
			rooms[1][2].addProperty(EAST_DOOR_NAME, door);
			rooms[2][2].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KB71RC", UNLOCKED, gameInterface), rooms[2][2], rooms[3][2], gameInterface);
			rooms[2][2].addProperty(EAST_DOOR_NAME, door);
			rooms[3][2].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KJ8O29", UNLOCKED, gameInterface), rooms[3][2], rooms[4][2], gameInterface);
			rooms[3][2].addProperty(EAST_DOOR_NAME, door);
			rooms[4][2].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KOOKW5", UNLOCKED, gameInterface), rooms[4][2], rooms[5][2], gameInterface);
			rooms[4][2].addProperty(EAST_DOOR_NAME, door);
			rooms[5][2].addProperty(WEST_DOOR_NAME, door);

			// Vertical

			door = doorFactory(OPEN, lockFactory("KAT55Y", UNLOCKED, gameInterface), rooms[1][2], rooms[1][3], gameInterface);
			rooms[1][2].addProperty(NORTH_DOOR_NAME, door);
			rooms[1][3].addProperty(SOUTH_DOOR_NAME, door);

			// Horizontal

			door = doorFactory(OPEN, lockFactory("KDV046", UNLOCKED, gameInterface), rooms[0][3], rooms[1][3], gameInterface);
			rooms[0][3].addProperty(EAST_DOOR_NAME, door);
			rooms[1][3].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K08L4C", UNLOCKED, gameInterface), rooms[1][3], rooms[2][3], gameInterface);
			rooms[1][3].addProperty(EAST_DOOR_NAME, door);
			rooms[2][3].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KOME75", UNLOCKED, gameInterface), rooms[2][3], rooms[3][3], gameInterface);
			rooms[2][3].addProperty(EAST_DOOR_NAME, door);
			rooms[3][3].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KPQXPS", UNLOCKED, gameInterface), rooms[3][3], rooms[4][3], gameInterface);
			rooms[3][3].addProperty(EAST_DOOR_NAME, door);
			rooms[4][3].addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K3ZH4R", UNLOCKED, gameInterface), rooms[4][3], rooms[5][3], gameInterface);
			rooms[4][3].addProperty(EAST_DOOR_NAME, door);
			rooms[5][3].addProperty(WEST_DOOR_NAME, door);

			// Vertical

			door = doorFactory(OPEN, lockFactory("KEFVH2", UNLOCKED, gameInterface), rooms[2][3], rooms[2][4], gameInterface);
			rooms[2][3].addProperty(NORTH_DOOR_NAME, door);
			rooms[2][4].addProperty(SOUTH_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KVW42D", UNLOCKED, gameInterface), rooms[3][3], rooms[3][4], gameInterface);
			rooms[3][3].addProperty(NORTH_DOOR_NAME, door);
			rooms[3][4].addProperty(SOUTH_DOOR_NAME, door);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction}, {@link UnlockLockAction}.
	 *
	 * @param state         The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock          The {@link Lock} placed on the {@link Door}.
	 * @param roomA         The first room (<code>roomA</code>).
	 * @param roomB         The second room (<code>roomB</code>).
	 * @param gameInterface The {@link GameInterface}.
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	public static Door doorFactory(Door.State state, Lock lock, Room roomA, Room roomB, GameInterface gameInterface)
	{
		BaseDoor door = new BaseDoor(state, lock, roomA, roomB);

		door.addAction("open", new OpenDoorAction(door, gameInterface::onDoorOpen));
		door.addAction("close", new CloseDoorAction(door, gameInterface::onDoorClose));
		door.addAction("use", new UseDoorAction(door, gameInterface::onDoorUse));
		door.addAction("inspect", new InspectDoorAction(door, gameInterface::onDoorInspect));
		door.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		door.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));

		door.addProperty("lock", lock);

		return door;
	}

	/**
	 * Creates a new {@link Room} with a provided {@link Floor}.
	 *
	 * @param name        The name of the {@link Room}
	 * @param description The description of the {@link Room}.
	 * @param floor       The {@link Floor} of the {@link Room}.
	 * @return The newly created {@link Room}.
	 */
	public static Room roomFactory(String name, String description, Floor floor)
	{
		return new BaseRoom(name, description, floor);
	}

	/**
	 * Creates a new {@link Room}.
	 *
	 * @param name        The name of the {@link Room}
	 * @param description The description of the {@link Room}.
	 * @return The newly created {@link Room}.
	 */
	public static Room roomFactory(String name, String description)
	{
		return roomFactory(name, description, new Floor());
	}

	/**
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction}, {@link UnlockLockAction}.
	 *
	 * @param state         The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock          The {@link Lock} placed on the {@link Door}.
	 * @param gameInterface The {@link GameInterface}.
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	public static Door doorFactory(Door.State state, Lock lock, GameInterface gameInterface)
	{
		return doorFactory(state, lock, null, null, gameInterface);
	}

	/**
	 * Creates and returns a {@link Lock} with the {@link LockLockAction}, {@link UnlockLockAction} and
	 * {@link InspectLockAction}.
	 *
	 * @param code          The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 *                      matching codes.
	 * @param state         The state of the {@link Lock}.
	 * @param gameInterface The {@link GameInterface}.
	 * @return The newly created {@link Lock}.
	 */
	public static Lock lockFactory(String code, Lock.State state, GameInterface gameInterface)
	{
		Lock lock = new Lock(code, state);

		lock.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		lock.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));
		lock.addAction("inspect", new InspectLockAction(lock, gameInterface::onLockInspect));

		return lock;
	}

	/**
	 * Creates and returns a new {@link Chest} with the {@link OpenChestAction}, {@link CloseChestAction},
	 * {@link InspectChestAction}, {@link TakeItemFromChestAction}, {@link TakeItemFromChestAction},
	 * {@link LockLockAction}, {@link LockLockAction} and {@link UnlockLockAction}.
	 *
	 * @param countPositions The number of positions available in the {@link Chest}.
	 * @param state          The {@link Chest.State} of the {@link Chest}.
	 * @param lock           The {@link Lock} on the {@link Chest}.
	 * @param gameInterface  The {@link GameInterface}.
	 * @return The newly created {@link Chest}.
	 */
	public static Chest chestFactory(int countPositions, Chest.State state, Lock lock, GameInterface gameInterface)
	{
		Chest chest = new Chest(countPositions, state, lock);

		chest.addAction("open", new OpenChestAction(chest, gameInterface::onChestOpen));
		chest.addAction("close", new CloseChestAction(chest, gameInterface::onChestClose));
		chest.addAction("inspect", new InspectChestAction(chest, gameInterface::onChestInspect));
		chest.addAction("take", new TakeItemFromChestAction(chest, gameInterface::onChestTake));
		chest.addAction("deposit", new DepositItemsIntoChestAction(chest, gameInterface::onChestDeposit));
		chest.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		chest.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));

		chest.addProperty("lock", lock);

		return chest;
	}
}
