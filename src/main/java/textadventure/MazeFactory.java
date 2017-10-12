package textadventure;

import textadventure.rooms.*;
import textadventure.doors.DefaultDoor;
import textadventure.doors.Door;
import textadventure.lock.Lock;

public class MazeFactory
{

	public static Maze generate() throws UnknownRoomException
	{
		// Create rooms
		StartingRoom startingRoom = new StartingRoom();
		EndingRoom   endingRoom   = new EndingRoom();
		BaseRoom     westRoom     = new BaseRoom("West room", "This is the west room");
		BaseRoom     centerRoom   = new BaseRoom("Center room", "This is the center room");
		BaseRoom     eastRoom     = new BaseRoom("East room", "This is the east room");

		// Starting center door.
		Lock startingCenterLock = new Lock("1", Lock.State.LOCKED);
		Door startingCenterDoor = new DefaultDoor(Door.State.OPEN, startingCenterLock, Direction.NORTH, startingRoom,
												  centerRoom
		);
		startingRoom.addProperty(startingCenterDoor);
		centerRoom.addProperty(startingCenterDoor);

		// Center west door.
		Lock centerWestLock = new Lock("2", Lock.State.UNLOCKED);
		Door centerWestDoor = new DefaultDoor(Door.State.OPEN, centerWestLock, Direction.WEST, centerRoom, westRoom);
		centerRoom.addProperty(centerWestDoor);
		westRoom.addProperty(centerWestDoor);

		// Center east door.
		Lock centerEastLock = new Lock("3", Lock.State.UNLOCKED);
		Door centerEastDoor = new DefaultDoor(Door.State.CLOSED, centerEastLock, Direction.EAST, centerRoom, eastRoom);
		centerRoom.addProperty(centerEastDoor);
		eastRoom.addProperty(centerEastDoor);

		// Center ending door.
		Lock centerEndingLock = new Lock("4", Lock.State.UNLOCKED);
		Door centerEndingDoor = new DefaultDoor(Door.State.OPEN, centerEndingLock, Direction.NORTH, centerRoom,
												endingRoom
		);
		centerRoom.addProperty(centerEndingDoor);
		endingRoom.addProperty(centerEndingDoor);

		// Return the created maze
		return new Maze(startingRoom, endingRoom, westRoom, centerRoom, eastRoom);
	}
}
