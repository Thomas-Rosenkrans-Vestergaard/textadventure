package textadventure;

import textadventure.rooms.*;
import textadventure.rooms.features.doors.BaseDoor;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.lock.Lock;

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
		Lock startingCenterLock = new Lock(1, Lock.State.UNLOCKED);
		Door startingCenterDoor = new BaseDoor("Door from starting to center room.", startingCenterLock, Door.State
				.OPEN, startingRoom, centerRoom, Direction.NORTH
		);

		startingRoom.addFeature(startingCenterDoor);
		centerRoom.addFeature(startingCenterDoor);

		// Center west door.
		Lock centerWestLock = new Lock(2, Lock.State.UNLOCKED);
		Door centerWestDoor = new BaseDoor("Door from center to west room.", centerWestLock, Door.State.OPEN,
										   centerRoom, westRoom, Direction.WEST
		);

		centerRoom.addFeature(centerWestDoor);
		westRoom.addFeature(centerWestDoor);

		// Center east door.
		Lock centerEastLock = new Lock(2, Lock.State.UNLOCKED);
		Door centerEastDoor = new BaseDoor("Door from center to east room.", centerEastLock, Door.State.CLOSED,
										   centerRoom, eastRoom, Direction.EAST
		);

		centerRoom.addFeature(centerEastDoor);
		eastRoom.addFeature(centerEastDoor);

		// Center ending door.
		Lock centerEndingLock = new Lock(2, Lock.State.UNLOCKED);
		Door centerEndingDoor = new BaseDoor("Door from center to ending room.", centerEndingLock, Door.State.OPEN,
											 centerRoom, endingRoom, Direction.NORTH
		);

		centerRoom.addFeature(centerEndingDoor);
		endingRoom.addFeature(centerEndingDoor);

		// Return the created maze
		return new Maze(startingRoom, endingRoom, westRoom, centerRoom, eastRoom);
	}
}
