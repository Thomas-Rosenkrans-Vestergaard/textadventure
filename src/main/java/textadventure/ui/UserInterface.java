package textadventure.ui;

import textadventure.ActionResponse;
import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.doors.Door;
import textadventure.items.inventory.Inventory;
import textadventure.lock.Lock;
import textadventure.rooms.Room;

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
	 * @param response The {@link ActionResponse} to respond with.
	 */
	void onActionRequest(Game game, Player player, ActionResponse response);

	void onDoorOpen(Game game, Player player, Door door);

	void onDoorClose(Game game, Player player, Door door);

	void onDoorEnter(Game game, Player player, Door door, Room room);

	void onDoorInspect(Game game, Player player, Door door);

	void onLockLock(Game game, Player player, Lock lock);

	void onLockUnlock(Game game, Player player, Lock lock);

	void onLockInspect(Game game, Player player, Lock lock);

	/**
	 * Requests a {@link Select} option {@link UserInterface}.
	 *
	 * @param message  The message to display before the {@link Player} can select.
	 * @param select   The {@link Select}.
	 * @param player   The {@link Player} selecting.
	 * @param callback The callback to use to return the selected element.
	 */
	<T extends Option> void select(String message, Select<T> select, Player player, SelectResponse<T> callback);

	/**
	 * Shows the provided {@link Inventory} in the {@link UserInterface}.
	 *
	 * @param header    The header of the item list.
	 * @param inventory The {@link Inventory} to show.
	 */
	void showInventory(String header, Inventory inventory);
}
