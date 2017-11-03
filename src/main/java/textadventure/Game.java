package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.Action;
import textadventure.characters.*;
import textadventure.characters.Character;
import textadventure.combat.Faction;
import textadventure.doors.*;
import textadventure.highscore.HighScoreWriter;
import textadventure.items.Money;
import textadventure.items.chest.*;
import textadventure.items.medical.BandAid;
import textadventure.items.medical.Gauze;
import textadventure.items.weapons.*;
import textadventure.items.wearables.*;
import textadventure.lock.*;
import textadventure.rooms.*;
import textadventure.ui.GameInterface;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static textadventure.doors.Door.State.CLOSED;
import static textadventure.doors.Door.State.OPEN;
import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class Game
{

	private GameState gameState;

	public RoomController getRoomController()
	{
		return gameState.getRoomController();
	}

	private Path highScoreFile = Paths.get("scores.txt");

	private HighScoreWriter highScoreWriter = new HighScoreWriter(highScoreFile);

	/**
	 * The minimum amount of {@link Character}s controlled by a {@link Player} in the {@link Game}.
	 */
	private final int numberOfCharacters;

	/**
	 * The {@link Faction}s in the {@link Game}.
	 */
	private final List<Faction> factions;

	/**
	 * Returns the {@link Set} of the names in the {@link Game}.
	 */
	private final Set<String> characterNames;

	public static Game create(int numberOfCharacters) throws Exception
	{

		RoomController roomController = new RoomController();
		roomController.add(Coordinate.of(2, 1), new BaseRoom("Room (2,1)", "This small chamber seems divided into three parts. The first has several hooks on the walls from which hang dusty robes. An open curtain separates that space from the next, which has a dry basin set in the floor. In the northern part of the room is a door."));
		Chest chest3 = chestFactory(10, Chest.State.CLOSED, lockFactory("KLY4SW", LOCKED));
		roomController.get(Coordinate.of(2, 1)).putProperty(chest3);

		roomController.add(Coordinate.of(4, 1), new BaseRoom("Room (4,1)", "A horrendous, overwhelming stench wafts from the room before you. Small cages containing small animals and large insects line the walls. Some of the creatures look sickly and alive but most are clearly dead. Their rotting corpses and the unclean cages no doubt result in the zoo's foul odor. A cat mews weakly from its cage, but the other creatures just silently shrink back into their filthy prisons. A dusty military sits in the corner of the room. In the northern part of the room is a door."));
		Chest chest = chestFactory(10, Chest.State.CLOSED, lockFactory("KLY4SW", LOCKED));
		chest.addItem(new Key("KZSE6X"));
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());
		chest.addItem(new Money());

		roomController.get(Coordinate.of(4, 1)).putProperty(chest);
		roomController.get(Coordinate.of(4, 1)).getRoomFloor().addItem(new Key("KLY4SW"));

		roomController.add(Coordinate.of(2, 2), new BaseRoom("Room (2,2)", "Corpses and pieces of corpses hang from hooks that dangle from chains attached to thick iron rings. You don't see any heads, hands, or feet -- all seem to have been chopped or torn off. Neither do you see any guts in the horrible array, but several thick leather sacks hang from hooks in the walls, and they are suspiciously wet and the leather looks extremely taut -- as if it' under great strain. In the northern, eastern and southern side of the room is a door."));
		roomController.get(Coordinate.of(2, 2)).getRoomFloor().addItem(new Whetstone());
		roomController.get(Coordinate.of(2, 2)).getRoomFloor().addItem(new Money());

		roomController.add(Coordinate.of(3, 2), new BaseRoom("Room (3,2)", "You've opened the door to a torture chamber. Several devices of degradation, pain, and death stand about the room, all of them showing signs of regular use. The wood of the rack is worn smooth by struggling bodies, and the iron maiden appears to be occupied by a corpse. In the western, northern and eastern side of the room is a door."));
		roomController.get(Coordinate.of(3, 2)).getRoomFloor().addItem(new Money());
		roomController.get(Coordinate.of(3, 2)).getRoomFloor().addItem(new Gauze());
		roomController.get(Coordinate.of(3, 2)).getRoomFloor().addItem(new BandAid());


		roomController.add(Coordinate.of(4, 2), new BaseRoom("Room (4,2)", "You catch a whiff of the unmistakable metallic tang of blood as you open the door. The floor is covered with it, and splashes of blood spatter the walls. Some drops even reach the ceiling. It looks fresh, but you don't see any bodies or footprints leaving the chamber. In the eastern, southern and western path of the room is a door."));

		roomController.add(Coordinate.of(5, 2), new BaseRoom("Room (5,2)", "You smelled smoke as you moved down the hall, and rounding the corner into this room you see why. Every surface bears scorch marks and ash piles on the floor. The room reeks of fire and burnt flesh. Either a great battle happened here or the room bears some fire danger you cannot see for no flames light the room anymore. In the western, northern and eastern side of the room is a door."));
		Chest chest1 = chestFactory(10, Chest.State.CLOSED, lockFactory("DNO56W", UNLOCKED));
		chest1.addItem(new KelvarBoots());
		chest1.addItem(new Key("SWDN9d"));
		roomController.get(Coordinate.of(5, 2)).putProperty(chest1);


		roomController.add(Coordinate.of(6, 2), new BaseRoom("Room (6,2)", "This tiny room holds a curious array of machinery. Winches and levers project from every wall, and chains with handles dangle from the ceiling. On a nearby wall, you note a pictogram of what looks like a scythe on a chain. In the western side of the room is a door."));
		roomController.get(Coordinate.of(6, 2)).getRoomFloor().addItem(new Whetstone());
		roomController.get(Coordinate.of(6, 2)).getRoomFloor().addItem(new Baton());

		roomController.add(Coordinate.of(2, 3), new BaseRoom("Room (2,3)", "Rats inside the room shriek when they hear the door open, then they run in all directions from a putrid corpse lying in the center of the floor. As these creatures crowd around the edges of the room, seeking to crawl through a hole in one corner, they fight one another. In the northern, eastern and southern side of the room is a door."));
		roomController.get(Coordinate.of(2, 3)).getRoomFloor().addItem(new GlassBottle());
		roomController.get(Coordinate.of(2, 3)).getRoomFloor().addItem(new Money());

		roomController.add(Coordinate.of(3, 3), new BaseRoom("Room (3,3)", "A flurry of bats suddenly flaps through the doorway, their screeching barely audible as they careen past your heads. They flap past you into the rooms and halls beyond. The room from which they came seems barren at first glance. In the western, eastern and southern side of the room is a door."));
		Chest chest4 = chestFactory(10, Chest.State.CLOSED, lockFactory("WF5FEW", UNLOCKED));
		chest4.addItem(new ScrewDriver());
		roomController.get(Coordinate.of(3, 3)).putProperty(chest4);

		roomController.add(Coordinate.of(4, 3), new BaseRoom("Room (4,3)", "A huge iron cage lies on its side in this room, and its gate rests open on the floor. A broken chain lies under the door, and the cage is on a rotting corpse. Another corpse lies a short distance away from the cage. It lacks a head. In the western and eastern side of the room is a door."));
		roomController.get(Coordinate.of(4, 3)).getRoomFloor().addItem(new KelvarBodyArmour());
		roomController.get(Coordinate.of(4, 3)).getRoomFloor().addItem(new Hammer());
		roomController.get(Coordinate.of(4, 3)).getRoomFloor().addItem(new Shotgun());


		roomController.add(Coordinate.of(5, 3), new BaseRoom("Room (5,3)", "This chamber served as an armory. Armor and weapon racks line the walls and rusty and broken weapons litter the floor. It hasn't been used in a long time. In the western, southern and eastern side of the room is a door."));
		roomController.get(Coordinate.of(5, 3)).getRoomFloor().addItem(new Key("JBI5DW"));
		roomController.get(Coordinate.of(5, 3)).getRoomFloor().addItem(new Grenade());


		roomController.add(Coordinate.of(6, 3), new BaseRoom("Room (6,3)", " This chamber is clearly a prison. Small barred cells line the walls, leaving a 15-foot-wide pathway for a guard to walk. Channels run down either side of the path next to the cages, probably to allow the prisoners' waste to flow through the grates on the other side of the room. The cells appear empty but your vantage point doesn't allow you to see the full extent of them all. In the western side of the room is a door."));
		Chest chest5 = chestFactory(10, Chest.State.CLOSED, lockFactory("SCFE5F", UNLOCKED));
		chest5.addItem(new BandAid());
		chest5.addItem(new Gauze());
		chest5.addItem(new SubMachineGun());
		roomController.get(Coordinate.of(6, 3)).putProperty(chest5);

		roomController.add(Coordinate.of(1, 4), new BaseRoom("Room (1,4)", "You open the door to what must be a combat training room. Rough fighting circles are scratched into the surface of the floor. Wooden fighting dummies stand waiting for someone to attack them. A few punching bags hang from the ceiling. There's something peculiar about it all though. In the eastern side of the room is a door."));
		Chest chest7 = chestFactory(10, Chest.State.CLOSED, lockFactory("JBI5DW", LOCKED));
		chest7.addItem(new Whetstone());
		chest7.addItem(new KelvarGloves());
		chest7.addItem(new Money());
		roomController.get(Coordinate.of(1, 4)).putProperty(chest7);

		roomController.add(Coordinate.of(2, 4), new BaseRoom("Room (2,4)", "This small room contains several pieces of well-polished wood furniture. Eight ornate, high-backed chairs surround a long oval table, and a side table stands next to the far exit. All bear delicate carvings of various shapes. One bears carvings of skulls and bones, another is carved with shields and magic circles, and a third is carved with shapes like flames and lightning strokes. In the western, southern and western side of the room is a door."));
		roomController.get(Coordinate.of(2, 4)).getRoomFloor().addItem(new KelvarCargoPants());
		roomController.get(Coordinate.of(2, 4)).getRoomFloor().addItem(new Money());

		roomController.add(Coordinate.of(3, 4), new BaseRoom("Room (3,4)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western, northern and eastern side of the room is a door."));
		roomController.get(Coordinate.of(3, 4)).getRoomFloor().addItem(new Fork());
		roomController.get(Coordinate.of(3, 4)).getRoomFloor().addItem(new Money());


		roomController.add(Coordinate.of(4, 4), new BaseRoom("Room (4,4)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western, northern and eastern side of the room is a door."));
		roomController.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new Knife());
		roomController.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarCargoPants());
		roomController.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarBoots());
		roomController.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarGloves());
		roomController.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarHelmet());
		roomController.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarBodyArmour());

		roomController.add(Coordinate.of(5, 4), new BaseRoom("Room (4,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western and eastern side of the room is a door."));
		roomController.get(Coordinate.of(5, 4)).getRoomFloor().addItem(new M18MachineGun());

		roomController.add(Coordinate.of(6, 4), new BaseRoom("Room (5,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp."));
		Chest chest6 = chestFactory(10, Chest.State.CLOSED, lockFactory("SWDN9d", LOCKED));
		chest6.addItem(new KelvarHelmet());
		chest6.addItem(new Pistol());
		roomController.get(Coordinate.of(6, 4)).putProperty(chest6);

		roomController.add(Coordinate.of(3, 5), new BaseRoom("Room (3,5)", "This room is of much higher quality that the other rooms. In the room is a desk lined with medals. In the southern side of the room is a door."));
		roomController.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new LegendaryRock());
		roomController.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new Pipe());
		roomController.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new BandAid());
		roomController.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new Gauze());

		roomController.add(Coordinate.of(4, 5), new BaseRoom("Room (3,4)", "Ending room description. In the southern side of the room is a door."));
		Chest chest9 = chestFactory(10, Chest.State.CLOSED, lockFactory("SWDN9d", UNLOCKED));
		chest9.addItem(new AA12Shotgun());
		chest9.addItem(new Money());
		roomController.get(Coordinate.of(4, 5)).putProperty(chest9);


		final String NORTH_DOOR_NAME = "north";
		final String SOUTH_DOOR_NAME = "south";
		final String EAST_DOOR_NAME  = "east";
		final String WEST_DOOR_NAME  = "west";

		Door door;
		door = doorFactory(OPEN, lockFactory("K5YUZB", UNLOCKED), roomController.get(Coordinate.of(2, 1)), roomController.get(Coordinate.of(2, 2)));
		roomController.get(Coordinate.of(2, 1)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(2, 2)).putProperty(new SouthDoor(door));


		door = doorFactory(CLOSED, lockFactory("KZSE6X", LOCKED), roomController.get(Coordinate.of(4, 1)), roomController.get(Coordinate.of(4, 2)));
		roomController.get(Coordinate.of(4, 1)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(4, 2)).putProperty(new SouthDoor(door));

		// Horizontal

		door = doorFactory(OPEN, lockFactory("KN68LL", UNLOCKED), roomController.get(Coordinate.of(2, 2)), roomController.get(Coordinate.of(3, 2)));
		roomController.get(Coordinate.of(2, 2)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(3, 2)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KXICTX", UNLOCKED), roomController.get(Coordinate.of(3, 2)), roomController.get(Coordinate.of(4, 2)));
		roomController.get(Coordinate.of(3, 2)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(4, 2)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KMV3F1", UNLOCKED), roomController.get(Coordinate.of(4, 2)), roomController.get(Coordinate.of(5, 2)));
		roomController.get(Coordinate.of(4, 2)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(5, 2)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KTJX8C", UNLOCKED), roomController.get(Coordinate.of(5, 2)), roomController.get(Coordinate.of(6, 2)));
		roomController.get(Coordinate.of(5, 2)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(6, 2)).putProperty(new WestDoor(door));

		// Vertical

		door = doorFactory(OPEN, lockFactory("K1WIWL", UNLOCKED), roomController.get(Coordinate.of(2, 2)), roomController.get(Coordinate.of(2, 3)));
		roomController.get(Coordinate.of(2, 2)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(2, 3)).putProperty(new SouthDoor(door));

		door = doorFactory(OPEN, lockFactory("K8BGK7", UNLOCKED), roomController.get(Coordinate.of(3, 2)), roomController.get(Coordinate.of(3, 3)));
		roomController.get(Coordinate.of(3, 2)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(3, 3)).putProperty(new SouthDoor(door));

		door = doorFactory(OPEN, lockFactory("K3DNE6", UNLOCKED), roomController.get(Coordinate.of(5, 2)), roomController.get(Coordinate.of(5, 3)));
		roomController.get(Coordinate.of(5, 2)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(5, 3)).putProperty(new SouthDoor(door));

		// Horizontal

		door = doorFactory(OPEN, lockFactory("KT6UH3", UNLOCKED), roomController.get(Coordinate.of(2, 3)), roomController.get(Coordinate.of(3, 3)));
		roomController.get(Coordinate.of(2, 3)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(3, 3)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KB71RC", UNLOCKED), roomController.get(Coordinate.of(3, 3)), roomController.get(Coordinate.of(4, 3)));
		roomController.get(Coordinate.of(3, 3)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(4, 3)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KJ8O29", UNLOCKED), roomController.get(Coordinate.of(4, 3)), roomController.get(Coordinate.of(5, 3)));
		roomController.get(Coordinate.of(4, 3)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(5, 3)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KOOKW5", UNLOCKED), roomController.get(Coordinate.of(5, 3)), roomController.get(Coordinate.of(6, 3)));
		roomController.get(Coordinate.of(5, 3)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(6, 3)).putProperty(new WestDoor(door));

		// Vertical

		door = doorFactory(OPEN, lockFactory("KAT55Y", UNLOCKED), roomController.get(Coordinate.of(2, 3)), roomController.get(Coordinate.of(2, 4)));
		roomController.get(Coordinate.of(2, 3)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(2, 4)).putProperty(new SouthDoor(door));

		// Horizontal

		door = doorFactory(OPEN, lockFactory("KDV046", UNLOCKED), roomController.get(Coordinate.of(1, 4)), roomController.get(Coordinate.of(2, 4)));
		roomController.get(Coordinate.of(1, 4)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(2, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("K08L4C", UNLOCKED), roomController.get(Coordinate.of(2, 4)), roomController.get(Coordinate.of(3, 4)));
		roomController.get(Coordinate.of(2, 4)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(3, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KOME75", UNLOCKED), roomController.get(Coordinate.of(3, 4)), roomController.get(Coordinate.of(4, 4)));
		roomController.get(Coordinate.of(3, 4)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(4, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KPQXPS", UNLOCKED), roomController.get(Coordinate.of(4, 4)), roomController.get(Coordinate.of(5, 4)));
		roomController.get(Coordinate.of(4, 4)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(5, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("K3ZH4R", UNLOCKED), roomController.get(Coordinate.of(5, 4)), roomController.get(Coordinate.of(6, 4)));
		roomController.get(Coordinate.of(5, 4)).putProperty(new EastDoor(door));
		roomController.get(Coordinate.of(6, 4)).putProperty(new WestDoor(door));

		// Vertical

		door = doorFactory(OPEN, lockFactory("KEFVH2", UNLOCKED), roomController.get(Coordinate.of(3, 4)), roomController.get(Coordinate.of(3, 5)));
		roomController.get(Coordinate.of(3, 4)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(3, 5)).putProperty(new SouthDoor(door));

		door = doorFactory(OPEN, lockFactory("KVW42D", UNLOCKED), roomController.get(Coordinate.of(4, 4)), roomController.get(Coordinate.of(4, 5)));
		roomController.get(Coordinate.of(4, 4)).putProperty(new NorthDoor(door));
		roomController.get(Coordinate.of(4, 5)).putProperty(new SouthDoor(door));

		GameState gameState = new GameState(roomController);

		return new Game(gameState, numberOfCharacters);
	}

	/**
	 * The {@link GameInterface} to use for input-output.
	 *
	 * @param numberOfCharacters The number of {@link Character}s controlled by a {@link Player}.
	 */
	public Game(GameState gameState, int numberOfCharacters) throws Exception
	{
		if (numberOfCharacters < 1)
			throw new IllegalArgumentException("numberOfCharacters must be positive.");

		this.gameState = gameState;
		this.numberOfCharacters = numberOfCharacters;
		this.factions = new ArrayList<>();
		this.characterNames = new HashSet<>();
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param faction The {@link Faction} the {@link Player} plays for.
	 */
	public void addFaction(Faction faction) throws FactionAlreadyTakenException
	{
		gameState.addFaction(faction);
		List<Character> characters = new ArrayList<>();

		CharacterCreationCallback characterCreationCallback = (characterCreationTemplate) -> {
			if (characters.size() == numberOfCharacters)
				throw new TooManyCharactersException(numberOfCharacters);
			approveCharacterCreationTemplate(characterCreationTemplate);
			Character character = BaseCharacter.factory(faction, characterCreationTemplate);
			faction.addCharacter(character);
			characters.add(character);
		};

		FinishCharacterCreationCallback finishCharacterCreationCallback = () -> {
			if (characters.size() < numberOfCharacters)
				throw new TooFewCharactersException(numberOfCharacters, characters.size());
			characters.forEach(character -> faction.getLeader().onCharacterCreation(character));
		};

		faction.getLeader().onCharactersCreate(numberOfCharacters, characterCreationCallback, finishCharacterCreationCallback);
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
		ImmutableList<Faction> factions = gameState.getFactions();
		for (Faction faction : factions)
			faction.getLeader().onGameStart(this, factions, faction);

		handleTurn(factions.get(0));
	}

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param faction The {@link Faction}
	 */
	private void handleTurn(Faction faction)
	{
		handleCharacterTurn(faction, faction.getCharacters());
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext(Faction faction)
	{
		ImmutableList<Faction> factions = gameState.getFactions();
		int                    index    = factions.indexOf(faction);
		if (index + 1 == factions.size()) {
			handleTurn(factions.get(0));
			return;
		}

		handleTurn(factions.get(index + 1));
	}

	/**
	 * Requests an {@link Action} from the provided {@link Player}.
	 */
	private void handleCharacterTurn(Faction faction, List<Character> characters)
	{

		if (faction.hasLost(gameState)) {
			faction.getLeader().onGameEnd(this, false);
			gameState.removeFaction(faction.getClass());
		}

		if (faction.hasWon(gameState)) {
			faction.getLeader().onGameEnd(this, true);
			gameState.removeFaction(faction.getClass());
		}

		faction.getLeader().onNextCharacter(ImmutableList.copyOf(characters), new CharacterSelectionCallback()
		{

			@Override public void next(Character character) throws CharacterAlreadyPlayedException
			{
				faction.getLeader().onActionRequest(character, (action -> {
					action.perform(character, faction.getLeader());
					List<Character> nextCharacters = new ArrayList<>();
					nextCharacters.addAll(characters);
					nextCharacters.remove(character);

					if (nextCharacters.size() == 0) {
						handleNext(faction);
						return;
					}

					handleCharacterTurn(faction, nextCharacters);
				}));
			}

			@Override public void skip()
			{
				handleNext(faction);
			}
		});
	}

	public List<Faction> getFactions()
	{
		return this.factions;
	}

	/**
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction}, {@link UnlockLockAction}.
	 *
	 * @param state The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock  The {@link Lock} placed on the {@link Door}.
	 * @param roomA The first room (<code>roomA</code>).
	 * @param roomB The second room (<code>roomB</code>).
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	private static Door doorFactory(Door.State state, Lock lock, Room roomA, Room roomB)
	{
		BaseDoor door = new BaseDoor(state, lock, roomA, roomB);

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
	private static Room roomFactory(String name, String description, Floor floor)
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
	private static Room roomFactory(String name, String description)
	{
		return roomFactory(name, description, new Floor());
	}

	/**
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction}, {@link UnlockLockAction}.
	 *
	 * @param state The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock  The {@link Lock} placed on the {@link Door}.
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	private static Door doorFactory(Door.State state, Lock lock)
	{
		return doorFactory(state, lock, null, null);
	}

	/**
	 * Creates and returns a {@link Lock} with the {@link LockLockAction}, {@link UnlockLockAction} and
	 * {@link InspectLockAction}.
	 *
	 * @param code  The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 *              matching codes.
	 * @param state The state of the {@link Lock}.
	 * @return The newly created {@link Lock}.
	 */
	private static Lock lockFactory(String code, Lock.State state)
	{
		Lock lock = new Lock(code, state);

		lock.putAction(new LockLockAction(lock));
		lock.putAction(new UnlockLockAction(lock));
		lock.putAction(new InspectLockAction(lock));

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
	 * @return The newly created {@link Chest}.
	 */
	private static Chest chestFactory(int countPositions, Chest.State state, Lock lock)
	{
		Chest chest = new Chest(countPositions, state, lock);

		chest.putAction(new OpenChestAction(chest));
		chest.putAction(new CloseChestAction(chest));
		chest.putAction(new InspectChestAction(chest));
		chest.putAction(new TakeItemFromChestAction(chest));
		chest.putAction(new DepositItemsIntoChestAction(chest));
		chest.putAction(new LockLockAction(lock));
		chest.putAction(new UnlockLockAction(lock));

		chest.putProperty(lock);

		return chest;
	}
}
