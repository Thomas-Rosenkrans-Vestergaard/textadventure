package textadventure.ui;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.openable.Openable;
import textadventure.openable.OpenableException;
import textadventure.openable.doors.Door;
import textadventure.lock.Lock;

import java.util.function.Consumer;

public interface UserInterface
{

	void write(String message);

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
	 * Called when a {@link Player} requests an {@link Action} from the {@link UserInterface}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param callback The callback to respond with.
	 */
	void onActionRequest(Game game, Player player, Consumer<Action> callback);

	/**
	 * Called when a {@link Player} responds to a request for {@link Action}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who responded to a request for {@link Action}.
	 * @param action The chosen {@link Action}.
	 */
	void onActionResponse(Game game, Player player, Action action);

	/**
	 * Requests a {@link Select} option {@link UserInterface}.
	 *
	 * @param select   The {@link Select}.
	 * @param player   The {@link Player} selecting.
	 * @param callback The callback to use to return the selected element.
	 */
	<T> void select(Select<T> select, Player player, SelectCallback<T> callback);

	/**
	 * Event when the provided {@link Player} opens an {@link Openable} object.
	 *
	 * @param game     The {@link Game} instance.
	 * @param openable The {@link Openable} object.
	 * @param player   The {@link Player} who opened the {@link Openable} object.
	 */
	void onOpen(Game game, Openable openable, Player player);

	/**
	 * Event when the provided {@link Player} closes an {@link Openable} object.
	 *
	 * @param game     The {@link Game} instance.
	 * @param openable The {@link Openable} object.
	 * @param player   The {@link Player} who closed the {@link Openable} object.
	 */
	void onClose(Game game, Openable openable, Player player);

	/**
	 * Event when the provided {@link Player} encounters an {@link Exception} while using a {@link Openable} object.
	 *
	 * @param game   The {@link Game} instance.
	 * @param e      The {@link OpenableException} that was thrown.
	 * @param player The {@link Player} who encountered the {@link OpenableException}.
	 */
	void onOpenException(Game game, OpenableException e, Player player);

	/**
	 * Event when the provided {@link Player} encounters an {@link Exception} while using a {@link Openable} object.
	 *
	 * @param game   The {@link Game} instance.
	 * @param e      The {@link OpenableException} that was thrown.
	 * @param player The {@link Player} who encountered the {@link OpenableException}.
	 */
	void onCloseException(Game game, OpenableException e, Player player);

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param door   The {@link Door} that was inspected.
	 * @param player
	 */
	void onDoorInspect(Game game, Door door, Player player);

	/**
	 * Called when the provided {@link Player} inspects the provided {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param lock   The {@link Lock}.
	 * @param player The {@link Player} who inspected the {@link Lock}.
	 */
	void onLockInspect(Game game, Lock lock, Player player);

	/**
	 * Called when the provided {@link Player} locks the provided {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param lock   The {@link Lock}.
	 * @param player The {@link Player} who locked the lock.
	 */
	void onLockLock(Game game, Lock lock, Player player);

	/**
	 * Called when the provided {@link Player} unlocks the provided {@link Lock}.
	 *
	 * @param game
	 * @param lock
	 * @param player
	 */
	void onLockUnlock(Game game, Lock lock, Player player);

	void onLockAlreadyLocked(Game game, Lock lock, Player player);

	void onLockAlreadyUnlocked(Game game, Lock lock, Player player);
}
