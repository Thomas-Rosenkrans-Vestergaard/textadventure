package textadventure.items.chest;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.lock.Lock;
import textadventure.ui.*;

import static org.junit.Assert.*;

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
		action.perform(character, new SomeActionResponses()
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
		});
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		Backpack      backpack  = new Backpack();
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.CLOSED, null);

		backpack.addItem(new SomeItem(), 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
			{
				assertTrue(action.hasException(ChestClosedException.class));
				assertEquals(0, action.getItems().size());
				assertEquals(0, chest.getNumberOfItems());
				assertEquals(1, backpack.getNumberOfItems());
			}
		});
	}

	@Test
	public void performArguments() throws Exception
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
		action.perform(character, new SomeActionResponses()
		{
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
		});
	}

	@Test
	public void performArgumentsThrowsNumberFormatException() throws Exception
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

		action.perform(character, new SomeActionResponses()
		{
			@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
			{
				assertTrue(action.hasException(NumberFormatException.class));
				assertEquals(0, action.getItems().size());
				assertEquals(0, chest.getNumberOfItems());
				assertEquals(1, backpack.getNumberOfItems());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new DepositItemsIntoChestAction(null));
	}
}