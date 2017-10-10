package textadventure;

import textadventure.exception.UnknownPlayerException;
import textadventure.exception.UnknownRoomException;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException, UnknownPlayerException
	{
		GameInterface gameInterface = new ConsoleGameInterface();
		GameController gameController = new GameController(gameInterface, MazeFactory.generate(), new
				HumanPlayer(100, "George"));

		gameController.start();
	}
}
