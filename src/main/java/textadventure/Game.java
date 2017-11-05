package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.Action;
import textadventure.characters.*;
import textadventure.characters.Character;
import textadventure.combat.Faction;
import textadventure.doors.*;
import textadventure.highscore.HighScoreWriter;
import textadventure.highscore.Outcome;
import textadventure.highscore.Score;
import textadventure.items.BandAid;
import textadventure.items.Gauze;
import textadventure.items.Money;
import textadventure.items.chest.*;
import textadventure.items.weapons.*;
import textadventure.items.wearables.*;
import textadventure.lock.*;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Coordinate;
import textadventure.rooms.Room;
import textadventure.rooms.RoomController;

import java.nio.file.Paths;
import java.util.*;

import static textadventure.doors.Door.State.CLOSED;
import static textadventure.doors.Door.State.OPEN;
import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class Game
{

	private static String ROOM_2_1_DESCRIPTION = "This small chamber seems divided into three parts. The first has several hooks on the walls from which hang dusty robes. An open curtain separates that space from the next, which has a dry basin set in the floor.";

	private static String ROOM_4_1_DESCRIPTION = "A horrendous, overwhelming stench wafts from the room before you. Small cages containing small animals and large insects line the walls. Some of the creatures look sickly and alive but most are clearly dead. Their rotting corpses and the unclean cages no doubt result in the zoo's foul odor. A cat mews weakly from its cage, but the other creatures just silently shrink back into their filthy prisons. A dusty military sits in the corner of the room.";

	private static String ROOM_2_2_DESCRIPTION = "Corpses and pieces of corpses hang from hooks that dangle from chains attached to thick iron rings. You don't see any heads, hands, or feet -- all seem to have been chopped or torn off. Neither do you see any guts in the horrible array, but several thick leather sacks hang from hooks in the walls, and they are suspiciously wet and the leather looks extremely taut -- as if it' under great strain.";

	private static String ROOM_3_2_DESCRIPTION = "You've opened the door to a torture chamber. Several devices of degradation, pain, and death stand about the room, all of them showing signs of regular use. The wood of the rack is worn smooth by struggling bodies, and the iron maiden appears to be occupied by a corpse.";

	private static String ROOM_4_2_DESCRIPTION = "You catch a whiff of the unmistakable metallic tang of blood as you open the door. The floor is covered with it, and splashes of blood spatter the walls. Some drops even reach the ceiling. It looks fresh, but you don't see any bodies or footprints leaving the chamber. In the eastern, southern and western path of the room is a door.";

	private static String ROOM_5_2_DESCRIPTION = "You smelled smoke as you moved down the hall, and rounding the corner into this room you see why. Every surface bears scorch marks and ash piles on the floor. The room reeks of fire and burnt flesh. Either a great battle happened here or the room bears some fire danger you cannot see for no flames light the room anymore.";

	private static String ROOM_6_2_DESCRIPTION = "This tiny room holds a curious array of machinery. Winches and levers project from every wall, and chains with handles dangle from the ceiling. On a nearby wall, you note a pictogram of what looks like a scythe on a chain.";

	private static String ROOM_2_3_DESCRIPTION = "Rats inside the room shriek when they hear the door open, then they run in all directions from a putrid corpse lying in the center of the floor. As these creatures crowd around the edges of the room, seeking to crawl through a hole in one corner, they fight one another.";

	private static String ROOM_3_3_DESCRIPTION = "A flurry of bats suddenly flaps through the doorway, their screeching barely audible as they careen past your heads. They flap past you into the rooms and halls beyond. The room from which they came seems barren at first glance.";

	private static String ROOM_4_3_DESCRIPTION = "A huge iron cage lies on its side in this room, and its gate rests open on the floor. A broken chain lies under the door, and the cage is on a rotting corpse. Another corpse lies a short distance away from the cage. It lacks a head.";

	private static String ROOM_5_3_DESCRIPTION = "This chamber served as an armory. Armor and weapon racks line the walls and rusty and broken weapons litter the floor. It hasn't been used in a long time.";

	private static String ROOM_6_3_DESCRIPTION = "This chamber is clearly a prison. Small barred cells line the walls, leaving a 15-foot-wide pathway for a guard to walk. Channels run down either side of the path next to the cages, probably to allow the prisoners' waste to flow through the grates on the other side of the room. The cells appear empty but your vantage point doesn't allow you to see the full extent of them all.";

	private static String ROOM_1_4_DESCRIPTION = "You open the door to what must be a combat training room. Rough fighting circles are scratched into the surface of the floor. Wooden fighting dummies stand waiting for someone to attack them. A few punching bags hang from the ceiling. There's something peculiar about it all though.";

	private static String ROOM_2_4_DESCRIPTION = "This small room contains several pieces of well-polished wood furniture. Eight ornate, high-backed chairs surround a long oval table, and a side table stands next to the far exit. All bear delicate carvings of various shapes. One bears carvings of skulls and bones, another is carved with shields and magic circles, and a third is carved with shapes like flames and lightning strokes.";

	private static String ROOM_3_4_DESCRIPTION = "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.";

	private static String ROOM_4_4_DESCRIPTION = "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.";

	private static String ROOM_5_4_DESCRIPTION = "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp";

	private static String ROOM_6_4_DESCRIPTION = "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.";

	private static String ROOM_3_5_DESCRIPTION = "This room is of much higher quality that the other rooms. In the room is a desk lined with medals.";

	/**
	 * The minimum amount of {@link Character}s controlled by a {@link Player} in the {@link Game}.
	 */
	private final int numberOfCharacters;
	/**
	 * The types of {@link Faction}s in the {@link Game}.
	 */
	private final Map<Class<? extends Faction>, Faction> factionTypes   = new HashMap<>();
	/**
	 * Returns the {@link Set} of the names in the {@link Game}.
	 */
	private final Set<String>                            characterNames = new HashSet<>();
	/**
	 * The current active {@link Faction} in the {@link Game}
	 */
	private       List<Faction>                          factions       = new ArrayList<>();
	/**
	 * The active factions.
	 */
	private       List<Faction>                          activeFactions = new ArrayList<>();
	/**
	 * The {@link Room}s that form the playable area in the {@link Game}.
	 */
	private       RoomController                         rooms          = new RoomController();

	/**
	 * The {@link GameInterface} to use for input-output.
	 *
	 * @param numberOfCharacters The number of {@link Character}s controlled by a {@link Player}.
	 */
	public Game(int numberOfCharacters) throws Exception
	{
		if (numberOfCharacters < 1)
			throw new IllegalArgumentException("numberOfCharacters must be positive.");

		this.numberOfCharacters = numberOfCharacters;

		Room  room;
		Chest chest;

		room = new BaseRoom("Room (2,1)", ROOM_2_1_DESCRIPTION);
		chest = chestFactory(10, Chest.State.CLOSED, lockFactory("KLY4SW", LOCKED));
		room.putProperty(chest);
		rooms.add(Coordinate.of(2, 1), room);

		room = new BaseRoom("Room (4,1)", ROOM_4_1_DESCRIPTION);
		chest = chestFactory(10, Chest.State.CLOSED, lockFactory("KLY4SW", LOCKED));
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
		room.putProperty(chest);
		room.getRoomFloor().addItem(new Key("KLY4SW"));
		rooms.add(Coordinate.of(4, 1), room);

		rooms.add(Coordinate.of(2, 2), new BaseRoom("Room (2,2)", ROOM_2_2_DESCRIPTION));
		rooms.get(Coordinate.of(2, 2)).getRoomFloor().addItem(new Whetstone());
		rooms.get(Coordinate.of(2, 2)).getRoomFloor().addItem(new Money());

		rooms.add(Coordinate.of(3, 2), new BaseRoom("Room (3,2)", ROOM_3_2_DESCRIPTION));
		rooms.get(Coordinate.of(3, 2)).getRoomFloor().addItem(new Money());
		rooms.get(Coordinate.of(3, 2)).getRoomFloor().addItem(new Gauze());
		rooms.get(Coordinate.of(3, 2)).getRoomFloor().addItem(new BandAid());


		rooms.add(Coordinate.of(4, 2), new BaseRoom("Room (4,2)", ROOM_4_2_DESCRIPTION));

		rooms.add(Coordinate.of(5, 2), new BaseRoom("Room (5,2)", ROOM_5_2_DESCRIPTION));
		chest = chestFactory(10, Chest.State.CLOSED, lockFactory("DNO56W", UNLOCKED));
		chest.addItem(new KelvarBoots());
		chest.addItem(new Key("SWDN9d"));
		rooms.get(Coordinate.of(5, 2)).putProperty(chest);

		rooms.add(Coordinate.of(6, 2), new BaseRoom("Room (6,2)", ROOM_6_2_DESCRIPTION));
		rooms.get(Coordinate.of(6, 2)).getRoomFloor().addItem(new Whetstone());
		rooms.get(Coordinate.of(6, 2)).getRoomFloor().addItem(new Baton());

		rooms.add(Coordinate.of(2, 3), new BaseRoom("Room (2,3)", ROOM_2_3_DESCRIPTION));
		rooms.get(Coordinate.of(2, 3)).getRoomFloor().addItem(new GlassBottle());
		rooms.get(Coordinate.of(2, 3)).getRoomFloor().addItem(new Money());

		rooms.add(Coordinate.of(3, 3), new BaseRoom("Room (3,3)", ROOM_3_3_DESCRIPTION));
		Chest chest4 = chestFactory(10, Chest.State.CLOSED, lockFactory("WF5FEW", UNLOCKED));
		chest4.addItem(new ScrewDriver());
		rooms.get(Coordinate.of(3, 3)).putProperty(chest4);

		rooms.add(Coordinate.of(4, 3), new BaseRoom("Room (4,3)", ROOM_4_3_DESCRIPTION));
		rooms.get(Coordinate.of(4, 3)).getRoomFloor().addItem(new KelvarBodyArmour());
		rooms.get(Coordinate.of(4, 3)).getRoomFloor().addItem(new Hammer());
		rooms.get(Coordinate.of(4, 3)).getRoomFloor().addItem(new Shotgun());


		rooms.add(Coordinate.of(5, 3), new BaseRoom("Room (5,3)", ROOM_5_3_DESCRIPTION));
		rooms.get(Coordinate.of(5, 3)).getRoomFloor().addItem(new Key("JBI5DW"));
		rooms.get(Coordinate.of(5, 3)).getRoomFloor().addItem(new Grenade());


		rooms.add(Coordinate.of(6, 3), new BaseRoom("Room (6,3)", ROOM_6_3_DESCRIPTION));
		Chest chest5 = chestFactory(10, Chest.State.CLOSED, lockFactory("SCFE5F", UNLOCKED));
		chest5.addItem(new BandAid());
		chest5.addItem(new Gauze());
		chest5.addItem(new SubMachineGun());
		rooms.get(Coordinate.of(6, 3)).putProperty(chest5);

		rooms.add(Coordinate.of(1, 4), new BaseRoom("Room (1,4)", ROOM_1_4_DESCRIPTION));
		Chest chest7 = chestFactory(10, Chest.State.CLOSED, lockFactory("JBI5DW", LOCKED));
		chest7.addItem(new Whetstone());
		chest7.addItem(new KelvarGloves());
		chest7.addItem(new Money());
		rooms.get(Coordinate.of(1, 4)).putProperty(chest7);

		rooms.add(Coordinate.of(2, 4), new BaseRoom("Room (2,4)", ROOM_2_4_DESCRIPTION));
		rooms.get(Coordinate.of(2, 4)).getRoomFloor().addItem(new KelvarCargoPants());
		rooms.get(Coordinate.of(2, 4)).getRoomFloor().addItem(new Money());

		rooms.add(Coordinate.of(3, 4), new BaseRoom("Room (3,4)", ROOM_3_4_DESCRIPTION));
		rooms.get(Coordinate.of(3, 4)).getRoomFloor().addItem(new Fork());
		rooms.get(Coordinate.of(3, 4)).getRoomFloor().addItem(new Money());


		rooms.add(Coordinate.of(4, 4), new BaseRoom("Room (4,4)", ROOM_4_4_DESCRIPTION));
		rooms.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new Knife());
		rooms.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarCargoPants());
		rooms.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarBoots());
		rooms.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarGloves());
		rooms.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarHelmet());
		rooms.get(Coordinate.of(4, 4)).getRoomFloor().addItem(new KelvarBodyArmour());

		rooms.add(Coordinate.of(5, 4), new BaseRoom("Room (5,4)", ROOM_5_4_DESCRIPTION));
		rooms.get(Coordinate.of(5, 4)).getRoomFloor().addItem(new M18MachineGun());

		rooms.add(Coordinate.of(6, 4), new BaseRoom("Room (6,4)", ROOM_6_4_DESCRIPTION));
		Chest chest6 = chestFactory(10, Chest.State.CLOSED, lockFactory("SWDN9d", LOCKED));
		chest6.addItem(new KelvarHelmet());
		chest6.addItem(new Pistol());
		rooms.get(Coordinate.of(6, 4)).putProperty(chest6);

		rooms.add(Coordinate.of(3, 5), new BaseRoom("Room (3,5)", ROOM_3_5_DESCRIPTION));
		rooms.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new LegendaryRock());
		rooms.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new Pipe());
		rooms.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new BandAid());
		rooms.get(Coordinate.of(3, 5)).getRoomFloor().addItem(new Gauze());

		rooms.add(Coordinate.of(4, 5), new BaseRoom("Room (4,5)", "Ending room description."));
		Chest chest9 = chestFactory(10, Chest.State.CLOSED, lockFactory("SWDN9d", UNLOCKED));
		chest9.addItem(new AA12Shotgun());
		chest9.addItem(new Money());
		rooms.get(Coordinate.of(4, 5)).putProperty(chest9);

		Door door;
		door = doorFactory(OPEN, lockFactory("K5YUZB", UNLOCKED), rooms.get(Coordinate.of(2, 1)), rooms.get(Coordinate.of(2, 2)));
		rooms.get(Coordinate.of(2, 1)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(2, 2)).putProperty(new SouthDoor(door));


		door = doorFactory(CLOSED, lockFactory("KZSE6X", LOCKED), rooms.get(Coordinate.of(4, 1)), rooms.get(Coordinate.of(4, 2)));
		rooms.get(Coordinate.of(4, 1)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(4, 2)).putProperty(new SouthDoor(door));

		// Horizontal

		door = doorFactory(OPEN, lockFactory("KN68LL", UNLOCKED), rooms.get(Coordinate.of(2, 2)), rooms.get(Coordinate.of(3, 2)));
		rooms.get(Coordinate.of(2, 2)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(3, 2)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KXICTX", UNLOCKED), rooms.get(Coordinate.of(3, 2)), rooms.get(Coordinate.of(4, 2)));
		rooms.get(Coordinate.of(3, 2)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(4, 2)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KMV3F1", UNLOCKED), rooms.get(Coordinate.of(4, 2)), rooms.get(Coordinate.of(5, 2)));
		rooms.get(Coordinate.of(4, 2)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(5, 2)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KTJX8C", UNLOCKED), rooms.get(Coordinate.of(5, 2)), rooms.get(Coordinate.of(6, 2)));
		rooms.get(Coordinate.of(5, 2)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(6, 2)).putProperty(new WestDoor(door));

		// Vertical

		door = doorFactory(OPEN, lockFactory("K1WIWL", UNLOCKED), rooms.get(Coordinate.of(2, 2)), rooms.get(Coordinate.of(2, 3)));
		rooms.get(Coordinate.of(2, 2)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(2, 3)).putProperty(new SouthDoor(door));

		door = doorFactory(OPEN, lockFactory("K8BGK7", UNLOCKED), rooms.get(Coordinate.of(3, 2)), rooms.get(Coordinate.of(3, 3)));
		rooms.get(Coordinate.of(3, 2)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(3, 3)).putProperty(new SouthDoor(door));

		door = doorFactory(OPEN, lockFactory("K3DNE6", UNLOCKED), rooms.get(Coordinate.of(5, 2)), rooms.get(Coordinate.of(5, 3)));
		rooms.get(Coordinate.of(5, 2)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(5, 3)).putProperty(new SouthDoor(door));

		// Horizontal

		door = doorFactory(OPEN, lockFactory("KT6UH3", UNLOCKED), rooms.get(Coordinate.of(2, 3)), rooms.get(Coordinate.of(3, 3)));
		rooms.get(Coordinate.of(2, 3)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(3, 3)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KB71RC", UNLOCKED), rooms.get(Coordinate.of(3, 3)), rooms.get(Coordinate.of(4, 3)));
		rooms.get(Coordinate.of(3, 3)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(4, 3)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KJ8O29", UNLOCKED), rooms.get(Coordinate.of(4, 3)), rooms.get(Coordinate.of(5, 3)));
		rooms.get(Coordinate.of(4, 3)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(5, 3)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KOOKW5", UNLOCKED), rooms.get(Coordinate.of(5, 3)), rooms.get(Coordinate.of(6, 3)));
		rooms.get(Coordinate.of(5, 3)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(6, 3)).putProperty(new WestDoor(door));

		// Vertical

		door = doorFactory(OPEN, lockFactory("KAT55Y", UNLOCKED), rooms.get(Coordinate.of(2, 3)), rooms.get(Coordinate.of(2, 4)));
		rooms.get(Coordinate.of(2, 3)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(2, 4)).putProperty(new SouthDoor(door));

		// Horizontal

		door = doorFactory(OPEN, lockFactory("KDV046", UNLOCKED), rooms.get(Coordinate.of(1, 4)), rooms.get(Coordinate.of(2, 4)));
		rooms.get(Coordinate.of(1, 4)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(2, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("K08L4C", UNLOCKED), rooms.get(Coordinate.of(2, 4)), rooms.get(Coordinate.of(3, 4)));
		rooms.get(Coordinate.of(2, 4)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(3, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KOME75", UNLOCKED), rooms.get(Coordinate.of(3, 4)), rooms.get(Coordinate.of(4, 4)));
		rooms.get(Coordinate.of(3, 4)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(4, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("KPQXPS", UNLOCKED), rooms.get(Coordinate.of(4, 4)), rooms.get(Coordinate.of(5, 4)));
		rooms.get(Coordinate.of(4, 4)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(5, 4)).putProperty(new WestDoor(door));

		door = doorFactory(OPEN, lockFactory("K3ZH4R", UNLOCKED), rooms.get(Coordinate.of(5, 4)), rooms.get(Coordinate.of(6, 4)));
		rooms.get(Coordinate.of(5, 4)).putProperty(new EastDoor(door));
		rooms.get(Coordinate.of(6, 4)).putProperty(new WestDoor(door));

		// Vertical

		door = doorFactory(OPEN, lockFactory("KEFVH2", UNLOCKED), rooms.get(Coordinate.of(3, 4)), rooms.get(Coordinate.of(3, 5)));
		rooms.get(Coordinate.of(3, 4)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(3, 5)).putProperty(new SouthDoor(door));

		door = doorFactory(OPEN, lockFactory("KVW42D", UNLOCKED), rooms.get(Coordinate.of(4, 4)), rooms.get(Coordinate.of(4, 5)));
		rooms.get(Coordinate.of(4, 4)).putProperty(new NorthDoor(door));
		rooms.get(Coordinate.of(4, 5)).putProperty(new SouthDoor(door));
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
		return new BaseDoor(state, lock, roomA, roomB);
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
		return new Lock(code, state);
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

		chest.putProperty(lock);

		return chest;
	}

	/**
	 * Returns the {@link Faction} of the provided type.
	 *
	 * @param type The type of {@link Faction} to return.
	 * @param <T>  The type of {@link Faction} to return.
	 * @return The {@link Faction} of the provided type. Returns null if no {@link Faction} of the provided type is
	 * active.
	 * @throws NoSuchFactionException When a faction of the provided type doesn't exist.
	 */
	public <T extends Faction> T getFaction(Class<T> type) throws NoSuchFactionException
	{
		Faction faction = factionTypes.get(type);
		if (faction == null)
			throw new NoSuchFactionException(type);

		return type.cast(faction);
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param faction The {@link Faction} the {@link Player} plays for.
	 */
	public void addFaction(Faction faction) throws FactionAlreadyTakenException
	{
		List<Character> characters = new ArrayList<>();

		CharacterCreationCallback create = (characterCreationTemplate) -> {
			if (characters.size() == numberOfCharacters)
				throw new TooManyCharactersException(numberOfCharacters);
			approveCharacterCreationTemplate(characterCreationTemplate);
			Character character = BaseCharacter.factory(faction, faction.getStartingRoom(), characterCreationTemplate);
			faction.addCharacter(character);
			characters.add(character);
			characterNames.add(character.getName());
		};

		FinishCharacterCreationCallback finish = () -> {
			if (characters.size() < numberOfCharacters)
				throw new TooFewCharactersException(numberOfCharacters, characters.size());
			characters.forEach(character -> {
				faction.getLeader().onCharacterCreation(character);
				factions.add(faction);
				activeFactions.add(faction);
				factionTypes.put(faction.getClass(), faction);
			});
		};

		faction.getLeader().onCharactersCreate(numberOfCharacters, create, finish);
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
		for (Faction faction : factions)
			faction.getLeader().onGameStart(this, ImmutableList.copyOf(factions), faction);

		handleFactionTurn(factions.get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNextFaction(Faction faction)
	{
		if (activeFactions.size() == 0)
			System.exit(1);

		int index = factions.indexOf(faction);

		if (index == -1)
			throw new IllegalStateException();

		for (int x = index + 1; x < factions.size(); x++) {
			if (activeFactions.contains(factions.get(x))) {
				handleFactionTurn(factions.get(x));
				return;
			}
		}

		for (int x = 0; x < index; x++) {
			if (activeFactions.contains(factions.get(x))) {
				handleFactionTurn(factions.get(x));
				return;
			}
		}
	}

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param faction The {@link Faction}
	 */
	private void handleFactionTurn(Faction faction)
	{
		handleCharactersTurn(faction, faction.getActiveCharacters());
	}

	/**
	 * Returns the score of the provided {@link Faction}.
	 *
	 * @param faction The {@link Faction}.
	 * @return The score of the {@link Faction}.
	 */
	private int getScore(Faction faction)
	{
		return faction.getCharacters(Character.Status.ALIVE).size();
	}

	private void saveHighScore(Score score)
	{
		try {
			HighScoreWriter writer = new HighScoreWriter(Paths.get("scores.txt"));
			writer.put(score);
			writer.save();
		} catch (Exception e) {
			// log
		}
	}

	/**
	 * Requests an {@link Action} from the provided {@link Player}.
	 */
	private void handleCharactersTurn(Faction faction, List<Character> characters)
	{

		if (faction.hasLost(this)) {
			faction.getLeader().onGameEnd(this, false);
			int score = getScore(faction);
			faction.getLeader().onHighScoreRequest(score, Outcome.LOSS, ((save, name) -> {
				if (save) saveHighScore(new Score(name, score, Outcome.LOSS));
			}));
			activeFactions.remove(faction);
			handleNextFaction(faction);
			return;
		}

		if (faction.hasWon(this)) {
			faction.getLeader().onGameEnd(this, true);
			int score = getScore(faction);
			faction.getLeader().onHighScoreRequest(score, Outcome.WIN, ((save, name) -> {
				if (save) saveHighScore(new Score(name, score, Outcome.WIN));
			}));
			activeFactions.remove(faction);
			handleNextFaction(faction);
			return;
		}

		CharacterSelectionCallback selectionCallback = new CharacterSelectionCallback()
		{

			@Override public void next(Character character) throws CharacterAlreadyPlayedException
			{
				faction.getLeader().onActionRequest(character, action -> {
					action.perform(character, character.getFaction().getLeader());
					List<Character> nextCharacters = new ArrayList<>();
					nextCharacters.addAll(characters);
					nextCharacters.remove(character);

					if (nextCharacters.size() == 0) {
						handleNextFaction(faction);
						return;
					}

					handleCharactersTurn(faction, nextCharacters);
				});
			}

			@Override public void skip()
			{
				handleNextFaction(faction);
			}
		};

		try {
			if (characters.size() == 1)
				selectionCallback.next(characters.get(0));
			else
				faction.getLeader().onNextCharacter(ImmutableList.copyOf(characters), selectionCallback);
		} catch (CharacterAlreadyPlayedException e) {
			faction.getLeader().onNextCharacter(ImmutableList.copyOf(characters), selectionCallback);
		}
	}

	public RoomController getRooms()
	{
		return rooms;
	}

	public int getNumberOfActiveFactions()
	{
		return activeFactions.size();
	}
}
