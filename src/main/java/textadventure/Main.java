package textadventure;

import textadventure.ui.ConsoleUI_;
import textadventure.ui.UI;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException
	{
		UI   gameInterface = new ConsoleUI_();
		Maze maze          = MazeFactory.generate();
		Game game          = new Game(gameInterface, MazeFactory.generate(), 5);
		game.addPlayer(new HumanPlayer("George", maze.getStartingRoom()));
		game.start();
	}
}
