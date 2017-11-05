package textadventure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.actions.ActionResponses;
import textadventure.characters.*;
import textadventure.characters.Character;
import textadventure.combat.*;
import textadventure.doors.*;
import textadventure.highscore.HighScoreReader;
import textadventure.highscore.HighScoreResponse;
import textadventure.highscore.Outcome;
import textadventure.highscore.Score;
import textadventure.items.*;
import textadventure.items.backpack.Backpack;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.chest.*;
import textadventure.lock.*;
import textadventure.rooms.*;
import textadventure.select.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class ConsoleGameInterface implements GameInterface
{

	/**
	 * The {@link Scanner} to use for console input.
	 */
	private Scanner     scanner;
	/**
	 * The {@link OutputStream} to use for console output.
	 */
	private PrintWriter printer;
	private Map<Class<? extends Action>, String>                            actionNames              = new HashMap<>();
	private Map<Class<? extends Property>, String>                          propertyNames            = new HashMap<>();
	private Map<Class<? extends Property>, List<Class<? extends Action>>>   actionRelations          = new HashMap<>();
	private Map<Class<? extends Property>, List<Class<? extends Property>>> propertyRelations        = new HashMap<>();
	private ActionResponses                                                 secondaryActionResponses = new ActionResponses()
	{
		@Override
		public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onEscapeAction(Character character, EscapeAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onAttackAction(Character character, AttackAction action)
		{
			if (!action.hasException()) {
				for (Map.Entry<Character, Integer> damageDone : action.getDamageDone().entrySet())
					printer.println(String.format("%s from faction %s attacked character %s from faction %s with %s " +
									"dealing %d damage.",
							character.getName(),
							character.getFaction().getName(),
							damageDone.getKey().getName(),
							damageDone.getKey().getFaction().getName(),
							character.getWeapon().getWeaponName(),
							damageDone.getValue()));
				return;
			}
		}

		@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onUseItemsAction(Character character, UseItemsAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s used the following item(s).", character.getName(),
						character.getFaction().getName()));
				for (UsableItem item : action.getItems())
					printer.println(String.format("\t%30%s %50%s", item.getItemTypeName(), item.getItemTypeDescription()));
				return;
			}
		}

		@Override public void onUseItemsOnAction(Character character, UseItemsOnAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s used item '%s' on the following characters:",
						character.getName(),
						character.getFaction().getName(),
						action.getItem().getItemTypeName()));
				for (Character target : action.getTargets())
					printer.println(String.format("\t%20%s %20%s", target.getName(), target.getFaction()));
				return;
			}
		}

		@Override public void onEquipAction(Character character, EquipAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s equipped the following items.",
						character.getName(),
						character.getFaction().getName()));
				for (EquipableItem item : action.getItems())
					printer.println(String.format("\t%s", item.getItemTypeName()));
				return;
			}
		}

		@Override public void onUnEquipAction(Character character, UnEquipAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s unequipped the following items:",
						character.getName(),
						character.getFaction().getName()));
				for (EquipableItem item : action.getItems())
					printer.println(String.format("\t%s", item.getItemTypeName()));
				return;
			}
		}

		@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s opened %s.",
						character.getName(),
						character.getFaction().getName(),
						action.getDoor().getClass().getSimpleName()));
				return;
			}
		}

		@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s closed %s.",
						character.getName(),
						character.getFaction().getName(),
						action.getDoor().getClass().getName()));
				return;
			}
		}

		@Override public void onUseDoorAction(Character character, UseDoorAction action)
		{

		}

		@Override public void onUseDoorExit(Character character, UseDoorAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s exited %s",
						character.getName(),
						character.getFaction().getName(),
						action.getInitialRoom().getRoomName()));
			}
		}

		@Override public void onUseDoorEntered(Character character, UseDoorAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s entered %s",
						character.getName(),
						character.getFaction().getName(),
						action.getTargetRoom().getRoomName()));

				printer.println(character.getCurrentLocation().getRoomDescription());
			}
		}

		@Override public void onInspectDoorAction(Character character, InspectDoorAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onLockLockAction(Character character, LockLockAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onUnlockLockAction(Character character, UnlockLockAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onInspectLockAction(Character character, InspectLockAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onOpenChestAction(Character character, OpenChestAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s opened the chest.",
						character.getName(),
						character.getFaction().getName()));
				return;
			}
		}

		@Override public void onCloseChestAction(Character character, CloseChestAction action)
		{
			if (!action.hasException()) {
				printer.println(String.format("%s from faction %s successfully closed the chest.",
						character.getName(),
						character.getFaction().getName()));
				return;
			}
		}

		@Override public void onInspectChestAction(Character character, InspectChestAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onInspectBackpackAction(Character character, InspectBackpackAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onDropItemAction(Character character, DropItemAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
		{
			throw new UnsupportedOperationException();
		}

		@Override public void onTransferItemAction(Character character, TransferItemsAction action)
		{
			throw new UnsupportedOperationException();
		}
	};

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

		addProperty(BaseCharacter.class, "character");
		addProperty(BaseRoom.class, "room");
		addProperty(Floor.class, "floor");
		addProperty(NorthDoor.class, "north");
		addProperty(SouthDoor.class, "south");
		addProperty(EastDoor.class, "east");
		addProperty(WestDoor.class, "west");
		addProperty(Chest.class, "chest");
		addProperty(Backpack.class, "backpack");
		addProperty(Lock.class, "lock");

		addAction(CharacterInformationAction.class, "stats");
		addAction(DropItemAction.class, "drop");
		addAction(EquipAction.class, "equip");
		addAction(EscapeAction.class, "escape");
		addAction(UnEquipAction.class, "unequip");
		addAction(NothingAction.class, "nothing");
		addAction(TransferItemsAction.class, "transfer");
		addAction(InspectFloorAction.class, "inspect");
		addAction(InspectRoomAction.class, "inspect");
		addAction(PickUpItemAction.class, "pickup");
		addAction(UseItemsAction.class, "use");
		addAction(AttackAction.class, "attack");
		addAction(CloseDoorAction.class, "close");
		addAction(OpenDoorAction.class, "open");
		addAction(InspectDoorAction.class, "inspect");
		addAction(UseDoorAction.class, "use");
		addAction(ExpandBackpackAction.class, "expand");
		addAction(InspectBackpackAction.class, "inspect");
		addAction(OpenChestAction.class, "open");
		addAction(CloseChestAction.class, "close");
		addAction(DepositItemsIntoChestAction.class, "deposit");
		addAction(TakeItemFromChestAction.class, "take");
		addAction(InspectChestAction.class, "inspect");
		addAction(LockLockAction.class, "lock");
		addAction(UnlockLockAction.class, "unlock");
		addAction(InspectLockAction.class, "inspect");

		addActionRelation(BaseCharacter.class, CharacterInformationAction.class);
		addActionRelation(BaseCharacter.class, DropItemAction.class);
		addActionRelation(BaseCharacter.class, EquipAction.class);
		addActionRelation(BaseCharacter.class, EscapeAction.class);
		addActionRelation(BaseCharacter.class, UnEquipAction.class);
		addActionRelation(BaseCharacter.class, PickUpItemAction.class);
		addActionRelation(BaseCharacter.class, UseItemsAction.class);
		addActionRelation(BaseCharacter.class, AttackAction.class);

		addActionRelation(Floor.class, InspectFloorAction.class);
		addActionRelation(BaseRoom.class, InspectRoomAction.class);

		addActionRelation(NorthDoor.class, OpenDoorAction.class);
		addActionRelation(NorthDoor.class, CloseDoorAction.class);
		addActionRelation(NorthDoor.class, InspectDoorAction.class);
		addActionRelation(NorthDoor.class, UseDoorAction.class);

		addActionRelation(SouthDoor.class, OpenDoorAction.class);
		addActionRelation(SouthDoor.class, CloseDoorAction.class);
		addActionRelation(SouthDoor.class, InspectDoorAction.class);
		addActionRelation(SouthDoor.class, UseDoorAction.class);

		addActionRelation(EastDoor.class, OpenDoorAction.class);
		addActionRelation(EastDoor.class, CloseDoorAction.class);
		addActionRelation(EastDoor.class, InspectDoorAction.class);
		addActionRelation(EastDoor.class, UseDoorAction.class);

		addActionRelation(WestDoor.class, OpenDoorAction.class);
		addActionRelation(WestDoor.class, CloseDoorAction.class);
		addActionRelation(WestDoor.class, InspectDoorAction.class);
		addActionRelation(WestDoor.class, UseDoorAction.class);

		addActionRelation(Backpack.class, InspectBackpackAction.class);
		addActionRelation(Backpack.class, ExpandBackpackAction.class);

		addActionRelation(Chest.class, OpenChestAction.class);
		addActionRelation(Chest.class, CloseChestAction.class);
		addActionRelation(Chest.class, DepositItemsIntoChestAction.class);
		addActionRelation(Chest.class, TakeItemFromChestAction.class);
		addActionRelation(Chest.class, InspectChestAction.class);

		addActionRelation(Lock.class, LockLockAction.class);
		addActionRelation(Lock.class, UnlockLockAction.class);
		addActionRelation(Lock.class, InspectLockAction.class);

		addActionRelation(Chest.class, LockLockAction.class);
		addActionRelation(Chest.class, UnlockLockAction.class);
		addActionRelation(Chest.class, InspectLockAction.class);

		addActionRelation(NorthDoor.class, LockLockAction.class);
		addActionRelation(NorthDoor.class, UnlockLockAction.class);
		addActionRelation(NorthDoor.class, InspectLockAction.class);

		addActionRelation(SouthDoor.class, LockLockAction.class);
		addActionRelation(SouthDoor.class, UnlockLockAction.class);
		addActionRelation(SouthDoor.class, InspectLockAction.class);

		addActionRelation(EastDoor.class, LockLockAction.class);
		addActionRelation(EastDoor.class, UnlockLockAction.class);
		addActionRelation(EastDoor.class, InspectLockAction.class);

		addActionRelation(WestDoor.class, LockLockAction.class);
		addActionRelation(WestDoor.class, UnlockLockAction.class);
		addActionRelation(WestDoor.class, InspectLockAction.class);

		addPropertyRelation(BaseRoom.class, NorthDoor.class);
		addPropertyRelation(BaseRoom.class, SouthDoor.class);
		addPropertyRelation(BaseRoom.class, EastDoor.class);
		addPropertyRelation(BaseRoom.class, WestDoor.class);
		addPropertyRelation(BaseRoom.class, Chest.class);
		addPropertyRelation(BaseCharacter.class, Backpack.class);
		addPropertyRelation(BaseCharacter.class, BaseRoom.class);
		addPropertyRelation(NorthDoor.class, Lock.class);
		addPropertyRelation(SouthDoor.class, Lock.class);
		addPropertyRelation(EastDoor.class, Lock.class);
		addPropertyRelation(WestDoor.class, Lock.class);
		addPropertyRelation(Chest.class, Lock.class);
	}

	public static void main(String[] args) throws Exception
	{
		try {
			GameInterface  gameInterface = new ConsoleGameInterface(new Scanner(System.in), new PrintWriter(System.out, true));
			Game           game          = new Game(1);
			RoomController rooms         = game.getRooms();
			game.addFaction(new Escapees(new HumanPlayer(gameInterface), rooms.get(Coordinate.of(4, 1)), rooms.get(Coordinate.of(4, 5))));
			game.addFaction(new SecretPolice(new SecretPoliceAI(), rooms.get(Coordinate.of(4, 5))));
			game.start();
		} catch (UnknownRoomException e) {
			System.out.println("Unknown room.");
			System.out.println(e.getCoordinate().getX());
			System.out.println(e.getCoordinate().getY());
		}
	}

	private void addAction(Class<? extends Action> type, String name)
	{
		actionNames.put(type, name);
	}

	private void addProperty(Class<? extends Property> type, String name)
	{
		this.propertyNames.put(type, name);
	}

	private void addActionRelation(Class<? extends Property> property, Class<? extends Action> action)
	{
		if (!actionRelations.containsKey(property))
			this.actionRelations.put(property, new ArrayList<>());

		this.actionRelations.get(property).add(action);
	}

	private void addPropertyRelation(Class<? extends Property> propertyContainer, Class<? extends Property> property)
	{
		if (!propertyRelations.containsKey(propertyContainer))
			this.propertyRelations.put(propertyContainer, new ArrayList<>());

		this.propertyRelations.get(propertyContainer).add(property);
	}

	/**
	 * Event for when the {@link Game} starts.
	 *
	 * @param game     The {@link Game} instance.
	 * @param factions The list of {@link Faction}s competing in the {@link Game}.
	 * @param faction  The {@link Faction} the {@link Player} belongs to.
	 */
	@Override
	public void onGameStart(Game game, ImmutableList<Faction> factions, Faction faction)
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

		printer.println("|");
		printer.println("|");
		printer.println("|  STORY");
		printer.println("|");
		printer.println("|");
		printer.println();
		printer.println(faction.getStartingMessage());
		printer.println(faction.getStartingRoom().getRoomDescription());
		printer.println();
		showInstructions();
		showCommands();
		showMembers();
	}

	/**
	 * Event for when the {@link Game} ends.
	 *
	 * @param game   The {@link Game} instance.
	 * @param result The result of the {@link Game}.
	 */
	@Override
	public void onGameEnd(Game game, boolean result)
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
	@Override
	public void onHighScoreRequest(int score, Outcome outcome, HighScoreResponse response)
	{
		printer.println(String.format("You achieved a score of %d in a %s.", score, outcome));

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
		printer.println(String.format("You play the game through %d characters.", numberOfCharacters));
		printer.println("Before the game starts you must now create those characters.");

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
		printer.println("Enter the name of your character.");
		return scanner.nextLine();
	}

	/**
	 * Event for when a new {@link Character} is created.
	 *
	 * @param character The {@link Character} who was created.
	 */
	@Override
	public void onCharacterCreation(Character character)
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

		StringBuilder builder = new StringBuilder();
		builder.append("What character do you want to play next. Enter 'skip' to skip the rest of your turn.");
		builder.append('\n');
		for (int x = 0; x < characters.size(); x++) {
			builder.append(String.format("\t%4d    %s", x, characters.get(x).getName()));
			builder.append('\n');
		}

		try {

			while (true) {
				String input = getInput(builder.toString());

				if (input.equals("skip")) {
					printer.println("You skipped the rest of your turn.");
					callback.skip();
					return;
				}

				try {
					int       index         = Integer.parseInt(input);
					Character nextCharacter = characters.get(index);
					if (nextCharacter == null) {
						printer.println("No character with that index.");
						continue;
					}

					callback.next(nextCharacter);
					return;

				} catch (NumberFormatException e) {
					for (Character character : characters) {
						if (character.getName().equals(input)) {
							printer.println(String.format("You chose to play %s.", character.getName()));
							callback.next(character);
							return;
						}
					}

					printer.println("No character with that name.");
					continue;
				}
			}
		} catch (CharacterAlreadyPlayedException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Event for when an {@link Action}
	 *
	 * @param character The {@link Character} that the {@link Player} should chose an {@link Action} for.
	 * @param callback  The callback to use for returning an {@link Action}.
	 */
	@Override public void onActionRequest(Character character, ActionRequestCallback callback)
	{
		LOOP:
		while (true) {

			String   input    = getInput(String.format("Enter the next action to perform for %s.", character.getName()));
			String[] sections = getSectionsFromCommand(input);

			try {

				Property currentProperty = character;
				SECTIONS:
				for (int i = 0; i < sections.length - 1; i++) {
					for (Map.Entry<Class<? extends Property>, String> entry : propertyNames.entrySet()) {
						if (currentProperty.hasProperty(entry.getKey()) && entry.getValue().equals(sections[i])) {
							currentProperty = currentProperty.getProperty(entry.getKey());
							continue SECTIONS;
						}
					}

					printer.println(String.format("No property %s on property %s.", sections[i], propertyNames.get
							(currentProperty.getClass())));
					continue LOOP;
				}

				Action action     = null;
				String actionName = sections[sections.length - 1];
				for (Map.Entry<Class<? extends Action>, String> entry : actionNames.entrySet()) {
					if (currentProperty.hasActionFactory(entry.getKey()) && actionNames.get(entry.getKey()).equals(actionName)) {
						action = currentProperty.getAction(entry.getKey());
						break;
					}
				}

				if (action == null) {
					printer.println(String.format("Unknown action with name %s on property %s.", actionName, propertyNames.get(currentProperty)));
					continue LOOP;
				}

				callback.respond(action);

			} catch (UnknownPropertyException e) {
				printer.println(String.format("Unknown property %s on property %s.",
						e.getChild().getSimpleName(),
						e.getParent().getClass().getSimpleName()));
				continue LOOP;
			} catch (UnknownActionException e) {
				printer.println(String.format("Unknown action %s on property %s.",
						e.getAction().getSimpleName(),
						e.getProperty().getClass().getSimpleName()));
				continue LOOP;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Event for when the {@link Character} performs the {@link CharacterInformationAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CharacterInformationAction}.
	 * @param action    The {@link CharacterInformationAction} instance.
	 */
	@Override
	public void onCharacterInformationAction(Character character, CharacterInformationAction action)
	{
		CharacterInformation characterInformation = action.getCharacterInformation();

		if (!action.hasException()) {
			printer.println(String.format("Printing information about Character %s.", character.getName()));
			printer.println(String.format("\tName                %s", characterInformation.getName()));
			printer.println(String.format("\tFaction             %s", characterInformation.getFaction().getName()));
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
	@Override
	public void onUseItemsAction(Character character, UseItemsAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s used the following item(s).", character.getName()));
			for (UsableItem item : action.getItems())
				printer.println(String.format("\t%-30%s %-50s", item.getItemTypeName(), item.getItemTypeDescription
						()));
			return;
		}

		action.onException(NoUsesLeftException.class, e -> {
			printer.println("No more uses left.");
		});
	}

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsOnAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsOnAction}.
	 * @param action    The {@link UseItemsOnAction} instance.
	 */
	@Override public void onUseItemsOnAction(Character character, UseItemsOnAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s used item '%s' on the following characters:",
					character.getName(),
					action.getItem().getItemTypeName()));
			for (Character target : action.getTargets())
				printer.println(String.format("\t%-30%s %-50%s", target.getName(), target.getFaction()));
			return;
		}

		action.onException(NoUsesLeftException.class, e -> {
			printer.println("No more uses left.");
		});
	}

	/**
	 * Event for when the {@link Character} performs the {@link EquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EquipAction}.
	 * @param action    The {@link EquipAction} instance.
	 */
	@Override
	public void onEquipAction(Character character, EquipAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s equipped the following items.",
					character.getName()));
			for (EquipableItem item : action.getItems())
				printer.println(String.format("\t%-30s %-50s", item.getEquipableName(), item.getEquipableDescription()));
			return;
		}

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format("%s has no equipable items.", character.getName()));
		});

		action.onException(SelectResponseException.class, e -> {
			printer.println(String.format("An error occurred while selecting what to equip."));
		});
	}

	/**
	 * Event for when the {@link Character} performs the {@link UnEquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnEquipAction}.
	 * @param action    The {@link UnEquipAction} instance.
	 */
	@Override
	public void onUnEquipAction(Character character, UnEquipAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s unequipped the following items:",
					character.getName()));
			for (EquipableItem item : action.getItems())
				printer.println(String.format("\t%-30s %-50s", item.getEquipableName(), item.getEquipableDescription()));
			return;
		}

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format("%s has no items to uneqiup.", character.getName()));
		});

		action.onException(SelectResponseException.class, e -> {
			printer.println(String.format("An error occurred while selecting what to unequip."));
		});
	}

	/**
	 * Event for when the {@link Character} performs the {@link AttackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link AttackAction}.
	 * @param action    The {@link AttackAction} instance.
	 */
	@Override
	public void onAttackAction(Character character, AttackAction action)
	{
		if (!action.hasException()) {
			for (Map.Entry<Character, Integer> damageDone : action.getDamageDone().entrySet())
				printer.println(String.format("%s attacked character %s from faction %s with %s dealing %d damage.",
						character.getName(),
						damageDone.getKey().getName(),
						damageDone.getKey().getFaction().getName(),
						character.getWeapon().getWeaponName(),
						damageDone.getValue()));
			return;
		}

		action.onException(NoTargetsException.class, e -> {
			printer.println(String.format("%s has no characters to attack."));
		});
	}

	/**
	 * Event for when the {@link Character} performs the {@link EscapeAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EscapeAction}.
	 * @param action    The {@link EscapeAction} instance.
	 */
	@Override
	public void onEscapeAction(Character character, EscapeAction action)
	{
		action.onSuccess(() -> {
			printer.println(String.format("%s escaped from the tunnel.",
					character.getName(),
					character.getFaction().getName()));
		});

		action.onException(NotEndingRoomException.class, e -> {
			printer.println("You can only escape through the ending room.");
		});

		action.onException(NotEscapeeException.class, e -> {
			printer.println(String.format("Only characters belonging to the Escapees faction can escape."));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	@Override
	public void onInspectRoomAction(Character character, InspectRoomAction action)
	{
		if (!action.hasException()) {
			ImmutableSet<Character>                 characters = action.getCharacters();
			ImmutableSet<Class<? extends Property>> properties = action.getProperties();

			printer.println(String.format("There are %d other characters in the room.", characters.size()));
			for (Character x : action.getCharacters())
				printer.println(String.format("\tCharacter %s from %s.", x.getName(), x.getFaction().getName()));

			printer.println(String.format("There are %d properties in the room.", properties.size()));
			for (Class<? extends Property> property : properties)
				printer.println(String.format("\t%s", propertyNames.get(property)));

			if (action.hasException())
				printer.println("Exception occurred.");
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	@Override
	public void onInspectFloorAction(Character character, InspectFloorAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s inspected the floor.",
					character.getName()));
			if (action.getFloor().getNumberOfItems() > 0)
				printInventory(action.getFloor());
			else
				printer.println("No items on the floor.");
			return;
		}

		if (action.hasException())
			printer.println("Exception occurred.");
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	@Override
	public void onOpenDoorAction(Character character, OpenDoorAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s opened %s.", character.getName(), propertyNames.get(action.getDoor().getClass())));
			return;
		}

		action.onException(DoorAlreadyOpenException.class, e -> {
			printer.println(String.format("%s attempted to open %s, even though the door is already open.",
					character.getName(),
					propertyNames.get(action.getDoor().getClass())));
		});

		action.onException(DoorLockedException.class, e -> {
			printer.println(String.format("%s attempted to open %s, but discover that the door is locked.",
					character.getName(),
					propertyNames.get(action.getDoor().getClass())));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link CloseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	@Override
	public void onCloseDoorAction(Character character, CloseDoorAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s closed %s.", character.getName(), propertyNames.get(action.getDoor().getClass())));
			return;
		}

		action.onException(DoorAlreadyClosedException.class, e -> {
			printer.println(String.format("%s attempted to close %s, even though the door is already closed.",
					character.getName(),
					propertyNames.get(action.getDoor().getClass())));
		});

		action.onException(DoorLockedException.class, e -> {
			printer.println(String.format("%s attempted to close %s, but discover that the door is locked.",
					character.getName(),
					propertyNames.get(action.getDoor().getClass())));
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
			printer.println(String.format("%s entered %s from direction %s.",
					character.getName(),
					action.getTargetRoom().getRoomName(),
					action.getDoor().getDirection().getInverse()));

			printer.println(action.getTargetRoom().getRoomDescription());

			if (character.getFaction() instanceof Escapees) {
				Escapees escapees = (Escapees) character.getFaction();
				if (escapees.getEndingRoom() == character.getCurrentLocation())
					printer.println("You can escape from this room.");
			}
		}

		action.onException(DoorClosedException.class, e -> {
			printer.println(String.format("%s attempted the use %s, but discovered that it's is closed.",
					character.getName(),
					propertyNames.get(action.getDoor().getClass())));
		});
	}

	/**
	 * Event when a {@link Character} exists a {@link Room} using the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	@Override public void onUseDoorExit(Character character, UseDoorAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s exited %s through direction %s.",
					character.getName(),
					action.getInitialRoom().getRoomName(),
					action.getDoor().getDirection()));
		}
	}

	/**
	 * Event when a {@link Character} enters a {@link Room} using the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	@Override public void onUseDoorEntered(Character character, UseDoorAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s entered %s from direction %s.",
					character.getName(),
					action.getTargetRoom().getRoomName(),
					action.getDoor().getDirection().getInverse()));

			printer.println(character.getCurrentLocation().getRoomDescription());
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	@Override
	public void onInspectDoorAction(Character character, InspectDoorAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s inspect %s, learning that the door is %s.",
					character.getName(),
					propertyNames.get(action.getDoor().getClass()),
					action.getDoor().getState().name().toLowerCase()));
			return;
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	@Override
	public void onLockLockAction(Character character, LockLockAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s successfully locked the lock.", character.getName()));
			return;
		}

		action.onException(LockAlreadyLockedException.class, e -> {
			printer.println(String.format("%s attempted to lock the lock, even though the lock is already locked.",
					character.getName()));
		});

		action.onException(UnknownIndexException.class, e -> {
			printer.println(String.format("%s attempted to lock the lock with something other than a key.",
					character.getName()));
		});

		action.onException(IncorrectKeyException.class, e -> {
			printer.println(String.format("%s attempted to turn the lock, but discover that the key doesn't fit.",
					character.getName()));
		});

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format("%s has no keys to use to lock the lock.", character.getName()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	@Override
	public void onUnlockLockAction(Character character, UnlockLockAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s successfully unlocked the lock.", character.getName()));
			return;
		}

		action.onException(LockAlreadyUnlockedException.class, e -> {
			printer.println(String.format("%s attempted to unlock the lock, even though the lock is already unlocked.",
					character.getName()));
		});

		action.onException(UnknownIndexException.class, e -> {
			printer.println(String.format("%s attempted to unlock the lock with something other than a key.",
					character.getName()));
		});

		action.onException(IncorrectKeyException.class, e -> {
			printer.println(String.format("%s attempted to turn the lock, but discover that the key doesn't fit.",
					character.getName()));
		});

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format("%s has no keys to use to unlock the lock.", character.getName()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	@Override
	public void onInspectLockAction(Character character, InspectLockAction action)
	{
		if (!action.hasException()) {
			Lock lock = action.getLock();
			printer.println(
					String.format("%s inspected the lock learning that the lock is %s. '%s' is written on the lock.",
							character.getName(),
							lock.getState().name().toLowerCase(),
							lock.getCode()
					)
			);
		}

		if (action.hasException()) {
			printer.println("Exception occurred.");
		}
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	@Override
	public void onOpenChestAction(Character character, OpenChestAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s opened the chest.", character.getName()));
			return;
		}

		action.onException(ChestAlreadyOpenException.class, e -> {
			printer.println(String.format("%s attempted to open the chest, even though the chest was already open.",
					character.getName()));
		});

		action.onException(ChestLockedException.class, e -> {
			printer.println(String.format("%s attempted to open the chest, but discovered that the chest is locked.",
					character.getName()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link CloseChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	@Override
	public void onCloseChestAction(Character character, CloseChestAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s successfully closed the chest.", character.getName()));
			return;
		}

		action.onException(DoorAlreadyClosedException.class, e -> {
			printer.println(String.format("%s attempted to close the chest, even though the chest was already closed" +
					".", character.getName()));
		});

		action.onException(DoorLockedException.class, e -> {
			printer.println(String.format("%s attempted to close the chest, but discover that the chest is locked.",
					character.getName()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	@Override
	public void onInspectChestAction(Character character, InspectChestAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s succeeded in inspecting the chest.", character.getName()));
			if (action.getChest().getNumberOfItems() > 0)
				printInventory(action.getChest());
			else
				printer.println("There are no items in the chest.");
			return;
		}

		action.onException(ChestClosedException.class, e -> {
			printer.println(String.format("%s attempted to inspect the chest, but discover that the chest is closed.",
					character.getName()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	@Override
	public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s succeeded in taking the following items from the chest.", character.getName()));
			for (Item item : action.getItems())
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
			return;
		}

		action.onException(ChestClosedException.class, e -> {
			printer.println(String.format("%s cannot take items from a closed chest.", character.getName()));
		});

		action.onException(InventoryFullException.class, e -> {
			printer.println(String.format("%s attempted to take the items, but cannot fit them all in the backpack.",
					character.getName()));
			if (action.getItems().size() > 0) {
				printer.println("The following items were moved.");
				for (Item item : action.getItems())
					printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription
							()));
			}
		});

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format(String.format("There is nothing %s can take from the chest.", character
					.getName())));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override
	public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s succeeded in depositing the following items into the chest.", character
					.getName()));
			for (Item item : action.getItems())
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
			return;
		}

		action.onException(ChestClosedException.class, e -> {
			printer.println(String.format("%s cannot deposit items into a closed chest.", character.getName()));
		});

		action.onException(InventoryFullException.class, e -> {
			printer.println(String.format("%s attempted to deposit the items, but cannot fit them all in the chest.", character.getName()));
			if (action.getItems().size() > 0) {
				printer.println("The following items were moved.");
				for (Item item : action.getItems())
					printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription
							()));
			}
		});

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format(String.format("There is nothing %s can deposit into the chest.", character.getName())));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	@Override
	public void onInspectBackpackAction(Character character, InspectBackpackAction action)
	{
		if (!action.hasException()) {
			if (action.getBackpack().getNumberOfItems() > 0) {
				printer.println(String.format("%s succeed in inspecting your backpack.", character.getName()));
				printInventory(action.getBackpack());
			} else {
				printer.println("There are no items in the backpack.");
			}
			return;
		}

		if (action.hasException())
			printer.println("Exception occurred.");
	}

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	@Override
	public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
	{
		if (!action.hasException()) {
			printer.println(String.format("%s's backpack was successfully expanded to %d positions.",
					character.getName(),
					action.getBackpack().getNumberOfPositions()));
			return;
		}

		action.onException(NotEnoughItemsException.class, e -> {
			printer.println(String.format("%s has no backpack expansion tokens.", character.getName()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override
	public void onDropItemAction(Character character, DropItemAction action)
	{
		ImmutableList<Item> items = action.getItems();

		if (!action.hasException()) {
			printer.println(String.format("%s successfully dropped %d item(s).", character.getName(), items.size()));
			for (Item item : items)
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
			return;
		}

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format("%s has nothing to drop.", character.getName()));
		});

		action.onException(InventoryFullException.class, e -> {
			printer.println(String.format("No more inventory space. %d items were dropped.", items.size()));
			for (Item item : items)
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	@Override
	public void onPickUpItemAction(Character character, PickUpItemAction action)
	{
		ImmutableList<Item> items = action.getItems();

		if (!action.hasException()) {
			printer.println(String.format("%s successfully picked up %d item(s).", character.getName(), items.size()));
			for (Item item : items)
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
			return;
		}

		action.onException(NoOptionsException.class, e -> {
			printer.println(String.format("Nothing to pick up from the floor."));
		});

		action.onException(InventoryFullException.class, e -> {
			printer.println(String.format("No more inventory space. %d items were dropped.", items.size()));
			for (Item item : items)
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
		});
	}

	/**
	 * Event when a {@link Character} performs the {@link TransferItemsAction}.
	 *
	 * @param character The {@link Character} who performed the {@link TransferItemsAction}.
	 * @param action    The {@link TransferItemsAction} instance.
	 */
	@Override public void onTransferItemAction(Character character, TransferItemsAction action)
	{
		ImmutableList<Item> items = action.getItems();

		action.onSuccess(() -> {
			printer.println(String.format("%s transferred %d items to %s.",
					action.getSource().getName(),
					items.size(),
					action.getDestination().getName()));

			for (Item item : items)
				printer.println(String.format("\t%-30s %-50s", item.getItemTypeName(), item.getItemTypeDescription()));
		});

		action.onException(NoOptionsException.class, e -> {
			printer.println("There are no characters to transfer items to.");
		});
	}

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

	@Override
	public void select(Select select)
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
		printer.println("|");
		printer.println("|");
		printer.println("|  INSTRUCTIONS");
		printer.println("|");
		printer.println("|");
		printer.println();

		printer.println("You interact with the room around you using 'properties' and 'actions'. An example can be found below.");
		printer.println();
		printer.println("\tnorth lock inspect");
		printer.println();
		printer.println("The above command executes the action 'inspect' on the property 'lock' on the property " +
				"'north'. To see all the 'properties' and 'actions' use the 'members' command.");
		printer.println();
	}

	/**
	 * Show a list of global commands.
	 */
	private void showCommands()
	{
		printer.println("|");
		printer.println("|");
		printer.println("|  COMMANDS");
		printer.println("|");
		printer.println("|");
		printer.println();


		printer.println("\tquit                          Exit the game.");
		printer.println("\tmembers                       See all the actions and properties you can access.");
		printer.println("\tcommands                      See a list of possible commands.");
		printer.println("\tinstructions                  Print the game instructions.");
		printer.println("\thighscores                    Print the highscores.");
		printer.println();
	}

	/**
	 * Show the properties available in game.
	 */
	private void showMembers()
	{
		printer.println("|");
		printer.println("|");
		printer.println("|  Members");
		printer.println("|");
		printer.println("|");
		printer.println();


		printer.println("All members descend from the character.");
		printer.println();

		printProperty(BaseCharacter.class);
		printer.println();
	}

	private void printProperty(Class<? extends Property> property)
	{
		printProperty(property, "");
	}

	private void printProperty(Class<? extends Property> property, String indent)
	{
		printer.println(indent + "property " + propertyNames.get(property));
		List<Class<? extends Action>> actions = actionRelations.get(property);
		if (actions != null)
			for (Class<? extends Action> action : actions)
				printer.println(indent + "\taction " + actionNames.get(action));
		List<Class<? extends Property>> properties = propertyRelations.get(property);
		if (properties != null)
			for (Class<? extends Property> x : properties)
				printProperty(x, indent + "\t");
	}

	private void showHighScores()
	{
		printer.println("|");
		printer.println("|");
		printer.println("|  High-scores");
		printer.println("|");
		printer.println("|");
		try {
			HighScoreReader reader = new HighScoreReader(Paths.get("scores.txt"));
			List<Score>     scores = reader.read();

			for (Score score : scores)
				printer.println(String.format("%-40s %-5d %-5s", score.getName(), score.getScore(), score.getOutcome
						()));
		} catch (Exception e) {
			printer.println("Could not show highscores.");
		}
	}

	/**
	 * Returns the next line from the {@link Scanner}. The input is trimmed and converted to lower-case.
	 *
	 * @return The next line from the {@link Scanner}. The input is trimmed and converted to lower-case.
	 */
	private String getInput(String message)
	{
		while (true) {
			printer.println(message);
			String input = scanner.nextLine().trim().toLowerCase();
			if (input.matches("^[a-zA-Z0-9\\- ]+$")) {

				if (input.equals("quit")) {
					System.exit(0);
				}

				if (input.equals("instructions")) {
					showInstructions();
					return getInput(message);
				}

				if (input.equals("members")) {
					showMembers();
					return getInput(message);
				}

				if (input.equals("highscores")) {
					showHighScores();
					return getInput(message);
				}

				if (input.equals("commands")) {
					showCommands();
					return getInput(message);
				}

				return input;
			}

			printer.println("Input must only contain letters, numbers and dashes.");
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

	/**
	 * Returns the {@link ActionResponses} used when notifying {@link Player}s about {@link Action}s occurring within
	 * sight of the {@link Character}s controlled by the {@link Player}.
	 *
	 * @return The {@link ActionResponses} used when notifying {@link Player}s about {@link Action}s occurring within
	 * sight of the {@link Character}s controlled by the {@link Player}.
	 */
	@Override public ActionResponses getSecondaryActionResponses()
	{
		return secondaryActionResponses;
	}
}
