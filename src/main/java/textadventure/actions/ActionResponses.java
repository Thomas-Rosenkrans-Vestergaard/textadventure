package textadventure.actions;

import textadventure.characters.Character;
import textadventure.characters.CharacterInformationAction;
import textadventure.combat.AttackAction;
import textadventure.doors.CloseDoorAction;
import textadventure.doors.InspectDoorAction;
import textadventure.doors.OpenDoorAction;
import textadventure.doors.UseDoorAction;
import textadventure.items.EquipAction;
import textadventure.items.UnEquipAction;
import textadventure.items.UseItemsAction;
import textadventure.characters.DropItemAction;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.characters.PickUpItemAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;
import textadventure.rooms.InspectFloorAction;
import textadventure.rooms.InspectRoomAction;
import textadventure.ui.*;

public interface ActionResponses
{

	/**
	 * Event for when a {@link textadventure.Player} is required to {@link Select} between one or more
	 * {@link textadventure.ui.Option}s.
	 *
	 * @param select The {@link Select}.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link textadventure.ui.Select.SelectResponse}.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 */
	void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException;

	/**
	 * Event for when the {@link Character} performs the {@link AttackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link AttackAction}.
	 * @param action    The {@link AttackAction} instance.
	 */
	void onAttackAction(Character character, AttackAction action);

	/**
	 * Event for when the {@link Character} performs the {@link CharacterInformationAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CharacterInformationAction}.
	 * @param action    The {@link CharacterInformationAction} instance.
	 */
	void onCharacterInformationAction(Character character, CharacterInformationAction action);

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsAction}.
	 * @param action    The {@link UseItemsAction} instance.
	 */
	void onUseItemAction(Character character, UseItemsAction action);

	/**
	 * Event for when the {@link Character} performs the {@link EquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EquipAction}.
	 * @param action    The {@link EquipAction} instance.
	 */
	void onEquipAction(Character character, EquipAction action);

	/**
	 * Event for when the {@link Character} performs the {@link UnEquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnEquipAction}.
	 * @param action    The {@link UnEquipAction} instance.
	 */
	void onUnEquipAction(Character character, UnEquipAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	void onInspectRoomAction(Character character, InspectRoomAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	void onInspectFloorAction(Character character, InspectFloorAction action);

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	void onOpenDoorAction(Character character, OpenDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link CloseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	void onCloseDoorAction(Character character, CloseDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	void onUseDoorAction(Character character, UseDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	void onInspectDoorAction(Character character, InspectDoorAction action);

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	void onLockLockAction(Character character, LockLockAction action);

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	void onUnlockLockAction(Character character, UnlockLockAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	void onInspectLockAction(Character character, InspectLockAction action);

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	void onOpenChestAction(Character character, OpenChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link CloseChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	void onCloseChestAction(Character character, CloseChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	void onInspectChestAction(Character character, InspectChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action);

	/**
	 * Event when a {@link Character} performs the {@link InspectBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	void onInspectBackpackAction(Character character, InspectBackpackAction action);

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	void onExpandBackpackAction(Character character, ExpandBackpackAction action);

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	void onDropItemAction(Character character, DropItemAction action);

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	void onPickUpItemAction(Character character, PickUpItemAction action);
}
