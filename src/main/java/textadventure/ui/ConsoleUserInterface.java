package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.*;
import textadventure.Character;
import textadventure.actions.Action;
import textadventure.actions.ActionResponse;
import textadventure.doors.*;
import textadventure.items.Item;
import textadventure.items.Inventory;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.chest.*;
import textadventure.lock.*;
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
	public void onPlayerJoin(Game game, Player player)
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
		showInstructions();
		printer.println("#");
		printer.println("# Story");
		printer.println("#");
		printer.println();
		printer.println(game.getMaze().getStartingRoom().getStartingMessage());
		printer.println(game.getMaze().getStartingRoom().getRoomDescription());
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
		System.exit(0);
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

			if (input.startsWith("instructions")) {
				showInstructions();
				continue;
			}

			if (input.startsWith("quit")) {
				System.exit(0);
			}

			if (input.startsWith("properties")) {
				showProperties(player.getCharacter());
				continue;
			}

			if (input.startsWith("actions")) {
				showActions(game);
				continue;
			}

			if (input.startsWith("commands")) {
				showCommands();
				continue;
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

			ImmutableMap<String, Property> properties = player.getCharacter().getProperties();
			Property property = properties.get(sections[0]);
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
			if (action == null) {
				printer.println("Unknown action " + sections[sections.length - 1] + ".");
				continue;
			}

			response.respond(action);
		}
	}

	/**
	 * Event when a {@link Player} performs the {@link OpenDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link OpenDoorAction}.
	 * @param action The {@link OpenDoorAction} instance.
	 */
	@Override public void onDoorOpen(Game game, Player player, OpenDoorAction action)
	{
		OpenDoorAction.Outcome outcome = action.getOutcome();

		if (outcome == OpenDoorAction.Outcome.SUCCESS) {
			printer.println("You attempted and succeeded in opening the door.");
			return;
		}

		if (outcome == OpenDoorAction.Outcome.ALREADY_OPEN) {
			printer.println("You attempted to open the door, even though the door is already open.");
			printer.println("You start to question your sanity.");
			return;
		}

		if (outcome == OpenDoorAction.Outcome.LOCKED) {
			printer.println("You attempted to open the door, but discover that the door is locked.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link CloseDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link CloseDoorAction}.
	 * @param action The {@link CloseDoorAction} instance.
	 */
	@Override public void onDoorClose(Game game, Player player, CloseDoorAction action)
	{
		CloseDoorAction.Outcome outcome = action.getOutcome();

		if (outcome == CloseDoorAction.Outcome.SUCCESS) {
			printer.println("You attempted and succeeded in closing the door.");
			return;
		}

		if (outcome == CloseDoorAction.Outcome.ALREADY_CLOSED) {
			printer.println("You attempted to close the door, even though the door is already closed.");
			printer.println("You start to question your sanity.");
			return;
		}

		if (outcome == CloseDoorAction.Outcome.LOCKED) {
			printer.println("You attempted to close the door, but discover that the door is locked.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link UseDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link UseDoorAction}.
	 * @param action The {@link UseDoorAction} instance.
	 */
	@Override public void onDoorUse(Game game, Player player, UseDoorAction action)
	{
		UseDoorAction.Outcome outcome = action.getOutcome();

		if (outcome == UseDoorAction.Outcome.SUCCESS) {
			printer.println("You successfully entered a new room using the door.");
			printer.println(player.getCharacter().getCurrentLocation().getRoomDescription());
			return;
		}

		if (outcome == UseDoorAction.Outcome.CLOSED) {
			printer.println("You attempted the use the door, but discover that the door is closed.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link InspectDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectDoorAction}.
	 * @param action The {@link InspectDoorAction} instance.
	 */
	@Override public void onDoorInspect(Game game, Player player, InspectDoorAction action)
	{
		InspectDoorAction.Outcome outcome = action.getOutcome();
		Door                      door    = action.getDoor();

		if (outcome == InspectDoorAction.Outcome.SUCCESS) {
			printer.println(String.format("You inspect the door, learning that the door is %s.", door.getState().name().toLowerCase()));
			return;
		}

		throw new IllegalStateException();
	}

	/**
	 * Event when a {@link Player} performs the {@link LockLockAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link LockLockAction}.
	 * @param action The {@link LockLockAction} instance.
	 */
	@Override public void onLockLock(Game game, Player player, LockLockAction action)
	{
		LockLockAction.Outcome outcome = action.getOutcome();

		if (outcome == LockLockAction.Outcome.SUCCESS) {
			printer.println("You successfully lock the lock using the provided key.");
			return;
		}

		if (outcome == LockLockAction.Outcome.ALREADY_LOCKED) {
			printer.println("You attempted to lock the lock, even though the lock is already locked.");
			printer.println("You start to question your sanity.");
			return;
		}

		if (outcome == LockLockAction.Outcome.SELECTED_NOT_KEY) {
			printer.println("The item you attempted to lock the lock with is not a key.");
			printer.println("You start to questing your sanity.");
			return;
		}

		if (outcome == LockLockAction.Outcome.INCORRECT_KEY) {
			printer.println("You attempt to turn the lock, but discover that you have the wrong key.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link UnlockLockAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link UnlockLockAction}.
	 * @param action The {@link UnlockLockAction} instance.
	 */
	@Override public void onLockUnlock(Game game, Player player, UnlockLockAction action)
	{
		UnlockLockAction.Outcome outcome = action.getOutcome();

		if (outcome == UnlockLockAction.Outcome.SUCCESS) {
			printer.println("You successfully unlock the lock using the provided key.");
			return;
		}

		if (outcome == UnlockLockAction.Outcome.ALREADY_UNLOCKED) {
			printer.println("You attempted to unlock the lock, even though the lock is already unlocked.");
			printer.println("You start to question your sanity.");
			return;
		}

		if (outcome == UnlockLockAction.Outcome.SELECTED_NOT_KEY) {
			printer.println("The item you attempted to unlock the lock with is not a key.");
			printer.println("You start to questing your sanity.");
			return;
		}

		if (outcome == UnlockLockAction.Outcome.INCORRECT_KEY) {
			printer.println("You attempt to turn the lock, but discover that you have the wrong key.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link InspectLockAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectLockAction}.
	 * @param action The {@link InspectLockAction} instance.
	 */
	@Override public void onLockInspect(Game game, Player player, InspectLockAction action)
	{
		InspectLockAction.Outcome outcome = action.getOutcome();
		Lock                      lock    = action.getLock();

		if (outcome == InspectLockAction.Outcome.SUCCESS) {
			printer.println(
					String.format("You inspect the lock learning that the lock is %s. You notice that '%s' is written on the lock.",
							lock.getState().name().toLowerCase(),
							lock.getCode()
					)
			);
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link OpenChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link OpenChestAction}.
	 * @param action The {@link OpenChestAction} instance.
	 */
	@Override public void onChestOpen(Game game, Player player, OpenChestAction action)
	{
		OpenChestAction.Outcome outcome = action.getOutcome();

		if (outcome == OpenChestAction.Outcome.SUCCESS) {
			printer.println("You successfully opened the chest.");
			return;
		}

		if (outcome == OpenChestAction.Outcome.ALREADY_OPEN) {
			printer.println("You attempted to open the chest, even though the chest is already open.");
			printer.println("You start to question your sanity.");
			return;
		}

		if (outcome == OpenChestAction.Outcome.LOCKED) {
			printer.println("You attempt to open the chest, but discover that the chest is locked.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link CloseChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link CloseChestAction}.
	 * @param action The {@link CloseChestAction} instance.
	 */
	@Override public void onChestClose(Game game, Player player, CloseChestAction action)
	{
		CloseChestAction.Outcome outcome = action.getOutcome();

		if (outcome == CloseChestAction.Outcome.SUCCESS) {
			printer.println("You successfully closed the chest.");
			return;
		}

		if (outcome == CloseChestAction.Outcome.ALREADY_CLOSED) {
			printer.println("You attempted to close the chest, even though the chest is already closed.");
			printer.println("You start to question your sanity.");
			return;
		}

		if (outcome == CloseChestAction.Outcome.LOCKED) {
			printer.println("You attempt to close the chest, but discover that the chest is locked.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link InspectChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectChestAction}.
	 * @param action The {@link InspectChestAction} instance.
	 */
	@Override public void onChestInspect(Game game, Player player, InspectChestAction action)
	{
		InspectChestAction.Outcome outcome = action.getOutcome();
		Chest                      chest   = action.getChest();

		if (outcome == InspectChestAction.Outcome.SUCCESS) {
			ImmutableMap<Integer, Item> items = chest.getItems();
			printer.println("-----------------------------------------------------------------------------------------");
			printer.println("| Slot | Item name            | Item description                                        |");
			printer.println("-----------------------------------------------------------------------------------------");
			items.entrySet().forEach(entry -> {
				printer.println(String.format("| %-4d | %-20s | %-40s |",
						entry.getKey(),
						entry.getValue().getItemName(),
						entry.getValue().getItemDescription()));
			});
			printer.println("------------------------------------------------------------------------------------------");
			return;
		}

		if (outcome == InspectChestAction.Outcome.CLOSED) {
			printer.println("You attempt to inspect the chest, but discover that the chest is closed.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link TakeChestItemAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link TakeChestItemAction}.
	 * @param action The {@link TakeChestItemAction} instance.
	 */
	@Override public void onChestTake(Game game, Player player, TakeChestItemAction action)
	{
		TakeChestItemAction.Outcome outcome = action.getOutcome();

		if (outcome == TakeChestItemAction.Outcome.SUCCESS) {
			printer.println("You succeeded in taking item " + action.getItem().getItemName() + " from the chest.");
			return;
		}

		if (outcome == TakeChestItemAction.Outcome.CLOSED) {
			printer.println("You cannot take items from a closed chest.");
			return;
		}

		if (outcome == TakeChestItemAction.Outcome.BACKPACK_FULL) {
			printer.println("You attempt to take the item, but you cannot fit it in your backpack.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link InspectBackpackAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectBackpackAction}.
	 * @param action The {@link InspectBackpackAction} instance.
	 */
	@Override public void onBackpackInspect(Game game, Player player, InspectBackpackAction action)
	{
		InspectBackpackAction.Outcome outcome = action.getOutcome();

		if (outcome == InspectBackpackAction.Outcome.SUCCESS) {
			ImmutableMap<Integer, Item> items = action.getBackpack().getItems();
			printer.println("-----------------------------------------------------------------------------------------");
			printer.println("| Slot | Item name            | Item description                                        |");
			printer.println("-----------------------------------------------------------------------------------------");
			items.entrySet().forEach(entry -> {
				printer.println(String.format("| %-4d | %-20s | %-40s |",
						entry.getKey(),
						entry.getValue().getItemName(),
						entry.getValue().getItemDescription()));
			});
			printer.println("------------------------------------------------------------------------------------------");
			return;
		}

		throw new UnsupportedOperationException();
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
		printer.println("'quit' to exit select.");
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
				String input = scanner.nextLine();

				if (input.equals("quit"))
					return;

				choice = Integer.parseInt(input);
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

		callback.<T>select(options.get(choice));
	}

	/**
	 * Shows the provided {@link Inventory} in the {@link UserInterface}.
	 *
	 * @param inventory The {@link Inventory} to show.
	 */
	@Override public void showInventory(String header, Inventory inventory)
	{
		ImmutableMap<Integer, Item> items   = inventory.getItems();
		StringBuilder               builder = new StringBuilder();
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

	/**
	 * Show instructions on show to play the game.
	 */
	private void showInstructions()
	{
		printer.println("#");
		printer.println("# Instructions");
		printer.println("#");
		printer.println();
		printer.println("You interact with the room around you using 'properties' and 'actions'.");
		printer.println();
		printer.println("\tnorth lock inspect");
		printer.println();
		printer.println("The above command executes the action 'inspect' on the property 'lock' on the property 'north'.");
		printer.println("The 'lock' is a property nested inside the 'north' property.");
		printer.println();

		showCommands();
	}

	/**
	 * Show a list of global commands.
	 */
	private void showCommands()
	{
		printer.println("A number of commands are available at all times in the game:");
		printer.println();
		printer.println("\tquit                          Exit the game without saving your progress.");
		printer.println("\tproperties                    See all the properties you can currently access.");
		printer.println("\tactions                       See a global list of actions that can be performed.");
		printer.println("\tcommands                      See a list of possible commands.");
		printer.println("\tinstructions                  Print the game instructions.");
		printer.println();
	}

	/**
	 * Show a list of global commands.
	 *
	 * @param game The {@link Game} instance.
	 */
	private void showActions(Game game)
	{
		printer.println("---------- Actions ---------");
		printer.println();

		game.getActionRegistry().getActions().entrySet().forEach(entry -> {
			printer.println(entry.getKey());
			entry.getValue().iterator().forEachRemaining(action -> {
				printer.println(String.format("\t%-30s %s", action.getActionName(), action.getActionDescription()));
			});
		});
	}

	private void showProperties(Character character)
	{
		printer.println("The properties available to the character.");
		ImmutableMap<String, Property> properties = character.getProperties();
		showProperties(properties, "");
	}

	private void showProperties(ImmutableMap<String, Property> properties, String indentation)
	{
		properties.entrySet().forEach(entry -> {
			printer.println(String.format("%s- %s", indentation, entry.getKey()));
			if (entry.getValue() instanceof PropertyContainer) {
				PropertyContainer propertyContainer = (PropertyContainer) entry.getValue();
				showProperties(propertyContainer.getProperties(), indentation + '\t');
			}
		});
	}
}
