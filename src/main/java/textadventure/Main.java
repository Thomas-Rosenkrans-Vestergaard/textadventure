package textadventure;

import textadventure.doors.DefaultDoor;
import textadventure.doors.Door;
import textadventure.items.backpack.Backpack;
import textadventure.items.backpack.BaseBackpack;
import textadventure.lock.Key;
import textadventure.lock.Lock;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.EndingRoom;
import textadventure.rooms.StartingRoom;
import textadventure.ui.ConsoleUI;
import textadventure.ui.UI;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException
	{
		UI       gameInterface = new ConsoleUI();
		Maze     maze          = generateMaze();
		Game     game          = new Game(gameInterface, maze, 5);
		Backpack backpack      = new BaseBackpack(10);
		backpack.addItem(0, new Key("NKATS3"));
		game.addPlayer(new HumanPlayer(
				new BaseCharacter("George", backpack, maze.getStartingRoom(), 100, 100, 0,
								  0, 0, 0, 0, 0
				)
		));
		game.start();
	}

	public static Maze generateMaze() throws UnknownRoomException
	{
		// Create rooms
		StartingRoom startingRoom = new StartingRoom();
		EndingRoom   endingRoom   = new EndingRoom();
		BaseRoom     westRoom     = new BaseRoom("West room", "This is the west room");
		BaseRoom     centerRoom   = new BaseRoom("Center room", "This is the center room");
		BaseRoom     eastRoom     = new BaseRoom("East room", "This is the east room");

		// Starting center door.
		Lock startingCenterLock = new Lock("NKATS3", Lock.State.UNLOCKED);
		Door startingCenterDoor = new DefaultDoor(Door.State.OPEN, startingCenterLock, Direction.NORTH, startingRoom,
												  centerRoom
		);
		startingRoom.addProperty(startingCenterDoor);
		centerRoom.addProperty(startingCenterDoor);

		// Center west door.
		Lock centerWestLock = new Lock("HQ7QSW", Lock.State.UNLOCKED);
		Door centerWestDoor = new DefaultDoor(Door.State.OPEN, centerWestLock, Direction.WEST, centerRoom, westRoom);
		centerRoom.addProperty(centerWestDoor);
		westRoom.addProperty(centerWestDoor);

		// Center east door.
		Lock centerEastLock = new Lock("4DN9C6", Lock.State.UNLOCKED);
		Door centerEastDoor = new DefaultDoor(Door.State.CLOSED, centerEastLock, Direction.EAST, centerRoom, eastRoom);
		centerRoom.addProperty(centerEastDoor);
		eastRoom.addProperty(centerEastDoor);

		// Center ending door.
		Lock centerEndingLock = new Lock("IPT2QJ", Lock.State.UNLOCKED);
		Door centerEndingDoor = new DefaultDoor(Door.State.OPEN, centerEndingLock, Direction.NORTH, centerRoom,
												endingRoom
		);
		centerRoom.addProperty(centerEndingDoor);
		endingRoom.addProperty(centerEndingDoor);

		// Return the created maze
		return new Maze(startingRoom, endingRoom, westRoom, centerRoom, eastRoom);
	}
}
