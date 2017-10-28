package textadventure.ui;

import textadventure.Character;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionRequestCallback;
import textadventure.doors.CloseDoorAction;
import textadventure.doors.InspectDoorAction;
import textadventure.doors.OpenDoorAction;
import textadventure.doors.UseDoorAction;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.backpack.PickupItemAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;

public class MockGameInterface implements GameInterface
{

	@Override public void onInit(Game game)
	{

	}

	@Override public void onPlayerJoin(Game game, Player player)
	{

	}

	@Override public void onGameStart(Game game)
	{

	}

	@Override public void onGameEnd(Game game)
	{

	}

	@Override public void onTurnStart(Game game, Player player)
	{

	}

	@Override public void onTurnEnd(Game game, Player player)
	{

	}

	@Override public void onActionRequest(Character character, ActionRequestCallback response)
	{

	}

	@Override public void onDoorOpen(Character character, OpenDoorAction action)
	{

	}

	@Override public void onDoorClose(Character character, CloseDoorAction action)
	{

	}

	@Override public void onDoorUse(Character character, UseDoorAction action)
	{

	}

	@Override public void onDoorInspect(Character character, InspectDoorAction action)
	{

	}

	@Override public void onLockLock(Character character, LockLockAction action)
	{

	}

	@Override public void onLockUnlock(Character character, UnlockLockAction action)
	{

	}

	@Override public void onLockInspect(Character character, InspectLockAction action)
	{

	}

	@Override public void onChestOpen(Character character, OpenChestAction action)
	{

	}

	@Override public void onChestClose(Character character, CloseChestAction action)
	{

	}

	@Override public void onChestInspect(Character character, InspectChestAction action)
	{

	}

	@Override public void onChestTake(Character character, TakeItemFromChestAction action)
	{

	}

	@Override public void onChestDeposit(Character character, DepositItemsIntoChestAction action)
	{

	}

	@Override public void onBackpackInspect(Character character, InspectBackpackAction action)
	{

	}

	@Override public void onBackpackExpand(Character character, ExpandBackpackAction action)
	{

	}

	@Override public void onItemDrop(Character character, DropItemAction action)
	{

	}

	@Override public void onItemPickup(Character character, PickupItemAction action)
	{

	}

	@Override public void select(Character character, Select select)
	{

	}
}
