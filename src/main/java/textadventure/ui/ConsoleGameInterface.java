package textadventure.ui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.doors.*;
import textadventure.items.Inventory;
import textadventure.items.Item;
import textadventure.items.ItemType;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.Lock;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleGameInterface implements GameInterface
{

	public static void main(String[] args) throws Exception
	{
		GameInterface gameInterface = new ConsoleGameInterface(new Scanner(System.in), new PrintWriter(System.out, true));
		DefaultGame   game          = new DefaultGame(gameInterface, 5);
		game.start();
	}

	/**
	 * The {@link Scanner} to use for console input.
	 */
	private Scanner scanner;

	/**
	 * The {@link OutputStream} to use for console output.
	 */
	private PrintWriter printer;

	/**
	 * Creates a new {@link ConsoleGameInterface}.
	 *
	 * @param scanner The {@link Scanner} to use for console input.
	 * @param printer The {@link PrintWriter} to use for console output.
	 */
	public ConsoleGameInterface(Scanner scanner, PrintWriter printer)
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
		printer.println("\n" +
				" _____                            __                       _   _  _   __  \n" +
				"|  ___|                          / _|                     | \\ | || | / /  \n" +
				"| |__ ___  ___ __ _ _ __   ___  | |_ _ __ ___  _ __ ___   |  \\| || |/ /   \n" +
				"|  __/ __|/ __/ _` | '_ \\ / _ \\ |  _| '__/ _ \\| '_ ` _ \\  | . ` ||    \\   \n" +
				"| |__\\__ \\ (_| (_| | |_) |  __/ | | | | | (_) | | | | | | | |\\  || |\\  \\_ \n" +
				"\\____/___/\\___\\__,_| .__/ \\___| |_| |_|  \\___/|_| |_| |_| \\_| \\_/\\_| \\_(_)\n" +
				"                   | |                                                    \n" +
				"                   |_|                                                    ");

		showInstructions();
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();
		printer.println("       STORY");
		printer.println();
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();
		showCommands();
		showProperties();
		showActions();
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
	 * Called when a {@link Player} requests an {@link Action} from the {@link GameInterface}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param response The {@link ActionRequestCallback} to send with.
	 */
	@Override public void onActionRequest(Game game, Player player, ActionRequestCallback response)
	{
		LOOP:
		while (true) {

			printer.println("Please enter your next action.");
			String   input     = getInput();
			String[] arguments = getArgumentsFromCommand(input);
			String[] sections  = getSectionsFromCommand(input);

			if (input.equals("quit")) {
				System.exit(0);
				return;
			}

			if (input.equals("instructions")) {
				showInstructions();
				continue;
			}

			if (input.equals("properties")) {
				showProperties();
				continue;
			}

			if (input.equals("actions")) {
				showActions();
				continue;
			}

			if (input.equals("commands")) {
				showCommands();
				continue;
			}

			Property property = player.getCharacter();
			for (int i = 0; i < sections.length - 1; i++) {
				if (property instanceof PropertyContainer) {
					PropertyContainer container = (PropertyContainer) property;
					property = container.getProperty(sections[i]);
					if (property == null) {
						System.out.println(sections[i]);
						printer.println(
								String.format("Unknown property '%s'.", sections[i]));
						continue LOOP;
					}
					continue;
				}

				printer.println(String.format("Property '%s' cannot have sub-properties.", sections[i - 1]));
				continue LOOP;
			}

			String actionName = sections[sections.length - 1];
			Action action     = property.getAction(actionName);
			if (action == null) {
				printer.println(
						String.format(
								"No action with name '%s' on property '%s'.",
								actionName,
								sections.length < 2 ? "character" : sections[sections.length - 2]
						)
				);
				continue;
			}

			response.respond(action, arguments);
		}

	}

	/**
	 * Checks if the provided input is valid. Legal input follow the pattern <code>^[a-zA-Z0-9" ]+$</code>.
	 *
	 * @param input The input to validate.
	 * @return <code>true</code> of the provided input is valid. Returns <code>false</code> otherwise.
	 */
	private boolean validateInput(String input)
	{
		return input.matches("^[a-zA-Z0-9\" ]+$");
	}

	/**
	 * Returns the next line from the {@link Scanner}. The input is trimmed and converted to lower-case.
	 *
	 * @return The next line from the {@link Scanner}. The input is trimmed and converted to lower-case.
	 */
	private String getInput()
	{
		while (true) {
			String input = scanner.nextLine().trim().toLowerCase();
			if (validateInput(input))
				return input;
			else
				printer.println("Input must be given in the pattern ^[a-zA-Z0-9\" ]+$");
		}
	}

	/**
	 * Returns the arguments passes to a command.
	 *
	 * @param command The command.
	 * @return The array of arguments.
	 */
	private String[] getArgumentsFromCommand(String command)
	{
		final Matcher      matcher   = Pattern.compile("(\"[a-zA-Z0-9]*\")").matcher(command);
		final List<String> arguments = new ArrayList<>();
		while (matcher.find()) {
			arguments.add(matcher.group(0));
		}

		String[] result = new String[arguments.size()];
		arguments.toArray(result);
		return result;
	}

	/**
	 * Returns the sections (properties and action) of the provided command.
	 *
	 * @param command The command.
	 * @return The sections (properties and action) of the provided command.
	 */
	private String[] getSectionsFromCommand(String command)
	{
		int index = command.indexOf('\"');

		if (index != -1) {
			command = command.substring(0, index);
		}

		return command.split("[ ]+");
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
			printInventory(chest);
			return;
		}

		if (outcome == InspectChestAction.Outcome.CLOSED) {
			printer.println("You attempt to inspect the chest, but discover that the chest is closed.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link TakeItemFromChestAction}.
	 * @param action The {@link TakeItemFromChestAction} instance.
	 */
	@Override public void onChestTake(Game game, Player player, TakeItemFromChestAction action)
	{
		TakeItemFromChestAction.Outcome outcome = action.getOutcome();

		if (outcome == TakeItemFromChestAction.Outcome.SUCCESS) {
			printer.println("You succeeded in taking item " + itemListToString(action.getItems()) + " from the chest.");
			return;
		}

		if (outcome == TakeItemFromChestAction.Outcome.CLOSED) {
			printer.println("You cannot take items from a closed chest.");
			return;
		}

		if (outcome == TakeItemFromChestAction.Outcome.BACKPACK_FULL) {
			printer.println("You attempt to take the item, but you cannot fit it in your backpack.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link DepositItemsIntoChestAction}.
	 * @param action The {@link DepositItemsIntoChestAction} instance.
	 */
	@Override public void onChestDeposit(Game game, Player player, DepositItemsIntoChestAction action)
	{
		DepositItemsIntoChestAction.Outcome outcome = action.getOutcome();

		if (outcome == DepositItemsIntoChestAction.Outcome.SUCCESS) {
			printer.println("You succeeded in depositing item " + itemListToString(action.getItems()) + " into the chest.");
			return;
		}

		if (outcome == DepositItemsIntoChestAction.Outcome.CLOSED) {
			printer.println("You cannot deposit items into a closed chest.");
			return;
		}

		if (outcome == DepositItemsIntoChestAction.Outcome.CHEST_FULL) {
			printer.println("You attempt to deposit the item, but the chest is full.");
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a comma separated string of the names in the provided list of {@link Item}s.
	 *
	 * @param items The {@link Item}s.
	 */
	private String itemListToString(ImmutableList<Item> items)
	{
		StringBuilder builder = new StringBuilder();
		int           size    = items.size();
		for (int x = 0; x < size; x++) {
			builder.append(items.get(x).getItemName());
			if (x != x - 1) builder.append(", ");
		}

		return builder.toString();
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
			printInventory(action.getBackpack());
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Event when a {@link Player} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link DepositItemsIntoChestAction}.
	 * @param action The {@link DepositItemsIntoChestAction} instance.
	 */
	@Override public void onItemDrop(Game game, Player player, DropItemAction action)
	{
		System.out.println("dropped");
		System.out.println(action.getOutcome());
	}

	private void printInventory(Inventory inventory)
	{
		ImmutableMap<Integer, ItemType> items = inventory.getSlots();
		if (items.isEmpty()){
			printer.print("Your backpack is empty.");
		}else{
			items.forEach((key, value) -> printer.println(String.format("%-4d %-20s %-96s",
					key,
					value.getItemName(),
					value.getItemDescription())));
		}

	}

	/**
	 * Prompts the player to select an {@link Option}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} selecting.
	 * @param select The {@link Select} object.
	 */
	@Override public void select(Game game, Player player, Select select)
	{
		int                           minimumOptions = select.getMinimumNumberOfOptions();
		int                           maximumOptions = select.getMaximumNumberOfOptions();
		ImmutableMap<Integer, Option> options        = select.getOptions();

		printer.println("To perform the action you must select some of the following options. You select the option " +
				"using its identifier (id). To abort from the selection you can enter the 'abort' command. It's " +
				"possible to select multiple options. To finish from the selection you can enter the 'finish' command" +
				". The number of selected options must be between " + minimumOptions + " and " + maximumOptions + ".");
		options.forEach((slot, option) ->
				printer.println(String.format("%-4d %-20s %-96s",
						slot,
						option.getOptionName(),
						option.getOptionDescription()))
		);

		List<Option> choices = new ArrayList<>();
		while (true) {
			int choicesSize = choices.size();
			if (choicesSize >= maximumOptions)
				break;
			try {
				String input = scanner.nextLine();
				if (input.equals("abort")) return;
				if (input.equals("finish")) {
					if (choicesSize >= minimumOptions) {
						break;
					} else {
						printer.println("You have not selected enough options.");
					}
				}

				Integer parsed = Integer.parseInt(input);
				if (!options.containsKey(parsed)) {
					printer.println("Invalid selection.");
					continue;
				}

				choices.add(options.get(parsed));
				if (choices.size() == minimumOptions) {
					break;
				}

			} catch (NumberFormatException e) {
				printer.println("Selection must be a number.");
				select(game, player, select);
			}
		}
		try {
			select.select(choices);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Show instructions on show to play the game.
	 */

	private void showInstructions()
	{
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();
		printer.println("       INSTRUCTIONS");
		printer.println();
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();

		printer.println("You interact with the room around you using 'properties' and 'actions'.");
		printer.println();
		printer.println("\tnorth lock inspect");
		printer.println();
		printer.println("The above command executes the action 'inspect' on the property 'lock' on the property 'north'.");
		printer.println("The 'lock' is a property nested inside the 'north' property.");
		printer.println();
	}

	/**
	 * Show a list of global commands.
	 */
	private void showCommands()
	{
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();
		printer.println("       COMMANDS");
		printer.println();
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();

		printer.println("\tquit                          Exit the game without saving your progress.");
		printer.println("\tproperties                    See all the properties you can access in the rooms.");
		printer.println("\tactions                       See a global list of actions that can be performed.");
		printer.println("\tcommands                      See a list of possible commands.");
		printer.println("\tinstructions                  Print the game instructions.");
		printer.println();
	}

	/**
	 * Show a list of global commands.
	 */
	private void showActions()
	{
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();
		printer.println("       ACTIONS");
		printer.println();
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();

		printer.println("door");
		printer.println(String.format("\t%-30s %s", "open", "Open the door"));
		printer.println(String.format("\t%-30s %s", "close", "Close the door"));
		printer.println(String.format("\t%-30s %s", "inspect", "Inspect the door to learn new information."));
		printer.println(String.format("\t%-30s %s", "use", "Move through the door."));
		printer.println(String.format("\t%-30s %s", "lock", "Lock the lock on the door."));
		printer.println(String.format("\t%-30s %s", "unlock", "Unlock the lock on the door."));

		printer.println("lock");
		printer.println(String.format("\t%-30s %s", "lock", "Lock the lock."));
		printer.println(String.format("\t%-30s %s", "lock", "Lock the lock."));
		printer.println(String.format("\t%-30s %s", "inspect", "Inspect the lock to learn new information."));

		printer.println("chest");
		printer.println(String.format("\t%-30s %s", "open", "Open the chest."));
		printer.println(String.format("\t%-30s %s", "close", "Close the chest."));
		printer.println(String.format("\t%-30s %s", "inspect", "Inspect the chest to learn new information."));
		printer.println(String.format("\t%-30s %s", "take", "Take an item from the chest."));
		printer.println(String.format("\t%-30s %s", "lock", "Lock the lock on the chest."));
		printer.println(String.format("\t%-30s %s", "unlock", "Unlock the lock on the chest."));

		printer.println("backpack");
		printer.println(String.format("\t%-30s %s", "inspect", "Inspect the backpack to see the inventory."));
		printer.println();
	}

	/**
	 * Show the properties available in game.
	 */
	private void showProperties()
	{
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();
		printer.println("       PROPERTIES");
		printer.println();
		printer.println("------------------------------------------------------------------------------------------------------------------------");
		printer.println();

		printer.println("\tchest                         A chest that contains items.");
		printer.println("\tbackpack                      The backpack contains the items you carry.");
		printer.println("\tnorth                         A door in the northern part of the room.");
		printer.println("\tsouth                         A door in the southern part of the room.");
		printer.println("\twest                          A door in the western part of the room.");
		printer.println("\teast                          A door in the eastern part of the room.");
		printer.println("\tdoor/chest lock               A lock on a door or chest preventing it from being opened.");
	}
}
