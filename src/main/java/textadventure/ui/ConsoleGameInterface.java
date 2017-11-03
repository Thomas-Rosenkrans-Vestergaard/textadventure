package textadventure.ui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.characters.Character;
import textadventure.characters.*;
import textadventure.combat.AttackAction;
import textadventure.combat.Escapees;
import textadventure.combat.Faction;
import textadventure.combat.SecretPolice;
import textadventure.doors.*;
import textadventure.highscore.HighScoreResponse;
import textadventure.highscore.Outcome;
import textadventure.items.*;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.chest.*;
import textadventure.lock.*;
import textadventure.rooms.Coordinate;
import textadventure.rooms.Room;
import textadventure.rooms.RoomController;
import textadventure.rooms.UnknownRoomException;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleGameInterface implements GameInterface
{

	public static void main(String[] args) throws Exception
	{
		try {
			GameInterface  gameInterface  = new ConsoleGameInterface(new Scanner(System.in), new PrintWriter(System.out, true));
			Game           game           = Game.create(3);
			RoomController roomController = game.getRoomController();
			game.addFaction(new Escapees(new HumanPlayer(gameInterface), roomController.get(Coordinate.of(4, 1)), roomController.get(Coordinate.of(4, 5))));
			game.addFaction(new SecretPolice(new SecretPoliceComputerPlayer(), roomController.get(Coordinate.of(4, 5))));
			game.start();
		} catch (UnknownRoomException e) {
			System.out.println(e.getCoordinate().getX());
			System.out.println(e.getCoordinate().getY());
		}
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
	 * Event for when the {@link Game} starts.
	 *
	 * @param game     The {@link Game} instance.
	 * @param factions The list of {@link Faction}s competing in the {@link Game}.
	 * @param faction  The {@link Faction} the {@link Player} belongs to.
	 */
	@Override public void onGameStart(Game game, ImmutableList<Faction> factions, Faction faction)
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
		printer.println(faction.getStartingMessage());
		printer.println(faction.getStartingRoom().getRoomDescription());
	}

	/**
	 * Event for when the {@link Game} ends.
	 *
	 * @param game   The {@link Game} instance.
	 * @param result The result of the {@link Game}.
	 */
	@Override public void onGameEnd(Game game, boolean result)
	{
		printer.println(String.format("You %s.", result ? "won" : "loss"));
	}

	/**
	 * Event for when the {@link Player} needs to decide whether or not to save the {@link Player}s score to the
	 * high-score file.
	 *
	 * @param score    The score achieved by the {@link Player}.
	 * @param outcome  The {@link Outcome} of the {@link Game}.
	 * @param response The response of the {@link Player}.
	 */
	@Override public void onHighScoreRequest(int score, Outcome outcome, HighScoreResponse response)
	{
		printer.println(String.format("You achieved a score of %d in a %d.", score, outcome));

		boolean save;
		while (true) {
			printer.println("Do you want do save this score to the high-score file? (yes, no)");
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.equals("yes")) {
				save = true;
				break;
			}

			if (input.equals("no")) {
				save = false;
				break;
			}

			printer.println("Invalid input");
		}


		String name;
		while (true) {
			printer.println("Please enter the name you want to save the score under.");
			String input = scanner.nextLine().trim();
			if (input.indexOf(',') == -1) {
				name = input;
				break;
			}

			printer.println("Invalid input");
		}

		response.respond(save, name);
	}

	/**
	 * Events called when the {@link Player} should create their {@link Character}s.
	 *
	 * @param numberOfCharacters The number of {@link Character}s to create.
	 * @param creationCallback   The callback to use to create {@link Character}s.
	 * @param finishCallback     The callback to use when finishing creating {@link Character}s.
	 */
	@Override
	public void onCharactersCreate(int numberOfCharacters, CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback)
	{
		printer.println(String.format("You must now create your characters. You must create %d characters.",
				numberOfCharacters));

		int created = 0;
		try {
			while (created != numberOfCharacters) {
				createCharacter(creationCallback);
				created++;
			}
			finishCallback.respond();
		} catch (TooFewCharactersException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Creates a new {@link Character}, returning it to the provided {@link CharacterCreationCallback}.
	 *
	 * @param creationCallback The {@link CharacterCreationCallback}.
	 */
	private void createCharacter(CharacterCreationCallback creationCallback)
	{
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		characterCreationTemplate.setName(getCharacterName());

		try {
			while (true) {
				creationCallback.respond(characterCreationTemplate);
				return;
			}
		} catch (CharacterNameTakenException e) {
			printer.println("That name is already taken.");
			characterCreationTemplate.setName(getCharacterName());
		} catch (IncompleteCharacterException | TooManyCharactersException e) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Takes an returns the name of a {@link Character}.
	 *
	 * @return The name of the {@link Character}.
	 */
	private String getCharacterName()
	{
		printer.println("Enter the name of this character.");
		return scanner.nextLine();
	}

	/**
	 * Event for when a new {@link Character} is created.
	 *
	 * @param character The {@link Character} who was created.
	 */
	@Override public void onCharacterCreation(Character character)
	{

	}

	/**
	 * Event for when a {@link Character} is attacked using an {@link AttackAction}.
	 *
	 * @param character The {@link Character} who was attacked.
	 * @param action    The {@link AttackAction} instance.
	 */
	@Override public void onCharacterAttacked(Character character, AttackAction action)
	{

	}

	/**
	 * Event for when a {@link Character} enter a {@link Room}.
	 *
	 * @param character The {@link Character} who entered the {@link Room}.
	 * @param room      The {@link Room} the {@link Character} entered.
	 * @param door      The {@link Door} the {@link Character} entered through.
	 */
	@Override public void onCharacterEntersRoom(Character character, Room room, Door door)
	{

	}

	/**
	 * Event for when a {@link Character} exits a {@link Room}.
	 *
	 * @param character The {@link Character} who exited the {@link Room}.
	 * @param room      The {@link Room} the {@link Character} exited.
	 * @param door      The {@link Door} the {@link Character} exited through.
	 */
	@Override public void onCharacterExistsRoom(Character character, Room room, Door door)
	{

	}

	/**
	 * Event for when the {@link Player} must chose which {@link Character} to play next.
	 *
	 * @param characters The list of possible {@link Character}s left to chose.
	 * @param callback   The callback allowing the {@link Player} to chose which {@link Character} to play next.
	 */
	@Override public void onNextCharacter(ImmutableList<Character> characters, CharacterSelectionCallback callback)
	{
		printer.println("What character do you want to play next. Enter 'skip' the skip the rest of your turn.");
		for (Character character : characters) {
			printer.println(String.format("\t%s", character.getName()));
		}

		while (true) {

			String nextCharacter = scanner.nextLine().trim();

			if (nextCharacter.equals("skip")) {
				printer.println("You skipped the rest of your turn.");
				callback.skip();
				return;
			}

			for (Character character : characters) {
				if (character.getName().equals(nextCharacter)) {
					try {
						printer.println(String.format("You chose to play %s.", character.getName()));
						callback.next(character);
						return;
					} catch (CharacterAlreadyPlayedException e) {
						throw new IllegalStateException(e);
					}
				}
			}

			printer.println("That character doesn't exist.");
		}
	}

	/**
	 * Event for when a {@link Character} requests an {@link Action} from the {@link GameInterface}.
	 *
	 * @param character The {@link Character} who requests the {@link Action}.
	 * @param response  The {@link ActionRequestCallback} to send with.
	 */
	@Override public void onActionRequest(Character character, ActionRequestCallback response)
	{
		LOOP:
		while (true) {

			printer.println(String.format("Enter the next action to perform for %s.", character.getName()));
			String   input    = getInput();
			String[] sections = getSectionsFromCommand(input);

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

			response.respond(action);
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link CharacterInformationAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CharacterInformationAction}.
	 * @param action    The {@link CharacterInformationAction} instance.
	 */
	@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
	{
		CharacterInformation characterInformation = action.getCharacterInformation();

		if (!action.hasException()) {
			printer.println(String.format("Printing information about %s (%s).", character.getName(), character.getFaction()));
			printer.println(String.format("\tName                %s", characterInformation.getName()));
			printer.println(String.format("\tFaction             %s", characterInformation.getFaction().getClass().getSimpleName()));
			printer.println(String.format("\tProtective factor   %f", characterInformation.getProtectiveFactor()));
			printer.println(String.format("\tHeadWear            %s", characterInformation.getHeadWear()));
			printer.println(String.format("\tTorsoWear           %s", characterInformation.getTorsoWear()));
			printer.println(String.format("\tGloves              %s", characterInformation.getGloves()));
			printer.println(String.format("\tPants               %s", characterInformation.getPants()));
			printer.println(String.format("\tBoots               %s", characterInformation.getBoots()));
			printer.println(String.format("\tWeapon              %s", characterInformation.getWeapon()));
			printer.println(String.format("\tCurrent location    %s", characterInformation.getCurrentLocation().getRoomName()));
			printer.println(String.format("\tCurrent HP          %d", characterInformation.getCurrentHP()));
			printer.println(String.format("\tMax HP              %d", characterInformation.getMaxHP()));
			printer.println(String.format("\tLevel               %d", characterInformation.getLevel()));
			printer.println(String.format("\tSanity              %d", characterInformation.getSanity()));
			printer.println(String.format("\tStrength            %d", characterInformation.getStrength()));
			printer.println(String.format("\tDexterity           %d", characterInformation.getDexterity()));
			printer.println(String.format("\tIntelligence        %d", characterInformation.getIntelligence()));
			printer.println(String.format("\tStealth             %d", characterInformation.getStealth()));
			printer.println(String.format("\tMoney               %d", characterInformation.getMoney()));
			return;
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsAction}.
	 * @param action    The {@link UseItemsAction} instance.
	 */
	@Override public void onUseItemAction(Character character, UseItemsAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s (%s) used the following item(s).",
					character.getName(),
					character.getFaction()));
			for (UsableItem item : action.getItems())
				printer.println(String.format("\t%s", item.getItemTypeName()));

			return;
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link EquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EquipAction}.
	 * @param action    The {@link EquipAction} instance.
	 */
	@Override public void onEquipAction(Character character, EquipAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s (%s) equipped the following items.", character
					.getName(), character.getFaction()));
			for (EquipableItem item : action.getItems())
				printer.println(String.format("\t%s", item.getItemTypeName()));
			return;
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link UnEquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnEquipAction}.
	 * @param action    The {@link UnEquipAction} instance.
	 */
	@Override public void onUnEquipAction(Character character, UnEquipAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s (%s) unequipped the following items.",
					character.getName(),
					character.getFaction()));
			for (EquipableItem item : action.getItems())
				printer.println(String.format("\t%s", item.getItemTypeName()));
			return;
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link AttackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link AttackAction}.
	 * @param action    The {@link AttackAction} instance.
	 */
	@Override public void onAttackAction(Character character, AttackAction action)
	{
		if (!action.hasException()) {
			for (Map.Entry<Character, Integer> damageDone : action.getDamageDone().entrySet())
				printer.println(String.format("%s (%s) attacked %s (%s) with %s dealing %d damage.",
						character.getName(),
						character.getFaction(),
						damageDone.getKey(),
						damageDone.getValue(),
						character.getWeapon().getItemTypeName(),
						action.getDamageDone()));
			return;
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link EscapeAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EscapeAction}.
	 * @param action    The {@link EscapeAction} instance.
	 */
	@Override public void onEscapeAction(Character character, EscapeAction action)
	{
		printer.println(String.format("%s (%s) escaped from the tunnel", character.getName(), character.getFaction()));
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
	{
		if (!action.hasException()) {
			ImmutableSet<Character> characters = action.getCharacters();
			printer.println("You inspect the room.");
			if (characters.size() == 0)
				printer.println("You are alone in the room.");
			else
				action.getCharacters().forEach(c -> {
					printer.println(String.format("In the room with you is %s from faction %s.", c.getName(), c
							.getFaction().toString()));
				});
			return;
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
	{
		if (!action.hasException()) {
			printer.println("You inspect the floor.");
			printInventory(action.getFloor());
			return;
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
	{
		if (!action.hasException()) {
			printer.println("You attempted and succeeded in opening the door.");
			return;
		}

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
	 * @param character The {@link Character} who performed the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
	{
		if (!action.hasException()) {
			printer.println("You attempted and succeeded in closing the door.");
			return;
		}

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
	 * @param character The {@link Character} who performed the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	@Override public void onUseDoorAction(Character character, UseDoorAction action)
	{
		if (!action.hasException()) {
			printer.println("You successfully entered a new room using the door.");
			printer.println(character.getCurrentLocation().getRoomDescription());
		}

		action.onException(DoorClosedException.class, e -> {
			printer.println("You attempted the use the door, but discover that the door is closed.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	@Override public void onInspectDoorAction(Character character, InspectDoorAction action)
	{
		if (!action.hasException()) {
			String doorState = action.getDoor().getState().name().toLowerCase();
			printer.println(String.format("You inspect the door, learning that the door is %s.", doorState));
			return;
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	@Override public void onLockLockAction(Character character, LockLockAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s (%s) successfully locked the lock.", character.getName(), character.getFaction()));
			return;
		}

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

		action.onException(NotEnoughOptionsException.class, e -> {
			printer.println("You have no keys.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	@Override public void onUnlockLockAction(Character character, UnlockLockAction action)
	{
		if (!action.hasException()) {
			printer.println("You successfully unlocked the lock using the provided key.");
			return;
		}

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

		action.onException(NotEnoughOptionsException.class, e -> {
			printer.println("You have no keys.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	@Override public void onInspectLockAction(Character character, InspectLockAction action)
	{
		if (!action.hasException()) {
			Lock lock = action.getLock();
			printer.println(
					String.format("You inspect the lock learning that the lock is %s. You notice that '%s' is written on the lock.",
							lock.getState().name().toLowerCase(),
							lock.getCode()
					)
			);

			return;
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	@Override public void onOpenChestAction(Character character, OpenChestAction action)
	{
		if (!action.hasException()) {
			printer.println("You successfully opened the chest.");
			return;
		}

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
	 * @param character The {@link Character} who performed the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	@Override public void onCloseChestAction(Character character, CloseChestAction action)
	{
		if (!action.hasException()) {
			printer.println("You successfully closed the chest.");
			return;
		}

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
	 * @param character The {@link Character} who performed the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	@Override public void onInspectChestAction(Character character, InspectChestAction action)
	{
		if (!action.hasException()) {
			printer.println("You succeed in inspecting the chest.");
			printInventory(action.getChest());
			return;
		}

		action.onException(ChestClosedException.class, e -> {
			printer.println("You attempt to inspect the chest, but discover that the chest is closed.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
	{
		if (!action.hasException()) {
			printer.println("You succeeded in taking item " + itemListToString(action.getItems()) + " from the chest.");
			return;
		}

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
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
	{
		if (!action.hasException()) {
			printer.println("You succeeded in depositing item " + itemListToString(action.getItems()) + " into the chest.");
			return;
		}

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
	 * @param character The {@link Character} who performed the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	@Override public void onInspectBackpackAction(Character character, InspectBackpackAction action)
	{
		if (!action.hasException()) {
			printer.println("You succeed in inspecting your backpack.");
			printInventory(action.getBackpack());
			return;
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("Your backpack was successfully expanded to %d positions.", action.getBackpack().getNumberOfPositions()));
			return;
		}

		action.onException(NumberFormatException.class, e -> {
			printer.println("The provided argument must be an integer.");
		});

		action.onException(NotEnoughItemsException.class, e -> {
			printer.println("The provided argument pointed to an empty backpack stack.");
		});

		action.onException(PositionRangeException.class, e -> {
			printer.println("The selected item was not a backpack expansion.");
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onDropItemAction(Character character, DropItemAction action)
	{
		if (!action.hasException()) {
			ImmutableList<Item> items = action.getItems();
			printer.println(String.format("You successfully dropped up %d item(s) (%s).", items.size(), itemListToString
					(items)));
			return;
		}

		action.onException(NumberFormatException.class, e -> {
			printer.println("The provided argument must be an integer.");
			return;
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
	{
		if (!action.hasException()) {
			ImmutableList<Item> items = action.getItems();
			printer.println(String.format("You successfully picked up %d item(s) (%s).", items.size(), itemListToString
					(items)));
			return;
		}
	}

	@Override public void onTransferItemAction(Character character, TransferItemAction action)
	{

	}

	/**
	 * @param inventory
	 */

	private void printInventory(Inventory inventory)
	{
		ImmutableMap<Integer, ItemType> items = inventory.getPositions();
		if (items.isEmpty()) {
			printer.println("The inventory is empty.");
			return;
		}

		items.forEach((key, value) -> printer.println(String.format("%-4d %-20s %-96s",
				key,
				value.getItemTypeName(),
				value.getItemTypeDescription())));
	}

	@Override public void select(Select select)
	{
		int                                     minimumOptions = select.getMinimumNumberOfOptions();
		int                                     maximumOptions = select.getMaximumNumberOfOptions();
		ImmutableMap<Integer, ? extends Option> options        = select.getOptions();

		printer.println("To perform the action you must select some of the following options. You select the option " +
				"using its identifier (id). To abort from the selection you can enter the 'abort' command. It's " +
				"possible to select multiple options. To finish from the selection you can enter the 'finish' command" +
				". The number of selected options must be between " + minimumOptions + " and " + maximumOptions + ".");
		options.forEach((position, option) ->
				printer.println(String.format("%-4d %-20s %-96s",
						position,
						option.getOptionName(),
						option.getOptionDescription()))
		);

		List<Option> choices = new ArrayList<>();
		while (true) {
			int choicesSize = choices.size();
			if (choicesSize >= maximumOptions || choicesSize == options.size())
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
				select(select);
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
			builder.append(items.get(x).getItemTypeName());
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
			if (input.matches("^[a-zA-Z0-9\\- ]+$"))
				return input;
			printer.println("Input must only contain letters, numbers, apostrophes and dashes.");
		}
	}

	/**
	 * Returns the sections (properties and action) of the provided command.
	 *
	 * @param command The command.
	 * @return The sections (properties and action) of the provided command.
	 */
	private String[] getSectionsFromCommand(String command)
	{
		return command.split("[ ]+");
	}
}
