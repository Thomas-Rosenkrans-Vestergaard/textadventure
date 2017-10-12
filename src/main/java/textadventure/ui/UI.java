package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.lock.Lockable;

import java.util.function.Consumer;

public interface UI
{

	/**
	 * Called when the {@link Game} is constructed.
	 *
	 * @param game The newly constructed {@link Game}.
	 */
	void onInit(Game game);

	/**
	 * Called when a new {@link Player} joins the {@link Game}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The newly joined {@link Player}.
	 */
	void onPlayer(Game game, Player player);

	/**
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	void onGameStart(Game game);

	/**
	 * Called when the {@link Game} ends.
	 *
	 * @param game The {@link Game} instance.
	 */
	void onGameEnd(Game game);

	/**
	 * Called when the {@link Game} is prompted to quit.
	 *
	 * @param game The {@link Game} instance.
	 */
	void onQuit(Game game);

	/**
	 * Called when the turn rotates.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	void onTurnStart(Game game, Player player);

	/**
	 * Called when a {@link Player} loses their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	void onTurnEnd(Game game, Player player);

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link UI}.
	 *
	 * @param game      The {@link Game} instance.
	 * @param UIRequest The {@link UIRequest} to respond to.
	 * @param player    The {@link Player} who requests the {@link Action}.
	 */
	void onActionRequest(Game game, UIRequest UIRequest, Player player);

	/**
	 * Called when a {@link Player} responds to a request for {@link Action}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who responded to a request for {@link Action}.
	 * @param action The chosen {@link Action}.
	 */
	void onActionResponse(Game game, Player player, Action action);

	/**
	 * Requests a {@link Selectable} option from the {@link UI}.
	 *
	 * @param selectable The {@link Selectable} options.
	 * @param player     The {@link Player} to request a {@link Selectable} element from.
	 * @param callback   The callback to use when responding to the select request.
	 * @param <T>        The {@link Selectable} element.
	 */
	<T extends Selectable> void select(ImmutableMap<String, T> selectable, Player player, Consumer<T> callback);

	/**
	 * Called when the provided {@link Player} closes the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was closed.
	 * @param player The {@link Player} who closed the {@link Door}.
	 */
	void onCloseDoor(Game game, Door door, Player player);

	/**
	 * Called when the provided {@link Player} opens the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was opened.
	 * @param player The {@link Player} who opened the {@link Door}.
	 */
	void onOpenDoor(Game game, Door door, Player player);

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was inspected.
	 * @param player
	 */
	void onDoorInspect(Game game, Door door, Player player);

	void onLockableInspect(Game game, Lockable lockable, Player player);

	void onLockableLock(Game game, Lockable lockable, Player player);

	void onLockableUnlock(Game game, Lockable lockable, Player player);

	/**
	 * Sends a message to the {@link Player}s in the {@link Game}.
	 *
	 * @param message The {@link UIMessage} to send.
	 * @param player  The {@link Player} to send the {@link UIMessage} to.
	 */
	void onMessage(UIMessage message, Player player);
}
