package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.*;
import textadventure.rooms.Room;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Consumer;

public class ConsoleUserInterface implements UserInterface
{

	/**
	 * The {@link Scanner} to use for console input.
	 */
	private Scanner scanner;

	/**
	 * The {@link OutputStream} to use for console output.
	 */
	private PrintWriter printer;

	/**
	 * Creates a new {@link ConsoleUserInterface}.
	 *
	 * @param scanner The {@link Scanner} to use for console input.
	 * @param printer The {@link PrintWriter} to use for console output.
	 */
	public ConsoleUserInterface(Scanner scanner, PrintWriter printer)
	{
		this.scanner = scanner;
		this.printer = printer;
	}

	@Override
	public void write(String message)
	{
		printer.println(message);
	}

	/**
	 * Called when the {@link Game} is constructed.
	 *
	 * @param game The newly constructed {@link Game}.
	 */
	@Override
	public void onInit(Game game)
	{

	}

	/**
	 * Called when a new {@link Player} joins the {@link Game}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The newly joined {@link Player}.
	 */
	@Override
	public void onPlayer(Game game, Player player)
	{

	}

	/**
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onGameStart(Game game)
	{
		printer.println(game.getMaze().getStartingRoom().getStartingMessage());
	}

	/**
	 * Called when the {@link Game} ends.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onGameEnd(Game game)
	{
		printer.println(game.getMaze().getEndingRoom().getEndingMessage());
	}

	/**
	 * Called when the {@link Game} is prompted to quit.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onQuit(Game game)
	{

	}

	/**
	 * Called when the turn rotates.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	@Override
	public void onTurnStart(Game game, Player player)
	{

	}

	/**
	 * Called when a {@link Player} loses their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	@Override
	public void onTurnEnd(Game game, Player player)
	{

	}

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link UserInterface}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param callback The callback to respond with.
	 */
	@Override
	public void onActionRequest(Game game, Player player, Consumer<Action> callback)
	{
		Room currentRoom = player.getCharacter().getCurrentLocation();

		while (true) {
			printer.println("What would you like to do?");
			String[] sections = scanner.nextLine().split(" ");

			if (sections.length < 2) {
				printer.println("To interact with door write:\nnorthern/eastern/western/southern + _door. For example: northern_door open.\n"
				+ "Door commands: open, close, enter, inspect.");
				continue;
			}

			Property property = currentRoom.getProperty(sections[0]);


			for (int i = 1; i < sections.length - 1; i++) {
				if (property instanceof PropertyContainer) {
					PropertyContainer container = (PropertyContainer) property;
					property = container.getProperty(sections[i]);
				}
			}

			if (property == null) {
				throw new Error();
			}

			Action action = property.getAction(sections[sections.length - 1]);
			callback.accept(action);
			break;
		}
	}

	/**
	 * Called when a {@link Player} responds to a request for {@link Action}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who responded to a request for {@link Action}.
	 * @param action The chosen {@link Action}.
	 */
	@Override
	public void onActionResponse(Game game, Player player, Action action)
	{

	}

	/**
	 * Requests a {@link Select} option {@link UserInterface}.
	 *
	 * @param select   The {@link Select}.
	 * @param player   The {@link Player} selecting.
	 * @param callback The callback to use to return the selected element.
	 */
	@Override
	public <T> void select(Select<T> select, Player player, SelectCallback<T> callback)
	{
		printer.println("Select one of the following options.");
		ImmutableMap<String, ? extends T> options = select.getOptions();
		for (Map.Entry<String, ? extends T> entry : options.entrySet()) {
			printer.println(entry.getKey() + ": " + entry.getValue());
		}

		String choice = scanner.nextLine();
		if (!options.containsKey(choice)) {
			printer.println("Unknown select.");
			select(select, player, callback);
			return;
		}

		try {
			callback.<T>call(options.get(choice));
		} catch (SelectException e) {
			printer.println("SelectException.");
		}
	}
}
