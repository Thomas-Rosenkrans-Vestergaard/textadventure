package textadventure;

import textadventure.doors.BaseDoor;
import textadventure.doors.Door;
import textadventure.lock.Lock;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.EndingRoom;
import textadventure.rooms.Room;
import textadventure.rooms.StartingRoom;

import static textadventure.doors.Door.State.OPEN;
import static textadventure.lock.Lock.State.UNLOCKED;

public class MazeCreator
{

	/**
	 * The generated rooms.
	 */
	private Room[][] rooms = new Room[6][5];

	/**
	 * Generates the maze.
	 *
	 * @return The maze.
	 */
	public Maze generate()
	{
		generateRooms();
		generateDoors();
		return new Maze((StartingRoom) rooms[3][0], (EndingRoom) rooms[3][4]);
	}

	/**
	 * Generate the rooms in the maze.
	 */
	private void generateRooms()
	{
		rooms[1][0] = new BaseRoom("(1,0)", "You entered room (1,0).");
		rooms[3][0] = new StartingRoom("(3,0)", "You entered room (3,0).");

		rooms[1][1] = new BaseRoom("(1,1)", "You entered room (1,1).");
		rooms[2][1] = new BaseRoom("(2,1)", "You entered room (2,1).");
		rooms[3][1] = new BaseRoom("(3,1)", "You entered room (3,1).");
		rooms[4][1] = new BaseRoom("(4,1)", "You entered room (4,1).");
		rooms[5][1] = new BaseRoom("(5,1)", "You entered room (5,1).");

		rooms[1][2] = new BaseRoom("(1,2)", "You entered room (1,2).");
		rooms[2][2] = new BaseRoom("(2,2)", "You entered room (2,2).");
		rooms[3][2] = new BaseRoom("(3,2)", "You entered room (3,2).");
		rooms[4][2] = new BaseRoom("(4,4)", "You entered room (4,4).");
		rooms[5][2] = new BaseRoom("(5,2)", "You entered room (5,2).");

		rooms[0][3] = new BaseRoom("(0,3)", "You entered room (0,3).");
		rooms[1][3] = new BaseRoom("(1,3)", "You entered room (1,3).");
		rooms[2][3] = new BaseRoom("(2,3)", "You entered room (2,3).");
		rooms[3][3] = new BaseRoom("(3,3)", "You entered room (3,3).");
		rooms[4][3] = new BaseRoom("(4,3)", "You entered room (4,3).");
		rooms[5][3] = new BaseRoom("(5,3)", "You entered room (5,3).");

		rooms[2][4] = new BaseRoom("(2,4)", "You entered room (2,4).");
		rooms[3][4] = new EndingRoom("(3,4)", "You entered room (3,4).");
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
		final String EAST_DOOR_NAME = "east";
		final String WEST_DOOR_NAME = "west";

		/*
		 * Room description constants.
		 */
		final String NORTH_DOOR_DESC = "In the northern part of the room is a door.";
		final String SOUTH_DOOR_DESC = "In the southern part of the room is a door.";
		final String EAST_DOOR_DESC = "In the eastern part of the room is a door.";
		final String WEST_DOOR_DESC = "In the western part of the room is a door.";

		Door door;

		// Vertical

		door = new BaseDoor(OPEN, new Lock("K5YUZB", UNLOCKED), rooms[1][0], rooms[1][1]);
		rooms[1][0].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[1][1].addProperty(SOUTH_DOOR_NAME, NORTH_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KZSE6X", UNLOCKED), rooms[3][0], rooms[3][1]);
		rooms[3][0].addProperty(NORTH_DOOR_NAME, SOUTH_DOOR_DESC, door);
		rooms[3][1].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);

		// Horizontal

		door = new BaseDoor(OPEN, new Lock("KN68LL", UNLOCKED), rooms[1][1], rooms[2][1]);
		rooms[1][1].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[2][1].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KXICTX", UNLOCKED), rooms[2][1], rooms[3][1]);
		rooms[2][1].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[3][1].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KMV3F1", UNLOCKED), rooms[3][1], rooms[4][1]);
		rooms[3][1].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[4][1].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KTJX8C", UNLOCKED), rooms[4][1], rooms[5][1]);
		rooms[4][1].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[5][1].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		// Vertical

		door = new BaseDoor(OPEN, new Lock("K1WIWL", UNLOCKED), rooms[1][1], rooms[1][2]);
		rooms[1][1].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[1][2].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("K8BGK7", UNLOCKED), rooms[2][1], rooms[2][2]);
		rooms[2][1].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[2][2].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("K3DNE6", UNLOCKED), rooms[4][1], rooms[4][2]);
		rooms[4][1].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[4][2].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);

		// Horizontal

		door = new BaseDoor(OPEN, new Lock("KT6UH3", UNLOCKED), rooms[1][2], rooms[2][2]);
		rooms[1][2].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[2][2].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KB71RC", UNLOCKED), rooms[2][2], rooms[3][2]);
		rooms[2][2].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[3][2].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KJ8O29", UNLOCKED), rooms[3][2], rooms[4][2]);
		rooms[3][2].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[4][2].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KOOKW5", UNLOCKED), rooms[4][2], rooms[5][2]);
		rooms[4][2].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[5][2].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		// Vertical

		door = new BaseDoor(OPEN, new Lock("KAT55Y", UNLOCKED), rooms[1][2], rooms[1][3]);
		rooms[1][2].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[1][3].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);

		// Horizontal

		door = new BaseDoor(OPEN, new Lock("KDV046", UNLOCKED), rooms[0][3], rooms[1][3]);
		rooms[0][3].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[1][3].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("K08L4C", UNLOCKED), rooms[1][3], rooms[2][3]);
		rooms[1][3].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[2][3].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KOME75", UNLOCKED), rooms[2][3], rooms[3][3]);
		rooms[2][3].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[3][3].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KPQXPS", UNLOCKED), rooms[3][3], rooms[4][3]);
		rooms[3][3].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[4][3].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("K3ZH4R", UNLOCKED), rooms[4][3], rooms[5][3]);
		rooms[4][3].addProperty(EAST_DOOR_NAME, EAST_DOOR_DESC, door);
		rooms[5][3].addProperty(WEST_DOOR_NAME, WEST_DOOR_DESC, door);

		// Vertical

		door = new BaseDoor(OPEN, new Lock("KEFVH2", UNLOCKED), rooms[2][3], rooms[2][4]);
		rooms[2][3].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[2][4].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);

		door = new BaseDoor(OPEN, new Lock("KVW42D", UNLOCKED), rooms[3][3], rooms[3][4]);
		rooms[3][3].addProperty(NORTH_DOOR_NAME, NORTH_DOOR_DESC, door);
		rooms[3][4].addProperty(SOUTH_DOOR_NAME, SOUTH_DOOR_DESC, door);
	}
}
