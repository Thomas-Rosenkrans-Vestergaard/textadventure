package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.*;
import textadventure.openable.Openable;
import textadventure.openable.OpenableException;
import textadventure.openable.doors.Door;
import textadventure.lock.Lock;
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
				printer.println("Less than two sections.");
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

			printer.println(action);

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

	/**
	 * Called when the provided {@link Player} closes the provided {@link Door}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param openable The {@link Door} that was closed.
	 * @param player   The {@link Player} who closed the {@link Door}.
	 */
	@Override
	public void onOpen(Game game, Openable openable, Player player)
	{
		String message = String.format("%s closed the door.", player.getCharacter().getName());

		printer.println(message);
	}

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was inspected.
	 * @param player
	 */
	@Override
	public void onDoorInspect(Game game, Door door, Player player)
	{
		String message = String.format("%s inspected the door. While inspecting the door %s discovers that the door " +
						"is %s.", player.getCharacter().getName(), player.getCharacter().getName(),
				door.getOpenableState().name().toLowerCase()
		);

		printer.println(message);
	}

	/**
	 * Event when the provided {@link Player} closes an {@link Openable} object.
	 *
	 * @param game     The {@link Game} instance.
	 * @param openable The {@link Openable} object.
	 * @param player   The {@link Player} who closed the {@link Openable} object.
	 */
	@Override
	public void onClose(Game game, Openable openable, Player player)
	{

	}

	/**
	 * Event when the provided {@link Player} encounters an {@link Exception} while using a {@link Openable} object.
	 *
	 * @param game   The {@link Game} instance.
	 * @param e      The {@link OpenableException} that was thrown.
	 * @param player The {@link Player} who encountered the {@link OpenableException}.
	 */
	@Override
	public void onOpenException(Game game, OpenableException e, Player player)
	{

	}

	/**
	 * Event when the provided {@link Player} encounters an {@link Exception} while using a {@link Openable} object.
	 *
	 * @param game   The {@link Game} instance.
	 * @param e      The {@link OpenableException} that was thrown.
	 * @param player The {@link Player} who encountered the {@link OpenableException}.
	 */
	@Override
	public void onCloseException(Game game, OpenableException e, Player player)
	{

	}

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param lock   The {@link Lock}.
	 * @param player The {@link Player} who inspected the {@link Lock}.
	 */
	@Override
	public void onLockInspect(Game game, Lock lock, Player player)
	{
		String message = String.format("%s inspected the lock. While inspecting the lock %s discovers that the lock " +
						"is %s. Written on the lock is the code %s.", player.getCharacter().getName(),
				player.getCharacter().getName(), lock.getState().name().toLowerCase(),
				lock.getCode()
		);

		printer.println(message);
	}

	/**
	 * Called when the provided {@link Player} locks the provided {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param lock   The {@link Lock}.
	 * @param player The {@link Player} who locked the lock.
	 */
	@Override
	public void onLockLock(Game game, Lock lock, Player player)
	{
		printer.println("lock");
	}

	/**
	 * Called when the provided {@link Player} unlocks the provided {@link Lock}.
	 *
	 * @param game
	 * @param lock
	 * @param player
	 */
	@Override
	public void onLockUnlock(Game game, Lock lock, Player player)
	{
		printer.println("unlock");
	}

	@Override
	public void onLockAlreadyLocked(Game game, Lock lock, Player player)
	{
		printer.println("already locked");
	}

	@Override
	public void onLockAlreadyUnlocked(Game game, Lock lock, Player player)
	{
		printer.println("already unlocked");
	}
}
