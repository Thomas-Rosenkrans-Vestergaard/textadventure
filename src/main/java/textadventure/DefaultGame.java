package textadventure;

import textadventure.doors.BaseDoor;
import textadventure.doors.Door;
import textadventure.items.InventoryFullException;
import textadventure.items.backpack.Backpack;
import textadventure.items.chest.Chest;
import textadventure.lock.Key;
import textadventure.lock.Lock;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.EndingRoom;
import textadventure.rooms.Room;
import textadventure.rooms.StartingRoom;
import textadventure.ui.GameInterface;

import static textadventure.doors.Door.State.CLOSED;
import static textadventure.doors.Door.State.OPEN;
import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class DefaultGame extends Game
{

	/**
	 * The generated rooms.
	 */
	private Room[][] rooms = new Room[6][5];

	/**
	 * Creates a {@link Game}.
	 *
	 * @param gameInterface The {@link GameInterface} to use with the {@link Game}.
	 * @param movesPerTurn  The number of moves per turn per player.
	 */
	public DefaultGame(GameInterface gameInterface, int movesPerTurn)
	{
		super(gameInterface, movesPerTurn);

		try {
			generateRooms();
			generateDoors();

			Backpack backpack = Backpack.factory(10, this);
			backpack.addItem(new Key("LY4SW"));
			Character character = BaseCharacter.factory("George", backpack, rooms[3][0], this);
			this.addPlayer(new HumanPlayer(character));
			this.setMaze(new Maze((StartingRoom) rooms[3][0], (EndingRoom) rooms[3][4]));
		} catch (InventoryFullException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Generate the rooms in the maze.
	 */
	private void generateRooms() throws InventoryFullException
	{
		rooms[1][0] = new BaseRoom("Room (1,0)", "This small chamber seems divided into three parts. The first has several hooks on the walls from which hang dusty robes. An open curtain separates that space from the next, which has a dry basin set in the floor.");
		rooms[3][0] = new StartingRoom("Room (3,0)", "A horrendous, overwhelming stench wafts from the room before you. Small cages containing small animals and large insects line the walls. Some of the creatures look sickly and alive but most are clearly dead. Their rotting corpses and the unclean cages no doubt result in the zoo's foul odor. A cat mews weakly from its cage, but the other creatures just silently shrink back into their filthy prisons.");
		Chest chest = Chest.factory(10, Chest.State.CLOSED, Lock.factory("LY4SW", LOCKED, this), this);
		chest.addItem(new Key("KZSE6X"));
		rooms[3][0].addProperty("chest", chest);
		rooms[3][0].addFeature("A dusty military sits in the corner of the room.");

		rooms[1][1] = new BaseRoom("Room (1,1)", "Corpses and pieces of corpses hang from hooks that dangle from chains attached to thick iron rings. You don't see any heads, hands, or feet -- all seem to have been chopped or torn off. Neither do you see any guts in the horrible array, but several thick leather sacks hang from hooks in the walls, and they are suspiciously wet and the leather looks extremely taut -- as if it' under great strain.");
		rooms[2][1] = new BaseRoom("Room (2,1)", "You've opened the door to a torture chamber. Several devices of degradation, pain, and death stand about the room, all of them showing signs of regular use. The wood of the rack is worn smooth by struggling bodies, and the iron maiden appears to be occupied by a corpse.");
		rooms[3][1] = new BaseRoom("Room (3,1)", "You catch a whiff of the unmistakable metallic tang of blood as you open the door. The floor is covered with it, and splashes of blood spatter the walls. Some drops even reach the ceiling. It looks fresh, but you don't see any bodies or footprints leaving the chamber.");
		rooms[4][1] = new BaseRoom("Room (4,1)", "You smelled smoke as you moved down the hall, and rounding the corner into this room you see why. Every surface bears scorch marks and ash piles on the floor. The room reeks of fire and burnt flesh. Either a great battle happened here or the room bears some fire danger you cannot see for no flames light the room anymore.");
		rooms[5][1] = new BaseRoom("Room (5,1)", "This tiny room holds a curious array of machinery. Winches and levers project from every wall, and chains with handles dangle from the ceiling. On a nearby wall, you note a pictogram of what looks like a scythe on a chain.");

		rooms[1][2] = new BaseRoom("Room (1,2)", "Rats inside the room shriek when they hear the door open, then they run in all directions from a putrid corpse lying in the center of the floor. As these creatures crowd around the edges of the room, seeking to crawl through a hole in one corner, they fight one another.");
		rooms[2][2] = new BaseRoom("Room (2,2)", "A flurry of bats suddenly flaps through the doorway, their screeching barely audible as they careen past your heads. They flap past you into the rooms and halls beyond. The room from which they came seems barren at first glance.");
		rooms[3][2] = new BaseRoom("Room (3,2)", "A huge iron cage lies on its side in this room, and its gate rests open on the floor. A broken chain lies under the door, and the cage is on a rotting corpse. Another corpse lies a short distance away from the cage. It lacks a head.");
		rooms[4][2] = new BaseRoom("Room (4,4)", "This chamber served as an armory. Armor and weapon racks line the walls and rusty and broken weapons litter the floor. It hasn't been used in a long time.");
		rooms[5][2] = new BaseRoom("Room (5,2)", " This chamber is clearly a prison. Small barred cells line the walls, leaving a 15-foot-wide pathway for a guard to walk. Channels run down either side of the path next to the cages, probably to allow the prisoners' waste to flow through the grates on the other side of the room. The cells appear empty but your vantage point doesn't allow you to see the full extent of them all.");

		rooms[0][3] = new BaseRoom("Room (0,3)", "You open the door to what must be a combat training room. Rough fighting circles are scratched into the surface of the floor. Wooden fighting dummies stand waiting for someone to attack them. A few punching bags hang from the ceiling. There's something peculiar about it all though.");
		rooms[1][3] = new BaseRoom("Room (1,3)", "This small room contains several pieces of well-polished wood furniture. Eight ornate, high-backed chairs surround a long oval table, and a side table stands next to the far exit. All bear delicate carvings of various shapes. One bears carvings of skulls and bones, another is carved with shields and magic circles, and a third is carved with shapes like flames and lightning strokes.");
		rooms[2][3] = new BaseRoom("Room (2,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.");
		rooms[3][3] = new BaseRoom("Room (3,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.");
		rooms[4][3] = new BaseRoom("Room (4,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.");
		rooms[5][3] = new BaseRoom("Room (5,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp.");

		rooms[2][4] = new BaseRoom("Room (2,4)", "This room is of much higher quality that the other rooms. In the " +
				"room is a desk lined with medals.");
		rooms[3][4] = new EndingRoom("Room (3,4)", "Ending room description.");
	}

	/**
	 * Generate the doors in the maze.
	 */
	private void generateDoors()
	{
		/*
		 * Room name constants.
		 */
		final String NORTH_DOOR_NAME = "north";
		final String SOUTH_DOOR_NAME = "south";
		final String EAST_DOOR_NAME  = "east";
		final String WEST_DOOR_NAME  = "west";

		/*
		 * Room description constants.
		 */
		final String NORTH_DOOR_DESC = "In the northern part of the room is a door.";
		final String SOUTH_DOOR_DESC = "In the southern part of the room is a door.";
		final String EAST_DOOR_DESC  = "In the eastern part of the room is a door.";
		final String WEST_DOOR_DESC  = "In the western part of the room is a door.";

		Door door;

		// Vertical

		door = BaseDoor.factory(OPEN, Lock.factory("K5YUZB", UNLOCKED, this), rooms[1][0], rooms[1][1], this);
		rooms[1][0].addProperty(NORTH_DOOR_NAME, door);
		rooms[1][1].addProperty(SOUTH_DOOR_NAME, door);
		rooms[1][0].addFeature(NORTH_DOOR_DESC);
		rooms[1][1].addFeature(SOUTH_DOOR_DESC);


		door = BaseDoor.factory(CLOSED, Lock.factory("KZSE6X", LOCKED, this), rooms[3][0], rooms[3][1], this);
		rooms[3][0].addProperty(NORTH_DOOR_NAME, door);
		rooms[3][1].addProperty(SOUTH_DOOR_NAME, door);
		rooms[3][0].addFeature(NORTH_DOOR_DESC);
		rooms[3][1].addFeature(SOUTH_DOOR_DESC);

		// Horizontal

		door = BaseDoor.factory(OPEN, Lock.factory("KN68LL", UNLOCKED, this), rooms[1][1], rooms[2][1], this);
		rooms[1][1].addProperty(EAST_DOOR_NAME, door);
		rooms[2][1].addProperty(WEST_DOOR_NAME, door);
		rooms[1][1].addFeature(EAST_DOOR_DESC);
		rooms[2][1].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KXICTX", UNLOCKED, this), rooms[2][1], rooms[3][1], this);
		rooms[2][1].addProperty(EAST_DOOR_NAME, door);
		rooms[3][1].addProperty(WEST_DOOR_NAME, door);
		rooms[2][1].addFeature(EAST_DOOR_DESC);
		rooms[3][1].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KMV3F1", UNLOCKED, this), rooms[3][1], rooms[4][1], this);
		rooms[3][1].addProperty(EAST_DOOR_NAME, door);
		rooms[4][1].addProperty(WEST_DOOR_NAME, door);
		rooms[3][1].addFeature(EAST_DOOR_DESC);
		rooms[4][1].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KTJX8C", UNLOCKED, this), rooms[4][1], rooms[5][1], this);
		rooms[4][1].addProperty(EAST_DOOR_NAME, door);
		rooms[5][1].addProperty(WEST_DOOR_NAME, door);
		rooms[4][1].addFeature(EAST_DOOR_DESC);
		rooms[5][1].addFeature(WEST_DOOR_DESC);

		// Vertical

		door = BaseDoor.factory(OPEN, Lock.factory("K1WIWL", UNLOCKED, this), rooms[1][1], rooms[1][2], this);
		rooms[1][1].addProperty(NORTH_DOOR_NAME, door);
		rooms[1][2].addProperty(SOUTH_DOOR_NAME, door);
		rooms[1][1].addFeature(NORTH_DOOR_DESC);
		rooms[1][2].addFeature(SOUTH_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("K8BGK7", UNLOCKED, this), rooms[2][1], rooms[2][2], this);
		rooms[2][1].addProperty(NORTH_DOOR_NAME, door);
		rooms[2][2].addProperty(SOUTH_DOOR_NAME, door);
		rooms[2][1].addFeature(NORTH_DOOR_DESC);
		rooms[2][2].addFeature(SOUTH_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("K3DNE6", UNLOCKED, this), rooms[4][1], rooms[4][2], this);
		rooms[4][1].addProperty(NORTH_DOOR_NAME, door);
		rooms[4][2].addProperty(SOUTH_DOOR_NAME, door);
		rooms[4][1].addFeature(NORTH_DOOR_DESC);
		rooms[4][2].addFeature(SOUTH_DOOR_DESC);

		// Horizontal

		door = BaseDoor.factory(OPEN, Lock.factory("KT6UH3", UNLOCKED, this), rooms[1][2], rooms[2][2], this);
		rooms[1][2].addProperty(EAST_DOOR_NAME, door);
		rooms[2][2].addProperty(WEST_DOOR_NAME, door);
		rooms[1][2].addFeature(EAST_DOOR_DESC);
		rooms[2][2].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KB71RC", UNLOCKED, this), rooms[2][2], rooms[3][2], this);
		rooms[2][2].addProperty(EAST_DOOR_NAME, door);
		rooms[3][2].addProperty(WEST_DOOR_NAME, door);
		rooms[2][2].addFeature(EAST_DOOR_DESC);
		rooms[3][2].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KJ8O29", UNLOCKED, this), rooms[3][2], rooms[4][2], this);
		rooms[3][2].addProperty(EAST_DOOR_NAME, door);
		rooms[4][2].addProperty(WEST_DOOR_NAME, door);
		rooms[3][2].addFeature(EAST_DOOR_DESC);
		rooms[4][2].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KOOKW5", UNLOCKED, this), rooms[4][2], rooms[5][2], this);
		rooms[4][2].addProperty(EAST_DOOR_NAME, door);
		rooms[5][2].addProperty(WEST_DOOR_NAME, door);
		rooms[4][2].addFeature(EAST_DOOR_DESC);
		rooms[5][2].addFeature(WEST_DOOR_DESC);

		// Vertical

		door = BaseDoor.factory(OPEN, Lock.factory("KAT55Y", UNLOCKED, this), rooms[1][2], rooms[1][3], this);
		rooms[1][2].addProperty(NORTH_DOOR_NAME, door);
		rooms[1][3].addProperty(SOUTH_DOOR_NAME, door);
		rooms[1][2].addFeature(NORTH_DOOR_DESC);
		rooms[1][3].addFeature(SOUTH_DOOR_DESC);

		// Horizontal

		door = BaseDoor.factory(OPEN, Lock.factory("KDV046", UNLOCKED, this), rooms[0][3], rooms[1][3], this);
		rooms[0][3].addProperty(EAST_DOOR_NAME, door);
		rooms[1][3].addProperty(WEST_DOOR_NAME, door);
		rooms[0][3].addFeature(EAST_DOOR_DESC);
		rooms[1][3].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("K08L4C", UNLOCKED, this), rooms[1][3], rooms[2][3], this);
		rooms[1][3].addProperty(EAST_DOOR_NAME, door);
		rooms[2][3].addProperty(WEST_DOOR_NAME, door);
		rooms[1][3].addFeature(EAST_DOOR_DESC);
		rooms[2][3].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KOME75", UNLOCKED, this), rooms[2][3], rooms[3][3], this);
		rooms[2][3].addProperty(EAST_DOOR_NAME, door);
		rooms[3][3].addProperty(WEST_DOOR_NAME, door);
		rooms[2][3].addFeature(EAST_DOOR_DESC);
		rooms[3][3].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KPQXPS", UNLOCKED, this), rooms[3][3], rooms[4][3], this);
		rooms[3][3].addProperty(EAST_DOOR_NAME, door);
		rooms[4][3].addProperty(WEST_DOOR_NAME, door);
		rooms[3][3].addFeature(EAST_DOOR_DESC);
		rooms[4][3].addFeature(WEST_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("K3ZH4R", UNLOCKED, this), rooms[4][3], rooms[5][3], this);
		rooms[4][3].addProperty(EAST_DOOR_NAME, door);
		rooms[5][3].addProperty(WEST_DOOR_NAME, door);
		rooms[4][3].addFeature(EAST_DOOR_DESC);
		rooms[5][3].addFeature(WEST_DOOR_DESC);

		// Vertical

		door = BaseDoor.factory(OPEN, Lock.factory("KEFVH2", UNLOCKED, this), rooms[2][3], rooms[2][4], this);
		rooms[2][3].addProperty(NORTH_DOOR_NAME, door);
		rooms[2][4].addProperty(SOUTH_DOOR_NAME, door);
		rooms[2][3].addFeature(NORTH_DOOR_DESC);
		rooms[2][4].addFeature(SOUTH_DOOR_DESC);

		door = BaseDoor.factory(OPEN, Lock.factory("KVW42D", UNLOCKED, this), rooms[3][3], rooms[3][4], this);
		rooms[3][3].addProperty(NORTH_DOOR_NAME, door);
		rooms[3][4].addProperty(SOUTH_DOOR_NAME, door);
		rooms[3][3].addFeature(NORTH_DOOR_DESC);
		rooms[3][4].addFeature(SOUTH_DOOR_DESC);
	}
}
