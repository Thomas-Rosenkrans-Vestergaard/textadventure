package textadventure;

import textadventure.exception.UnknownRoomException;

public class MazeFactory
{

	public static Maze generate() throws UnknownRoomException
	{

		// Create connection tracker
		RoomConnectionTracker connections = new RoomConnectionTracker();

		// Create rooms
		StartingRoom startingRoom = new StartingRoom();
		EndingRoom   endingRoom   = new EndingRoom();
		BaseRoom     westRoom     = new BaseRoom("West room", "This is the west room");
		BaseRoom     centerRoom   = new BaseRoom("Center room", "This is the center room");
		BaseRoom     eastRoom     = new BaseRoom("East room", "This is a east room");

		// Add rooms to connection tracker
		connections.addRoom(startingRoom);
		connections.addRoom(endingRoom);
		connections.addRoom(westRoom);
		connections.addRoom(centerRoom);
		connections.addRoom(eastRoom);

		// Add connections
		connections.addMutualConnection(centerRoom, westRoom, Direction.WEST);
		connections.addMutualConnection(centerRoom, eastRoom, Direction.EAST);
		connections.addMutualConnection(centerRoom, startingRoom, Direction.SOUTH);
		connections.addMutualConnection(centerRoom, endingRoom, Direction.NORTH);

		// Return the created maze
		return new Maze(connections, startingRoom, endingRoom);
	}
}
