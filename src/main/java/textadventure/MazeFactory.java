package textadventure;

import textadventure.rooms.BaseRoom;
import textadventure.rooms.EndingRoom;
import textadventure.rooms.RoomController;
import textadventure.rooms.StartingRoom;
import textadventure.rooms.features.doors.BaseDoor;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.doors.Lock;

public class MazeFactory
{

	public static Maze generate() throws UnknownRoomException
	{

		// Create connection tracker
		RoomController connections = new RoomController();

		// Create rooms
		StartingRoom startingRoom = new StartingRoom();
		EndingRoom   endingRoom   = new EndingRoom();
		BaseRoom     westRoom     = new BaseRoom("West room", "This is the west room");
		BaseRoom centerRoom = new BaseRoom(
				"Center room",
				"This is the center room",
				new BaseDoor("center " + "door", new Lock(10, Lock.State.LOCKED), Door.State.CLOSED)
		);
		BaseRoom eastRoom = new BaseRoom("East room", "This is the east room");

		// Add rooms to connection tracker
		connections.addRoom(startingRoom);
		connections.addRoom(endingRoom);
		connections.addRoom(westRoom);
		connections.addRoom(centerRoom);
		connections.addRoom(eastRoom);

		// Add connections
		connections.addConnection(centerRoom, westRoom, Direction.WEST);
		connections.addConnection(centerRoom, eastRoom, Direction.EAST);
		connections.addConnection(centerRoom, startingRoom, Direction.SOUTH);
		connections.addConnection(centerRoom, endingRoom, Direction.NORTH);

		// Return the created maze
		return new Maze(connections, startingRoom, endingRoom);
	}
}
