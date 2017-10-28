package textadventure.ui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import textadventure.Character;
import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.doors.*;
import textadventure.items.*;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.backpack.PickUpItemAction;
import textadventure.items.chest.*;
import textadventure.lock.*;
import textadventure.rooms.InspectFloorAction;

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
		DefaultGame   game          = new DefaultGame(gameInterface);
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
	 * Event when a {@link Player} starts their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	@Override public void onTurnStart(Game game, Player player)
	{

	}

	/**
	 * Event when a {@link Player} ends their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	@Override public void onTurnEnd(Game game, Player player)
	{

	}

	/**
	 * Called when a {@link Character} requests an {@link Action} from the {@link GameInterface}.
	 *
	 * @param character The {@link Character} who requests the {@link Action}.
	 * @param response  The {@link ActionRequestCallback} to send with.
	 */
	@Override public void onActionRequest(Character character, ActionRequestCallback response)
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

			Property property = character;
			for (int i = 0; i < sections.length - 1; i++) {
				if (property instanceof PropertyContainer) {
					PropertyContainer container = (PropertyContainer) property;
					property = container.getProperty(sections[i]);
					if (property == null) {
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
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	@Override public void onFloorInspect(Character character, InspectFloorAction action)
	{
		action.onSuccess(() -> {
			printer.println("You inspect the floor.");
			printInventory(action.getFloor());
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	@Override public void onDoorOpen(Character character, OpenDoorAction action)
	{
		action.onSuccess(() -> {
			printer.println("You attempted and succeeded in opening the door.");
		});

		action.onException(DoorAlreadyOpenException.class, e -> {
			printer.println("You attempted to open the door, even though the door is already open.");
			printer.println("You start to question your sanity.");
		});

		action.onException(DoorLockedException.class, e -> {
			printer.println("You attempted to open the door, but discover that the door is locked.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link CloseDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	@Override public void onDoorClose(Character character, CloseDoorAction action)
	{
		action.onSuccess(() -> {
			printer.println("You attempted and succeeded in closing the door.");
		});

		action.onException(DoorAlreadyClosedException.class, e -> {
			printer.println("You attempted to close the door, even though the door is already closed.");
			printer.println("You start to question your sanity.");
		});

		action.onException(DoorLockedException.class, e -> {
			printer.println("You attempted to close the door, but discover that the door is locked.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	@Override public void onDoorUse(Character character, UseDoorAction action)
	{
		action.onSuccess(() -> {
			printer.println("You successfully entered a new room using the door.");
			printer.println(character.getCurrentLocation().getRoomDescription());
		});

		action.onException(DoorClosedException.class, e -> {
			printer.println("You attempted the use the door, but discover that the door is closed.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	@Override public void onDoorInspect(Character character, InspectDoorAction action)
	{
		action.onSuccess(() -> {
			String doorState = action.getDoor().getState().name().toLowerCase();
			printer.println(String.format("You inspect the door, learning that the door is %s.", doorState));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	@Override public void onLockLock(Character character, LockLockAction action)
	{
		action.onSuccess(() -> {
			printer.println("You successfully lock the lock using the provided key.");
		});

		action.onException(LockAlreadyLockedException.class, e -> {
			printer.println("You attempted to lock the lock, even though the lock is already locked.");
			printer.println("You start to question your sanity.");
		});

		action.onException(UnknownIndexException.class, e -> {
			printer.println("The item you attempted to lock the lock with is not a key.");
			printer.println("You start to questing your sanity.");
		});

		action.onException(IncorrectKeyException.class, e -> {
			printer.println("You attempt to turn the lock, but discover that you have the wrong key.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	@Override public void onLockUnlock(Character character, UnlockLockAction action)
	{
		action.onSuccess(() -> {
			printer.println("You successfully unlocked the lock using the provided key.");
		});

		action.onException(NumberFormatException.class, e -> {
			printer.println("The provided argument must be an integer.");
		});

		action.onException(LockAlreadyUnlockedException.class, e -> {
			printer.println("You attempted to unlock the lock, even though the lock is already unlocked.");
			printer.println("You start to question your sanity.");
		});

		action.onException(UnknownIndexException.class, e -> {
			printer.println("The item you attempted to unlock the lock with is not a key.");
			printer.println("You start to questing your sanity.");
		});

		action.onException(IncorrectKeyException.class, e -> {
			printer.println("You attempt to turn the lock, but discover that you have the wrong key.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	@Override public void onLockInspect(Character character, InspectLockAction action)
	{
		action.onSuccess(() -> {
			Lock lock = action.getLock();
			printer.println(
					String.format("You inspect the lock learning that the lock is %s. You notice that '%s' is written on the lock.",
							lock.getState().name().toLowerCase(),
							lock.getCode()
					)
			);
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	@Override public void onChestOpen(Character character, OpenChestAction action)
	{
		action.onSuccess(() -> {
			printer.println("You successfully opened the chest.");
		});

		action.onException(ChestAlreadyOpenException.class, e -> {
			printer.println("You attempted to open the chest, even though the chest is already open.");
			printer.println("You start to question your sanity.");
		});

		action.onException(ChestLockedException.class, e -> {
			printer.println("You attempt to open the chest, but discover that the chest is locked.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link CloseChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	@Override public void onChestClose(Character character, CloseChestAction action)
	{
		action.onSuccess(() -> {
			printer.println("You successfully closed the chest.");
		});

		action.onException(DoorAlreadyClosedException.class, e -> {
			printer.println("You attempted to close the chest, even though the chest is already closed.");
			printer.println("You start to question your sanity.");
		});

		action.onException(DoorLockedException.class, e -> {
			printer.println("You attempt to close the chest, but discover that the chest is locked.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	@Override public void onChestInspect(Character character, InspectChestAction action)
	{
		action.onSuccess(() -> {
			printer.println("You succeed in inspecting the chest.");
			printInventory(action.getChest());
		});

		action.onException(ChestClosedException.class, e -> {
			printer.println("You attempt to inspect the chest, but discover that the chest is closed.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	@Override public void onChestTake(Character character, TakeItemFromChestAction action)
	{
		action.onSuccess(() -> {
			printer.println("You succeeded in taking item " + itemListToString(action.getItems()) + " from the chest.");
		});

		action.onException(ChestClosedException.class, e -> {
			printer.println("You cannot take items from a closed chest.");
		});

		action.onException(InventoryFullException.class, e -> {
			printer.println("You attempt to take the items, but you cannot fit them in your backpack.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onChestDeposit(Character character, DepositItemsIntoChestAction action)
	{
		action.onSuccess(() -> {
			printer.println("You succeeded in depositing item " + itemListToString(action.getItems()) + " into the chest.");
		});

		action.onException(ChestClosedException.class, e -> {
			printer.println("You cannot deposit items into a closed chest.");
		});

		action.onException(InventoryFullException.class, e -> {
			printer.println("You attempt to deposit the item, but the chest is full.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectBackpackAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	@Override public void onBackpackInspect(Character character, InspectBackpackAction action)
	{
		action.onSuccess(() -> {
			printer.println("You succeed in inspecting your backpack.");
			printInventory(action.getBackpack());
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	@Override public void onBackpackExpand(Character character, ExpandBackpackAction action)
	{
		action.onSuccess(() -> {
			printer.println(String.format("Your backpack was successfully expanded to %d slots.", action.getBackpack().getNumberOfSlots()));
		});

		action.onException(NumberFormatException.class, e -> {
			printer.println("The provided argument must be an integer.");
		});

		action.onException(NotEnoughItemsException.class, e -> {
			printer.println("The provided argument pointed to an empty backpack stack.");
		});

		action.onException(SlotOutOfRangeException.class, e -> {
			printer.println("The selected item was not a backpack expansion.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onItemDrop(Character character, DropItemAction action)
	{
		action.onSuccess(() -> {
			ImmutableList<Item> items = action.getItems();
			printer.println(String.format("You successfully dropped up %d item(s) (%s).", items.size(), itemListToString
					(items)));
		});

		action.onException(NumberFormatException.class, e -> {
			printer.println("The provided argument must be an integer.");
			return;
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	@Override public void onItemPickup(Character character, PickUpItemAction action)
	{
		action.onSuccess(() -> {
			ImmutableList<Item> items = action.getItems();
			printer.println(String.format("You successfully picked up %d item(s) (%s).", items.size(), itemListToString
					(items)));
		});
	}

	/**
	 * @param inventory
	 */
	private void printInventory(Inventory inventory)
	{
		ImmutableMap<Integer, ItemType> items = inventory.getSlots();
		if (items.isEmpty()) {
			printer.println("The inventory is empty.");
			return;
		}

		items.forEach((key, value) -> printer.println(String.format("%-4d %-20s %-96s",
				key,
				value.getItemName(),
				value.getItemDescription())));
	}

	/**
	 * Prompts the character to select one or more {@link Option}.
	 *
	 * @param character The {@link Character} selecting.
	 * @param select    The {@link Select} object.
	 */
	@Override public void select(Character character, Select select)
	{
		int                                     minimumOptions = select.getMinimumNumberOfOptions();
		int                                     maximumOptions = select.getMaximumNumberOfOptions();
		ImmutableMap<Integer, ? extends Option> options        = select.getOptions();

		printer.println("To perform the action you must selectIndices some of the following options. You selectIndices the option " +
				"using its identifier (id). To abort from the selection you can enter the 'abort' command. It's " +
				"possible to selectIndices multiple options. To finish from the selection you can enter the 'finish' command" +
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
				select(character, select);
			}
		}
		try {
			select.selectOptions(choices);
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

	/**
	 * Returns a comma separated string of the names in the provided list of {@link Item}s.
	 *
	 * @param items The {@link Item}s.
	 */
	private String itemListToString(ImmutableList<? extends ItemType> items)
	{
		StringBuilder builder = new StringBuilder();
		int           size    = items.size();
		for (int x = 0; x < size; x++) {
			builder.append(items.get(x).getItemName());
			if (x != size - 1) builder.append(", ");
		}

		return builder.toString();
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
			if (input.matches("^[a-zA-Z0-9'\\- ]+$"))
				return input;
			printer.println("Input must only contain letters, numbers, apostrophes and dashes.");
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
		final Matcher      matcher   = Pattern.compile("('([a-zA-Z0-9\\-]*)')").matcher(command);
		final List<String> arguments = new ArrayList<>();
		while (matcher.find())
			arguments.add(matcher.group(2));

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
		int index = command.indexOf('\'');

		if (index != -1) {
			command = command.substring(0, index);
		}

		return command.split("[ ]+");
	}
}
