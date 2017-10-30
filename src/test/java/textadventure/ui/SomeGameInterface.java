package textadventure.ui;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionRequestCallback;
import textadventure.characters.Character;
import textadventure.characters.CharacterInformationAction;
import textadventure.combat.DamageSource;
import textadventure.combat.Faction;
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
import textadventure.rooms.RoomController;
import textadventure.ui.characterSelection.CharacterCreationCallback;
import textadventure.ui.characterSelection.FinishCharacterCreationCallback;

public class SomeGameInterface implements GameInterface
{

	@Override public void onInit(Game game, RoomController roomController, int numberOfCharacters)
	{

	}

	@Override public void onFactionJoin(Game game, Faction faction)
	{

	}

	@Override
	public void onCharacterCreation(Player player, int numberOfCharacters, CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback)
	{

	}

	@Override public void onGameStart(Game game, Faction faction)
	{

	}

	@Override public void onGameEnd(Game game, Faction faction, boolean result)
	{

	}

	@Override public void onTurnStart(Game game, Faction faction)
	{

	}

	@Override public void onTurnEnd(Game game, Faction faction)
	{

	}

	@Override public void onCharacterDies(Player player, Character character, DamageSource damageSource)
	{

	}

	@Override public void onActionRequest(Character character, ActionRequestCallback response)
	{

	}

	@Override public void onCharacterInformation(Character character, CharacterInformationAction action)
	{

	}

	@Override public void onRoomInspect(Character character, InspectRoomAction action)
	{

	}

	@Override public void onFloorInspect(Character character, InspectFloorAction action)
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

	@Override public void onItemPickup(Character character, PickUpItemAction action)
	{

	}

	@Override public void select(Character character, Select select)
	{

	}
}
