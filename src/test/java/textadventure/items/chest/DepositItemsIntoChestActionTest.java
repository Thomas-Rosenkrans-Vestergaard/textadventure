package textadventure.items.chest;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.SomePlayer;
import textadventure.actions.ActionResponses;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.combat.SomeFaction;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.lock.Lock;
import textadventure.lock.SomeLock;
import textadventure.select.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class DepositItemsIntoChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		Item          item      = new SomeItem();
		Backpack      backpack  = new Backpack();
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		backpack.addItem(item, 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
			{
				try {
					assertFalse(action.hasException());
					assertEquals(1, chest.getNumberOfItems());
					assertEquals(0, backpack.getNumberOfItems());
					assertEquals(item, action.getItems().get(0));
					assertEquals(item, action.getChest().getItem(0));
				} catch (Exception e) {
					fail();
				}
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onDepositItemsIntoChestAction(same(character), same(action));
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		Backpack    backpack = new Backpack();
		SomeFaction faction  = new SomeFaction();
		faction.setPlayer(new SomePlayer());
		SomeCharacter character = new SomeCharacter();
		character.setFaction(faction);
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.CLOSED, new SomeLock());

		backpack.addItem(new SomeItem(), 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
			{
				assertTrue(action.hasException(ChestClosedException.class));
				assertEquals(0, action.getItems().size());
				assertEquals(0, chest.getNumberOfItems());
				assertEquals(1, backpack.getNumberOfItems());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onDepositItemsIntoChestAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new DepositItemsIntoChestAction(null));
	}
}