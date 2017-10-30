package textadventure.ui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import textadventure.Character;
import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.combat.DamageSource;
import textadventure.combat.Escapees;
import textadventure.combat.Faction;
import textadventure.combat.SecretPolice;
import textadventure.doors.*;
import textadventure.items.*;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.backpack.PickUpItemAction;
import textadventure.items.chest.*;
import textadventure.lock.*;
import textadventure.rooms.*;
import textadventure.ui.characterSelection.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static textadventure.doors.Door.State.CLOSED;
import static textadventure.doors.Door.State.OPEN;
import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class ConsoleGameInterface implements GameInterface
{

	public static void main(String[] args) throws Exception
	{
		try {
			GameInterface  gameInterface  = new ConsoleGameInterface(new Scanner(System.in), new PrintWriter(System.out, true));
			RoomController roomController = new RoomController();

			roomController.add(Coordinate.of(2, 1), new BaseRoom("Room (2,1)", "This small chamber seems divided into three parts. The first has several hooks on the walls from which hang dusty robes. An open curtain separates that space from the next, which has a dry basin set in the floor. In the northern part of the room is a door."));

			roomController.add(Coordinate.of(4, 1), new BaseRoom("Room (4,1)", "A horrendous, overwhelming stench wafts from the room before you. Small cages containing small animals and large insects line the walls. Some of the creatures look sickly and alive but most are clearly dead. Their rotting corpses and the unclean cages no doubt result in the zoo's foul odor. A cat mews weakly from its cage, but the other creatures just silently shrink back into their filthy prisons. A dusty military sits in the corner of the room. In the northern part of the room is a door."));
			Chest chest = chestFactory(10, Chest.State.CLOSED, lockFactory("KLY4SW", LOCKED, gameInterface), gameInterface);
			chest.addItem(new Key("KZSE6X"));
			roomController.get(Coordinate.of(4, 1)).addProperty("chest", chest);
			roomController.get(Coordinate.of(4, 1)).getRoomFloor().addItem(new Key("KLY4SW"));

			roomController.add(Coordinate.of(2, 2), new BaseRoom("Room (2,2)", "Corpses and pieces of corpses hang from hooks that dangle from chains attached to thick iron rings. You don't see any heads, hands, or feet -- all seem to have been chopped or torn off. Neither do you see any guts in the horrible array, but several thick leather sacks hang from hooks in the walls, and they are suspiciously wet and the leather looks extremely taut -- as if it' under great strain. In the northern, eastern and southern side of the room is a door."));

			roomController.add(Coordinate.of(3, 2), new BaseRoom("Room (3,2)", "You've opened the door to a torture chamber. Several devices of degradation, pain, and death stand about the room, all of them showing signs of regular use. The wood of the rack is worn smooth by struggling bodies, and the iron maiden appears to be occupied by a corpse. In the western, northern and eastern side of the room is a door."));

			roomController.add(Coordinate.of(4, 2), new BaseRoom("Room (4,2)", "You catch a whiff of the unmistakable metallic tang of blood as you open the door. The floor is covered with it, and splashes of blood spatter the walls. Some drops even reach the ceiling. It looks fresh, but you don't see any bodies or footprints leaving the chamber. In the eastern, southern and western path of the room is a door."));

			roomController.add(Coordinate.of(5, 2), new BaseRoom("Room (5,2)", "You smelled smoke as you moved down the hall, and rounding the corner into this room you see why. Every surface bears scorch marks and ash piles on the floor. The room reeks of fire and burnt flesh. Either a great battle happened here or the room bears some fire danger you cannot see for no flames light the room anymore. In the western, northern and eastern side of the room is a door."));

			roomController.add(Coordinate.of(6, 2), new BaseRoom("Room (6,2)", "This tiny room holds a curious array of machinery. Winches and levers project from every wall, and chains with handles dangle from the ceiling. On a nearby wall, you note a pictogram of what looks like a scythe on a chain. In the western side of the room is a door."));

			roomController.add(Coordinate.of(2, 3), new BaseRoom("Room (2,3)", "Rats inside the room shriek when they hear the door open, then they run in all directions from a putrid corpse lying in the center of the floor. As these creatures crowd around the edges of the room, seeking to crawl through a hole in one corner, they fight one another. In the northern, eastern and southern side of the room is a door."));

			roomController.add(Coordinate.of(3, 3), new BaseRoom("Room (3,3)", "A flurry of bats suddenly flaps through the doorway, their screeching barely audible as they careen past your heads. They flap past you into the rooms and halls beyond. The room from which they came seems barren at first glance. In the western, eastern and southern side of the room is a door."));

			roomController.add(Coordinate.of(4, 3), new BaseRoom("Room (4,3)", "A huge iron cage lies on its side in this room, and its gate rests open on the floor. A broken chain lies under the door, and the cage is on a rotting corpse. Another corpse lies a short distance away from the cage. It lacks a head. In the western and eastern side of the room is a door."));

			roomController.add(Coordinate.of(5, 3), new BaseRoom("Room (5,3)", "This chamber served as an armory. Armor and weapon racks line the walls and rusty and broken weapons litter the floor. It hasn't been used in a long time. In the western, southern and eastern side of the room is a door."));

			roomController.add(Coordinate.of(6, 3), new BaseRoom("Room (6,3)", " This chamber is clearly a prison. Small barred cells line the walls, leaving a 15-foot-wide pathway for a guard to walk. Channels run down either side of the path next to the cages, probably to allow the prisoners' waste to flow through the grates on the other side of the room. The cells appear empty but your vantage point doesn't allow you to see the full extent of them all. In the western side of the room is a door."));

			roomController.add(Coordinate.of(1, 4), new BaseRoom("Room (1,4)", "You open the door to what must be a combat training room. Rough fighting circles are scratched into the surface of the floor. Wooden fighting dummies stand waiting for someone to attack them. A few punching bags hang from the ceiling. There's something peculiar about it all though. In the eastern side of the room is a door."));

			roomController.add(Coordinate.of(2, 4), new BaseRoom("Room (2,4)", "This small room contains several pieces of well-polished wood furniture. Eight ornate, high-backed chairs surround a long oval table, and a side table stands next to the far exit. All bear delicate carvings of various shapes. One bears carvings of skulls and bones, another is carved with shields and magic circles, and a third is carved with shapes like flames and lightning strokes. In the western, southern and western side of the room is a door."));

			roomController.add(Coordinate.of(3, 4), new BaseRoom("Room (3,4)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western, northern and eastern side of the room is a door."));

			roomController.add(Coordinate.of(4, 4), new BaseRoom("Room (4,4)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western, northern and eastern side of the room is a door."));

			roomController.add(Coordinate.of(5, 4), new BaseRoom("Room (4,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp. In the western and eastern side of the room is a door."));

			roomController.add(Coordinate.of(6, 4), new BaseRoom("Room (5,3)", "This room served as a sleeping area for the soldiers stationed here. The room is filled with bunk beds lining the walls. The beds bring back memories of sleeping in the concentration camp."));

			roomController.add(Coordinate.of(3, 5), new BaseRoom("Room (3,5)", "This room is of much higher quality that the other rooms. In the room is a desk lined with medals. In the southern side of the room is a door."));

			roomController.add(Coordinate.of(4, 5), new BaseRoom("Room (3,4)", "Ending room description. In the southern side of the room is a door."));

			final String NORTH_DOOR_NAME = "north";
			final String SOUTH_DOOR_NAME = "south";
			final String EAST_DOOR_NAME  = "east";
			final String WEST_DOOR_NAME  = "west";

			Door door;
			door = doorFactory(OPEN, lockFactory("K5YUZB", UNLOCKED, gameInterface), roomController.get(Coordinate.of(2, 1)), roomController.get(Coordinate.of(2, 2)), gameInterface);
			roomController.get(Coordinate.of(2, 1)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(2, 2)).addProperty(SOUTH_DOOR_NAME, door);


			door = doorFactory(CLOSED, lockFactory("KZSE6X", LOCKED, gameInterface), roomController.get(Coordinate.of(4, 1)), roomController.get(Coordinate.of(4, 2)), gameInterface);
			roomController.get(Coordinate.of(4, 1)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(4, 2)).addProperty(SOUTH_DOOR_NAME, door);

			// Horizontal

			door = doorFactory(OPEN, lockFactory("KN68LL", UNLOCKED, gameInterface), roomController.get(Coordinate.of(2, 2)), roomController.get(Coordinate.of(3, 2)), gameInterface);
			roomController.get(Coordinate.of(2, 2)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(3, 2)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KXICTX", UNLOCKED, gameInterface), roomController.get(Coordinate.of(3, 2)), roomController.get(Coordinate.of(4, 2)), gameInterface);
			roomController.get(Coordinate.of(3, 2)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(4, 2)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KMV3F1", UNLOCKED, gameInterface), roomController.get(Coordinate.of(4, 2)), roomController.get(Coordinate.of(5, 2)), gameInterface);
			roomController.get(Coordinate.of(4, 2)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(5, 2)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KTJX8C", UNLOCKED, gameInterface), roomController.get(Coordinate.of(5, 2)), roomController.get(Coordinate.of(6, 2)), gameInterface);
			roomController.get(Coordinate.of(5, 2)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(6, 2)).addProperty(WEST_DOOR_NAME, door);

			// Vertical

			door = doorFactory(OPEN, lockFactory("K1WIWL", UNLOCKED, gameInterface), roomController.get(Coordinate.of(2, 2)), roomController.get(Coordinate.of(2, 3)), gameInterface);
			roomController.get(Coordinate.of(2, 2)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(2, 3)).addProperty(SOUTH_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K8BGK7", UNLOCKED, gameInterface), roomController.get(Coordinate.of(3, 2)), roomController.get(Coordinate.of(3, 3)), gameInterface);
			roomController.get(Coordinate.of(3, 2)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(3, 3)).addProperty(SOUTH_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K3DNE6", UNLOCKED, gameInterface), roomController.get(Coordinate.of(5, 2)), roomController.get(Coordinate.of(5, 3)), gameInterface);
			roomController.get(Coordinate.of(5, 2)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(5, 3)).addProperty(SOUTH_DOOR_NAME, door);

			// Horizontal

			door = doorFactory(OPEN, lockFactory("KT6UH3", UNLOCKED, gameInterface), roomController.get(Coordinate.of(2, 3)), roomController.get(Coordinate.of(3, 3)), gameInterface);
			roomController.get(Coordinate.of(2, 3)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(3, 3)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KB71RC", UNLOCKED, gameInterface), roomController.get(Coordinate.of(3, 3)), roomController.get(Coordinate.of(4, 3)), gameInterface);
			roomController.get(Coordinate.of(3, 3)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(4, 3)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KJ8O29", UNLOCKED, gameInterface), roomController.get(Coordinate.of(4, 3)), roomController.get(Coordinate.of(5, 3)), gameInterface);
			roomController.get(Coordinate.of(4, 3)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(5, 3)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KOOKW5", UNLOCKED, gameInterface), roomController.get(Coordinate.of(5, 3)), roomController.get(Coordinate.of(6, 3)), gameInterface);
			roomController.get(Coordinate.of(5, 3)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(6, 3)).addProperty(WEST_DOOR_NAME, door);

			// Vertical

			door = doorFactory(OPEN, lockFactory("KAT55Y", UNLOCKED, gameInterface), roomController.get(Coordinate.of(2, 3)), roomController.get(Coordinate.of(2, 4)), gameInterface);
			roomController.get(Coordinate.of(2, 3)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(2, 4)).addProperty(SOUTH_DOOR_NAME, door);

			// Horizontal

			door = doorFactory(OPEN, lockFactory("KDV046", UNLOCKED, gameInterface), roomController.get(Coordinate.of(1, 4)), roomController.get(Coordinate.of(2, 4)), gameInterface);
			roomController.get(Coordinate.of(1, 4)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(2, 4)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K08L4C", UNLOCKED, gameInterface), roomController.get(Coordinate.of(2, 4)), roomController.get(Coordinate.of(3, 4)), gameInterface);
			roomController.get(Coordinate.of(2, 4)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(3, 4)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KOME75", UNLOCKED, gameInterface), roomController.get(Coordinate.of(3, 4)), roomController.get(Coordinate.of(4, 4)), gameInterface);
			roomController.get(Coordinate.of(3, 4)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(4, 4)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KPQXPS", UNLOCKED, gameInterface), roomController.get(Coordinate.of(4, 4)), roomController.get(Coordinate.of(5, 4)), gameInterface);
			roomController.get(Coordinate.of(4, 4)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(5, 4)).addProperty(WEST_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("K3ZH4R", UNLOCKED, gameInterface), roomController.get(Coordinate.of(5, 4)), roomController.get(Coordinate.of(6, 4)), gameInterface);
			roomController.get(Coordinate.of(5, 4)).addProperty(EAST_DOOR_NAME, door);
			roomController.get(Coordinate.of(6, 4)).addProperty(WEST_DOOR_NAME, door);

			// Vertical

			door = doorFactory(OPEN, lockFactory("KEFVH2", UNLOCKED, gameInterface), roomController.get(Coordinate.of(3, 4)), roomController.get(Coordinate.of(3, 5)), gameInterface);
			roomController.get(Coordinate.of(3, 4)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(3, 5)).addProperty(SOUTH_DOOR_NAME, door);

			door = doorFactory(OPEN, lockFactory("KVW42D", UNLOCKED, gameInterface), roomController.get(Coordinate.of(4, 4)), roomController.get(Coordinate.of(4, 5)), gameInterface);
			roomController.get(Coordinate.of(4, 4)).addProperty(NORTH_DOOR_NAME, door);
			roomController.get(Coordinate.of(4, 5)).addProperty(SOUTH_DOOR_NAME, door);

			Game game = new Game(gameInterface, roomController, 1);
			game.addFaction(new Escapees(new HumanPlayer(), roomController.get(Coordinate.of(4, 1))));
			game.addFaction(new SecretPolice(new ComputerPlayer(), roomController.get(Coordinate.of(4, 5))));
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
	 * Called when the {@link Game} is constructed.
	 *
	 * @param game               The newly constructed {@link Game}.
	 * @param roomController     The {@link RoomController} containing the {@link Room}s in the
	 *                           {@link Game}.
	 * @param numberOfCharacters The number {@link java.lang.Character}s in each {@link Faction}.
	 */
	@Override public void onInit(Game game, RoomController roomController, int numberOfCharacters)
	{

	}

	/**
	 * Called when a new {@link Faction} joins the {@link Game}.
	 *
	 * @param game    The {@link Game} instance.
	 * @param faction The {@link Faction}.
	 */
	@Override public void onFactionJoin(Game game, Faction faction)
	{
		printer.println("Faction " + faction.getLeader());
	}

	/**
	 * Lets the {@link Player} create the {@link Character} they control.
	 *
	 * @param player             The {@link Player} creating the {@link Character}s.
	 * @param numberOfCharacters The number of {@link Character}s to create.
	 * @param creationCallback   The callback to use to add a {@link Character} creation.
	 * @param finishCallback     The callback to use to finish the {@link Character} creation.
	 */
	@Override
	public void onCharacterCreation(Player player, int numberOfCharacters, CharacterCreationCallback creationCallback,
	                                FinishCharacterCreationCallback finishCallback)
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
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override
	public void onGameStart(Game game, Faction faction)
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
	 * Called when the {@link Game} ends.
	 *
	 * @param game    The {@link Game} instance.
	 * @param faction The {@link Faction}.
	 * @param result  The result for the {@link Faction}. True for win, false for less.
	 */
	@Override public void onGameEnd(Game game, Faction faction, boolean result)
	{
		System.out.println("The game ended " + faction.getClass().getSimpleName() + result);
	}

	@Override public void onCharacterDies(Player player, Character character, DamageSource damageSource)
	{
		printer.println(String.format("%s died from %s.", character.getName(), damageSource.getClass().getSimpleName()));
	}

	/**
	 * Event when a {@link Player} starts their turn.
	 *
	 * @param game    The {@link Game} instance.
	 * @param faction The {@link Faction} whose turn it is.
	 */
	@Override public void onTurnStart(Game game, Faction faction)
	{

	}

	/**
	 * Event when a {@link Player} ends their turn.
	 *
	 * @param game    The {@link Game} instance.
	 * @param faction The {@link Faction} whose turn ended.
	 */
	@Override public void onTurnEnd(Game game, Faction faction)
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
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	@Override public void onRoomInspect(Character character, InspectRoomAction action)
	{
		action.onSuccess(() -> {
			printer.println("You inspect the door.");
			action.getCharacters().forEach(c -> {
				printer.println(String.format("In the room with you is %s from faction %s.", c.getName(), c
						.getFaction().toString()));
			});
		});
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

		System.out.println("Exception " + action.getException());
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

		action.onException(NotEnoughOptionsException.class, e -> {
			printer.println("You have no keys.");
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

		action.onException(NotEnoughOptionsException.class, e -> {
			printer.println("You have no keys.");
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
			printer.println(String.format("Your backpack was successfully expanded to %d positions.", action.getBackpack().getNumberOfPositions()));
		});

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

	/**
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction}, {@link UnlockLockAction}.
	 *
	 * @param state         The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock          The {@link Lock} placed on the {@link Door}.
	 * @param roomA         The first room (<code>roomA</code>).
	 * @param roomB         The second room (<code>roomB</code>).
	 * @param gameInterface The {@link GameInterface}.
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	public static Door doorFactory(Door.State state, Lock lock, Room roomA, Room roomB, GameInterface gameInterface)
	{
		BaseDoor door = new BaseDoor(state, lock, roomA, roomB);

		door.addAction("open", new OpenDoorAction(door, gameInterface::onDoorOpen));
		door.addAction("close", new CloseDoorAction(door, gameInterface::onDoorClose));
		door.addAction("use", new UseDoorAction(door, gameInterface::onDoorUse));
		door.addAction("inspect", new InspectDoorAction(door, gameInterface::onDoorInspect));
		door.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		door.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));

		door.addProperty("lock", lock);

		return door;
	}

	/**
	 * Creates a new {@link Room} with a provided {@link Floor}.
	 *
	 * @param name        The name of the {@link Room}
	 * @param description The description of the {@link Room}.
	 * @param floor       The {@link Floor} of the {@link Room}.
	 * @return The newly created {@link Room}.
	 */
	public static Room roomFactory(String name, String description, Floor floor)
	{
		return new BaseRoom(name, description, floor);
	}

	/**
	 * Creates a new {@link Room}.
	 *
	 * @param name        The name of the {@link Room}
	 * @param description The description of the {@link Room}.
	 * @return The newly created {@link Room}.
	 */
	public static Room roomFactory(String name, String description)
	{
		return roomFactory(name, description, new Floor());
	}

	/**
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction}, {@link UnlockLockAction}.
	 *
	 * @param state         The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock          The {@link Lock} placed on the {@link Door}.
	 * @param gameInterface The {@link GameInterface}.
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	public static Door doorFactory(Door.State state, Lock lock, GameInterface gameInterface)
	{
		return doorFactory(state, lock, null, null, gameInterface);
	}

	/**
	 * Creates and returns a {@link Lock} with the {@link LockLockAction}, {@link UnlockLockAction} and
	 * {@link InspectLockAction}.
	 *
	 * @param code          The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 *                      matching codes.
	 * @param state         The state of the {@link Lock}.
	 * @param gameInterface The {@link GameInterface}.
	 * @return The newly created {@link Lock}.
	 */
	public static Lock lockFactory(String code, Lock.State state, GameInterface gameInterface)
	{
		Lock lock = new Lock(code, state);

		lock.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		lock.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));
		lock.addAction("inspect", new InspectLockAction(lock, gameInterface::onLockInspect));

		return lock;
	}

	/**
	 * Creates and returns a new {@link Chest} with the {@link OpenChestAction}, {@link CloseChestAction},
	 * {@link InspectChestAction}, {@link TakeItemFromChestAction}, {@link TakeItemFromChestAction},
	 * {@link LockLockAction}, {@link LockLockAction} and {@link UnlockLockAction}.
	 *
	 * @param countPositions The number of positions available in the {@link Chest}.
	 * @param state          The {@link Chest.State} of the {@link Chest}.
	 * @param lock           The {@link Lock} on the {@link Chest}.
	 * @param gameInterface  The {@link GameInterface}.
	 * @return The newly created {@link Chest}.
	 */
	public static Chest chestFactory(int countPositions, Chest.State state, Lock lock, GameInterface gameInterface)
	{
		Chest chest = new Chest(countPositions, state, lock);

		chest.addAction("open", new OpenChestAction(chest, gameInterface::onChestOpen));
		chest.addAction("close", new CloseChestAction(chest, gameInterface::onChestClose));
		chest.addAction("inspect", new InspectChestAction(chest, gameInterface::onChestInspect));
		chest.addAction("take", new TakeItemFromChestAction(chest, gameInterface::onChestTake));
		chest.addAction("deposit", new DepositItemsIntoChestAction(chest, gameInterface::onChestDeposit));
		chest.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		chest.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));

		chest.addProperty("lock", lock);

		return chest;
	}
}
