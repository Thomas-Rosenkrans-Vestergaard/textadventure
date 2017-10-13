package textadventure;

import textadventure.items.BaseInventory;
import textadventure.items.Inventory;
import textadventure.lock.Key;
import textadventure.ui.ConsoleUI;
import textadventure.ui.UI;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException
	{
		UI        gameInterface = new ConsoleUI();
		Maze      maze          = MazeFactory.generate();
		Game      game          = new Game(gameInterface, MazeFactory.generate(), 5);
		Inventory inventory     = new BaseInventory(10);
		inventory.addItem(0, new Key("NKATS3"));
		game.addPlayer(new HumanPlayer(
				new BaseCharacter("George", inventory, maze.getStartingRoom(), 100, 100, 0,
								  0, 0, 0, 0, 0
				)
		));
		game.start();
	}
}
