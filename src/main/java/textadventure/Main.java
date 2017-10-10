package textadventure;

import textadventure.exception.UnknownPlayerException;
import textadventure.exception.UnknownRoomException;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException, UnknownPlayerException
	{
        StartingRoom firstRoom = new StartingRoom();
		GameInterface gameInterface = new ConsoleGameInterface();
		GameController gameController = new GameController(gameInterface, MazeFactory.generate(), new
				HumanPlayer("George", firstRoom, 100));

		gameController.start();
	}
}
