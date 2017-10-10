package textadventure;

import textadventure.console.ConsoleGameInterface;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException
	{
		GameInterface gameInterface = new ConsoleGameInterface();
		Maze          maze          = MazeFactory.generate();
		GameController gameController = new GameController(gameInterface, MazeFactory.generate(), new
				HumanPlayer("George", maze.getStartingRoom()));

		gameController.start();
	}
}
