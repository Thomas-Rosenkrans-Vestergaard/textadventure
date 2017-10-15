package textadventure;

import textadventure.items.inventory.Backpack;
import textadventure.lock.Key;
import textadventure.ui.ConsoleUserInterface;
import textadventure.ui.UserInterface;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args) throws UnknownRoomException
	{
		UserInterface gameInterface = new ConsoleUserInterface(new Scanner(System.in), new PrintWriter(System.out, true));
		MazeCreator mazeCreator = new MazeCreator();
		Maze maze = mazeCreator.generate();
		Game game = new Game(gameInterface, maze, 5);
		Backpack backpack = new Backpack(10);
		backpack.addItem(0, new Key("NKATS3"));
		game.addPlayer(new HumanPlayer(
				new BaseCharacter("George", backpack, maze.getStartingRoom(), 100, 100, 0,
						0, 0, 0, 0, 0, 0
				)
		));
		game.start();
	}
}
