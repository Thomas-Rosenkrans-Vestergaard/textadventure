package textadventure.ui;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.doors.CloseDoorAction;
import textadventure.doors.InspectDoorAction;
import textadventure.doors.OpenDoorAction;
import textadventure.doors.UseDoorAction;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.backpack.PickupItemAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;

public interface GameInterface
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
	void onPlayerJoin(Game game, Player player);

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
	 * Event when a {@link Player} starts their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	void onTurnStart(Game game, Player player);

	/**
	 * Event when a {@link Player} ends their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	void onTurnEnd(Game game, Player player);

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link GameInterface}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param response The {@link ActionRequestCallback} to send with.
	 */
	void onActionRequest(Game game, Player player, ActionRequestCallback response);

	/**
	 * Event when a {@link Player} performs the {@link OpenDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link OpenDoorAction}.
	 * @param action The {@link OpenDoorAction} instance.
	 */
	void onDoorOpen(Game game, Player player, OpenDoorAction action);

	/**
	 * Event when a {@link Player} performs the {@link CloseDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link CloseDoorAction}.
	 * @param action The {@link CloseDoorAction} instance.
	 */
	void onDoorClose(Game game, Player player, CloseDoorAction action);

	/**
	 * Event when a {@link Player} performs the {@link UseDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link UseDoorAction}.
	 * @param action The {@link UseDoorAction} instance.
	 */
	void onDoorUse(Game game, Player player, UseDoorAction action);

	/**
	 * Event when a {@link Player} performs the {@link InspectDoorAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectDoorAction}.
	 * @param action The {@link InspectDoorAction} instance.
	 */
	void onDoorInspect(Game game, Player player, InspectDoorAction action);

	/**
	 * Event when a {@link Player} performs the {@link LockLockAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link LockLockAction}.
	 * @param action The {@link LockLockAction} instance.
	 */
	void onLockLock(Game game, Player player, LockLockAction action);

	/**
	 * Event when a {@link Player} performs the {@link UnlockLockAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link UnlockLockAction}.
	 * @param action The {@link UnlockLockAction} instance.
	 */
	void onLockUnlock(Game game, Player player, UnlockLockAction action);

	/**
	 * Event when a {@link Player} performs the {@link InspectLockAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectLockAction}.
	 * @param action The {@link InspectLockAction} instance.
	 */
	void onLockInspect(Game game, Player player, InspectLockAction action);

	/**
	 * Event when a {@link Player} performs the {@link OpenChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link OpenChestAction}.
	 * @param action The {@link OpenChestAction} instance.
	 */
	void onChestOpen(Game game, Player player, OpenChestAction action);

	/**
	 * Event when a {@link Player} performs the {@link CloseChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link CloseChestAction}.
	 * @param action The {@link CloseChestAction} instance.
	 */
	void onChestClose(Game game, Player player, CloseChestAction action);

	/**
	 * Event when a {@link Player} performs the {@link InspectChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectChestAction}.
	 * @param action The {@link InspectChestAction} instance.
	 */
	void onChestInspect(Game game, Player player, InspectChestAction action);

	/**
	 * Event when a {@link Player} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link TakeItemFromChestAction}.
	 * @param action The {@link TakeItemFromChestAction} instance.
	 */
	void onChestTake(Game game, Player player, TakeItemFromChestAction action);

	/**
	 * Event when a {@link Player} performs the {@link DropItemAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link DropItemAction}.
	 * @param action The {@link DropItemAction} instance.
	 */
	void onChestDeposit(Game game, Player player, DepositItemsIntoChestAction action);

	/**
	 * Event when a {@link Player} performs the {@link InspectBackpackAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link InspectBackpackAction}.
	 * @param action The {@link InspectBackpackAction} inst ance.
	 */
	void onBackpackInspect(Game game, Player player, InspectBackpackAction action);

	/**
	 * Event when a {@link Player} performs the {@link DropItemAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link DropItemAction}.
	 * @param action The {@link DropItemAction} instance.
	 */
	void onItemDrop(Game game, Player player, DropItemAction action);

	/**
	 * Event when a {@link Player} performs the {@link PickupItemAction}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who attempted to perform the {@link PickupItemAction}.
	 * @param action The {@link PickupItemAction} instance.
	 */
	void onItemPickup(Game game, Player player, PickupItemAction action);

	/**
	 * Prompts the player to select an {@link Option}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} selecting.
	 * @param select The {@link Select} object.
	 */
	void select(Game game, Player player, Select select);
}
