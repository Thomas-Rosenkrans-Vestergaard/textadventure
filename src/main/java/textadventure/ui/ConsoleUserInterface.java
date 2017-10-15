package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.*;
import textadventure.doors.*;
import textadventure.items.Item;
import textadventure.items.inventory.Inventory;
import textadventure.lock.AlreadyLockedException;
import textadventure.lock.AlreadyUnlockedException;
import textadventure.lock.Lock;
import textadventure.rooms.Room;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

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
		String message = "The game was initialized.";

		printer.println(message);
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
		String message = String.format("%s connected to the game.", player.getCharacter().getName());

		printer.println(message);
	}

	/**
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onGameStart(Game game)
	{
		String message = game.getMaze().getStartingRoom().getStartingMessage();

		printer.println(message);
	}

	/**
	 * Called when the {@link Game} ends.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onGameEnd(Game game)
	{
		String message = game.getMaze().getEndingRoom().getEndingMessage();

		printer.println(message);
	}

	/**
	 * Called when the {@link Game} is prompted to quit.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onQuit(Game game)
	{
		String message = "The game was prompted to quit.";

		printer.println(message);
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
	 * @param response The {@link ActionResponse} to respond with.
	 */
	@Override public void onActionRequest(Game game, Player player, ActionResponse response)
	{
		Room currentRoom = player.getCharacter().getCurrentLocation();

		while (true) {

			printer.println("Please enter your next action.");
			String input = scanner.nextLine().trim();

			if (input.startsWith("help")) {
				showHelp();
				return;
			}

			if (input.startsWith("quit")) {
				return;
			}

			if (input.startsWith("properties")) {
				printer.println("The properties available is this room:");
				for (Map.Entry<String, Property> entry : player.getCharacter().getCurrentLocation().getProperties().entrySet()) {
					printer.println(String.format("- %s", entry.getKey()));
				}
				continue;
			}

			if (input.startsWith("actions")) {
				throw new UnsupportedOperationException();
			}

			String[] sections = input.split(" ");
			if (sections.length < 2) {
				printer.println("Your action must contain at least one property.");
				printer.println("To interact with door write:\nnorthern/eastern/western/southern + _door. For example: northern_door open.\n"
						+ "Door commands: open, close, enter, inspect.\nTo interact with door lock, simply add 'lock' after 'northern_door'.\n" +
						"Door commands: inspect, lock, unlock.\n" +
						"To lock/unlock a door you need the code.\n" +
						"When you got the code, write northern_door lock lock, and afterwards enter the code.");
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
				printer.println("Unknown property.");
				continue;
			}

			Action action = property.getAction(sections[sections.length - 1]);
			try {
				response.respond(action);
				break;
			} catch (DoorAlreadyClosedException e) {
				printer.println("The door is already closed.");
			} catch (DoorAlreadyOpenException e) {
				printer.println("The door is already open.");
			} catch (DoorClosedException e) {
				printer.println("You cannot do that since the door is closed.");
			} catch (DoorLockedException e) {
				printer.println("You cannot do that since the door is locked.");
			} catch (AlreadyLockedException e) {
				printer.println("The lock is already locked.");
			} catch (AlreadyUnlockedException e) {
				printer.println("The lock is already unlocked.");
			} catch (ActionException e) {
				printer.println("ActionException");
				e.printStackTrace(printer);
			}
		}
	}

	/**
	 * Requests a {@link Select} option {@link UserInterface}.
	 *
	 * @param message  The message to display before the {@link Player} can select.
	 * @param select   The {@link Select}.
	 * @param player   The {@link Player} selecting.
	 * @param callback The callback to use to return the selected element.
	 */
	@Override
	public <T extends Option> void select(String message, Select<T> select, Player player, SelectResponse<T> callback)
	{
		printer.println(message);
		ImmutableMap<Integer, ? extends T> options = select.getOptions();
		printer.println("------------------------------------------------------------------------------------------");
		printer.println("| ID  | Option name          | Option description                                        |");
		printer.println("------------------------------------------------------------------------------------------");
		for (Map.Entry<Integer, ? extends T> entry : options.entrySet()) {
			printer.println(String.format("| %-3d | %-20s | %-40s |", entry.getKey(), entry.getValue().getOptionName(),
					entry.getValue().getOptionDescription()));
		}
		printer.println("------------------------------------------------------------------------------------------");
		int choice;
		while (true) {
			try {
				choice = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				printer.println("Selection must be a number.");
			}
		}

		if (!options.containsKey(choice)) {
			printer.println("Unknown selected value.");
			select(message, select, player, callback);
			return;
		}

		try {
			callback.<T>select(options.get(choice));
		} catch (ActionException e) {
			System.out.println("SelectException");
			e.printStackTrace();
		}
	}

	/**
	 * Shows the provided {@link Inventory} in the {@link UserInterface}.
	 *
	 * @param inventory The {@link Inventory} to show.
	 */
	@Override public void showInventory(String header, Inventory inventory)
	{
		ImmutableMap<Integer, Item> items = inventory.getItems();
		StringBuilder builder = new StringBuilder();
		builder.append("------------------------------------------------------------------------------------------\n");
		builder.append("| #   | Item name            | Item description                                          |\n");
		builder.append("------------------------------------------------------------------------------------------\n");
		for (Map.Entry<Integer, Item> entry : items.entrySet()) {
			builder.append(String.format("| %-3d | %-20s | %-57s |\n", entry.getKey(), entry.getValue().getItemName(),
					entry.getValue().getItemDescription()));
		}
		builder.append("------------------------------------------------------------------------------------------");

		printer.println(header);
		printer.println(builder);
	}

	@Override public void onDoorOpen(Game game, Player player, Door door)
	{
		printer.println("You successfully opened the door.");
	}

	@Override public void onDoorClose(Game game, Player player, Door door)
	{
		printer.println("You successfully closed the door.");
	}

	@Override public void onDoorEnter(Game game, Player player, Door door, Room room)
	{
		printer.println("You successfully entered the door.");
		printer.println(room.getRoomDescription());
	}

	@Override public void onDoorInspect(Game game, Player player, Door door)
	{
		String message = String.format("You successfully inspect the door, learning that the door is %s.",
				door.getState().name().toLowerCase());

		printer.println(message);
	}

	@Override public void onLockLock(Game game, Player player, Lock lock)
	{
		printer.println("You successfully locked the lock using the provided key.");
	}

	@Override public void onLockUnlock(Game game, Player player, Lock lock)
	{
		printer.println("You successfully unlocked the lock using the provided key.");
	}

	@Override public void onLockInspect(Game game, Player player, Lock lock)
	{
		String message = String.format("You inspect the lock learning that the lock is %s.", lock.getState().name()
				.toLowerCase());

		printer.println(message);
	}

	private void showHelp()
	{
		printer.println("You interact with the room around you using properties and actions.\nProperties are objects" +
				" that you can perform actions upon. Properties can be nested inside other properties.\nActions " +
				"cannot be nested inside other actions.\n");

		printer.println("\tnorthern_door lock unlock\n");

		printer.println("In the above command you access the property 'northern_door'.\nThen you access the property" +
				" 'lock' on the 'northern_door'. Lastly then perform the 'unlock' action on the 'lock'.\n");

		printer.println("To see the a");
	}
}
