package textadventure.actions;

import textadventure.characters.Character;
import textadventure.characters.*;
import textadventure.combat.AttackAction;
import textadventure.doors.CloseDoorAction;
import textadventure.doors.InspectDoorAction;
import textadventure.doors.OpenDoorAction;
import textadventure.doors.UseDoorAction;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;
import textadventure.ui.*;

public class SomeActionResponses implements ActionResponses
{
	@Override
	public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
	{

	}

	@Override public void onAttackAction(Character character, AttackAction action)
	{

	}

	@Override public void onEscapeAction(Character character, EscapeAction action)
	{

	}

	@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
	{

	}

	@Override public void onUseItemAction(Character character, UseItemsAction action)
	{

	}

	@Override public void onEquipAction(Character character, EquipAction action)
	{

	}

	@Override public void onUnEquipAction(Character character, UnEquipAction action)
	{

	}

	@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
	{

	}

	@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
	{

	}

	@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
	{

	}

	@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
	{

	}

	@Override public void onUseDoorAction(Character character, UseDoorAction action)
	{

	}

	@Override public void onInspectDoorAction(Character character, InspectDoorAction action)
	{

	}

	@Override public void onLockLockAction(Character character, LockLockAction action)
	{

	}

	@Override public void onUnlockLockAction(Character character, UnlockLockAction action)
	{

	}

	@Override public void onInspectLockAction(Character character, InspectLockAction action)
	{

	}

	@Override public void onOpenChestAction(Character character, OpenChestAction action)
	{

	}

	@Override public void onCloseChestAction(Character character, CloseChestAction action)
	{

	}

	@Override public void onInspectChestAction(Character character, InspectChestAction action)
	{

	}

	@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
	{

	}

	@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
	{

	}

	@Override public void onInspectBackpackAction(Character character, InspectBackpackAction action)
	{

	}

	@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
	{

	}

	@Override public void onDropItemAction(Character character, DropItemAction action)
	{

	}

	@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
	{

	}
}
