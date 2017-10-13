package textadventure;

import textadventure.openable.doors.DefaultDoor;
import textadventure.openable.doors.Door;
import textadventure.items.inventory.Inventory;
import textadventure.items.inventory.BaseInventory;
import textadventure.lock.Key;
import textadventure.lock.Lock;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.EndingRoom;
import textadventure.rooms.StartingRoom;
import textadventure.ui.ConsoleUserInterface;
import textadventure.ui.UserInterface;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException
	{
		UserInterface gameInterface = new ConsoleUserInterface(new Scanner(System.in), new PrintWriter(System.out, true));
		Maze maze = generateMaze();
		Game game = new Game(gameInterface, maze, 5);
		Inventory inventory = new BaseInventory(10);
		inventory.addItem(0, new Key("NKATS3"));
		game.addPlayer(new HumanPlayer(
				new BaseCharacter("George", inventory, maze.getStartingRoom(), 100, 100, 0,
						0, 0, 0, 0, 0
				)
		));
		game.start();
	}

	public static Maze generateMaze() throws UnknownRoomException
	{
		// Create rooms
		StartingRoom startingRoom = new StartingRoom();
		EndingRoom endingRoom = new EndingRoom();
		BaseRoom westRoom = new BaseRoom("West room", "This is the west room");
		BaseRoom centerRoom = new BaseRoom("Center room", "This is the center room");
		BaseRoom eastRoom = new BaseRoom("East room", "This is the east room");

		// Starting center door.
		Lock startingCenterLock = new Lock("NKATS3", Lock.State.UNLOCKED);
		Door startingCenterDoor = new DefaultDoor(Door.State.OPEN, startingCenterLock, Direction.NORTH, startingRoom,
				centerRoom
		);
		startingRoom.addProperty("northern_door", startingCenterDoor);
		centerRoom.addProperty("southern_door", startingCenterDoor);
		centerRoom.addRoomFeature(startingCenterDoor);
		startingRoom.addRoomFeature(startingCenterDoor);

		// Center west door.
		Lock centerWestLock = new Lock("HQ7QSW", Lock.State.UNLOCKED);
		Door centerWestDoor = new DefaultDoor(Door.State.OPEN, centerWestLock, Direction.WEST, centerRoom, westRoom);
		centerRoom.addProperty("western_door", centerWestDoor);
		westRoom.addProperty("eastern_door", centerWestDoor);
		centerRoom.addRoomFeature(centerWestDoor);
		westRoom.addRoomFeature(centerWestDoor);

		// Center east door.
		Lock centerEastLock = new Lock("4DN9C6", Lock.State.UNLOCKED);
		Door centerEastDoor = new DefaultDoor(Door.State.CLOSED, centerEastLock, Direction.EAST, centerRoom, eastRoom);
		centerRoom.addProperty("eastern_door", centerEastDoor);
		eastRoom.addProperty("western_door", centerEastDoor);
		centerRoom.addRoomFeature(centerEastDoor);
		eastRoom.addRoomFeature(centerEastDoor);

		// Center ending door.
		Lock centerEndingLock = new Lock("IPT2QJ", Lock.State.UNLOCKED);
		Door centerEndingDoor = new DefaultDoor(Door.State.OPEN, centerEndingLock, Direction.NORTH, centerRoom,
				endingRoom
		);
		centerRoom.addProperty("northern_door", centerEndingDoor);
		endingRoom.addProperty("southern_door", centerEndingDoor);
		centerRoom.addRoomFeature(centerEndingDoor);
		endingRoom.addRoomFeature(centerEndingDoor);

		// Return the created maze
		return new Maze(startingRoom, endingRoom, westRoom, centerRoom, eastRoom);
	}
}
