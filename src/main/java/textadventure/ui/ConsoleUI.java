package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.*;
import textadventure.doors.Door;
import textadventure.lock.Lock;
import textadventure.rooms.Room;

import java.util.*;
import java.util.function.Consumer;

public class ConsoleUI implements UI
{

	private Scanner scanner = new Scanner(System.in);

	/**
	 * Called when the {@link Game} is constructed.
	 *
	 * @param game The newly constructed {@link Game}.
	 */
	@Override public void onInit(Game game)
	{

	}

	/**
	 * Called when a new {@link Player} joins the {@link Game}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The newly joined {@link Player}.
	 */
	@Override public void onPlayer(Game game, Player player)
	{

	}

	/**
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override public void onGameStart(Game game)
	{
		System.out.println(game.getMaze().getStartingRoom().getStartingMessage());
	}

	/**
	 * Called when the {@link Game} ends.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override public void onGameEnd(Game game)
	{

	}

	/**
	 * Called when the {@link Game} is prompted to quit.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override public void onQuit(Game game)
	{

	}

	/**
	 * Called when the turn rotates.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	@Override public void onTurnStart(Game game, Player player)
	{

	}

	/**
	 * Called when a {@link Player} loses their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	@Override public void onTurnEnd(Game game, Player player)
	{

	}

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link UI}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param callback The callback to respond with.
	 */
	@Override public void onActionRequest(Game game, Player player, Consumer<Action> callback)
	{
		Room currentRoom = player.getCharacter().getCurrentLocation();

		while (true) {
			System.out.println("What would you like to do?");
			String[] sections = scanner.nextLine().split(" ");

			if (sections.length < 2) {
				System.out.println("Less than two sections.");
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
	@Override public void onActionResponse(Game game, Player player, Action action)
	{

	}

	/**
	 * Requests a {@link Option} option from the {@link UI}.
	 *
	 * @param select   The {@link Select} to select the {@link Option}s from.
	 * @param player   The {@link Player} to request a {@link Option} element from.
	 * @param callback The callback to use when responding to the select request.
	 */
	@Override public /*<T extends Option>*/ void select(Select select, Player player, Consumer<Option> callback)
	{
		System.out.println("Select");
		ImmutableMap<String, ? extends Option> options = select.getOptions();
		for (Map.Entry<String, ? extends Option> entry : options.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue().getName());
		}

		String choice = scanner.nextLine();
		if (!options.containsKey(choice)) {
			System.out.println("Unknown select.");
			return;
		}

		callback.accept(options.get(choice));
	}

	/**
	 * Called when the provided {@link Player} closes the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was closed.
	 * @param player The {@link Player} who closed the {@link Door}.
	 */
	@Override public void onCloseDoor(Game game, Door door, Player player)
	{
		String message = String.format("%s closed the door.", player.getCharacter().getName());

		System.out.println(message);
	}

	/**
	 * Called when the provided {@link Player} opens the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was opened.
	 * @param player The {@link Player} who opened the {@link Door}.
	 */
	@Override public void onOpenDoor(Game game, Door door, Player player)
	{
		String message = String.format("%s opened the door", player.getCharacter().getName());

		System.out.println(message);
	}

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was inspected.
	 * @param player
	 */
	@Override public void onDoorInspect(Game game, Door door, Player player)
	{
		String message = String.format("%s inspected the door. While inspecting the door %s discovers that the door " +
									   "is %s.", player.getCharacter().getName(), player.getCharacter().getName(),
									   door.getState().name().toLowerCase()
		);

		System.out.println(message);
	}

	@Override public void onDoorAlreadyClosed(Game game, Door door, Player player)
	{
		System.out.println("Door is already closed.");
	}

	@Override public void onDoorAlreadyOpen(Game game, Door door, Player player)
	{
		System.out.println("Door is already open.");
	}

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param lock   The {@link Lock}.
	 * @param player The {@link Player} who inspected the {@link Lock}.
	 */
	@Override public void onLockInspect(Game game, Lock lock, Player player)
	{
		String message = String.format("%s inspected the lock. While inspecting the lock %s descovers the the lock " +
									   "is %s. Written on the lock is the code %d.", player.getCharacter().getName(),
									   player.getCharacter().getName(), lock.getState().name().toLowerCase(),
									   lock.getCode()
		);

		System.out.println(message);
	}

	/**
	 * Called when the provided {@link Player} locks the provided {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param lock   The {@link Lock}.
	 * @param player The {@link Player} who locked the lock.
	 */
	@Override public void onLockLock(Game game, Lock lock, Player player)
	{
		System.out.println("lock");
	}

	/**
	 * Called when the provided {@link Player} unlocks the provided {@link Lock}.
	 *
	 * @param game
	 * @param lock
	 * @param player
	 */
	@Override public void onLockUnlock(Game game, Lock lock, Player player)
	{
		System.out.println("unlock");
	}

	@Override public void onLockAlreadyLocked(Game game, Lock lock, Player player)
	{
		System.out.println("already locked");
	}

	@Override public void onLockAlreadyUnlocked(Game game, Lock lock, Player player)
	{
		System.out.println("already unlocked");
	}
}
