package textadventure.ui;

import textadventure.Character;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.combat.DamageSource;
import textadventure.doors.CloseDoorAction;
import textadventure.doors.InspectDoorAction;
import textadventure.doors.OpenDoorAction;
import textadventure.doors.UseDoorAction;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.backpack.PickUpItemAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;
import textadventure.rooms.InspectFloorAction;
import textadventure.rooms.InspectRoomAction;
import textadventure.ui.characterSelection.CharacterCreationCallback;
import textadventure.ui.characterSelection.FinishCharacterCreationCallback;

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
	 * @param player The {@link Player} who joined the {@link Game}.
	 */
	void onPlayerJoin(Game game, Player player);

	/**
	 * Lets the {@link Player} create the {@link Character} they control.
	 *
	 * @param player           The {@link Player} creating the {@link Character}s.
	 * @param minimum          The minimum amount of {@link Character}s to create.
	 * @param maximum          The maximum amount of {@link Character}s to create.
	 * @param creationCallback The callback to use to add a {@link Character} creation.
	 * @param finishCallback   The callback to use to finish the {@link Character} creation.
	 */
	void onCharacterCreation(Player player, int minimum, int maximum, CharacterCreationCallback creationCallback,
	                         FinishCharacterCreationCallback finishCallback);

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
	 * Called when a {@link Character} dies.
	 *
	 * @param player       The {@link Player} controlling the {@link Character}.
	 * @param character    The {@link Character} who died.
	 * @param damageSource The {@link DamageSource} that killed them.
	 */
	void onCharacterDies(Player player, Character character, DamageSource damageSource);

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
	 * Called when a {@link Character} requests an {@link Action} from the {@link GameInterface}.
	 *
	 * @param character The {@link Character} who requests the {@link Action}.
	 * @param response  The {@link ActionRequestCallback} to send with.
	 */
	void onActionRequest(Character character, ActionRequestCallback response);

	/**
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	void onRoomInspect(Character character, InspectRoomAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	void onFloorInspect(Character character, InspectFloorAction action);

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	void onDoorOpen(Character character, OpenDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link CloseDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	void onDoorClose(Character character, CloseDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	void onDoorUse(Character character, UseDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	void onDoorInspect(Character character, InspectDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	void onLockLock(Character character, LockLockAction action);

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	void onLockUnlock(Character character, UnlockLockAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	void onLockInspect(Character character, InspectLockAction action);

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	void onChestOpen(Character character, OpenChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link CloseChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	void onChestClose(Character character, CloseChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	void onChestInspect(Character character, InspectChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	void onChestTake(Character character, TakeItemFromChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	void onChestDeposit(Character character, DepositItemsIntoChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectBackpackAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	void onBackpackInspect(Character character, InspectBackpackAction action);

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	void onBackpackExpand(Character character, ExpandBackpackAction action);

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	void onItemDrop(Character character, DropItemAction action);

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who attempted to perform the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	void onItemPickup(Character character, PickUpItemAction action);

	/**
	 * Prompts the character to select one or more {@link Option}.
	 *
	 * @param character The {@link Character} selecting.
	 * @param select    The {@link Select} object.
	 */
	void select(Character character, Select select);
}
