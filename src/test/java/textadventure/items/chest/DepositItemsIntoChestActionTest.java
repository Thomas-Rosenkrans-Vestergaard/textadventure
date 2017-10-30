package textadventure.items.chest;

import org.junit.Test;
import textadventure.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.lock.Lock;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class DepositItemsIntoChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface()
		{
			@Override public void select(Character character, Select select)
			{
				try {
					select.selectIndex(0);
				} catch (Exception e) {
					fail();
				}
			}
		};

		Item          item      = new SomeItem();
		Backpack      backpack  = new Backpack();
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		backpack.addItem(item, 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest, ((characterResponse, actionResponse) -> {
			try {
				assertSame(character, characterResponse);
				assertSame(chest, actionResponse.getChest());
				assertFalse(actionResponse.hasException());
				assertEquals(1, chest.getNumberOfItems());
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(item, actionResponse.getItems().get(0));
				assertEquals(item, actionResponse.getChest().getItem(0));
			} catch (Exception e) {
				fail();
			}
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface();
		Backpack      backpack      = new Backpack();
		SomeCharacter character     = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.CLOSED, null);

		backpack.addItem(new SomeItem(), 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(ChestClosedException.class));
			assertEquals(0, actionResponse.getItems().size());
			assertEquals(0, chest.getNumberOfItems());
			assertEquals(1, backpack.getNumberOfItems());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performArguments() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface();
		Item          item          = new SomeItem();
		Backpack      backpack      = new Backpack();
		SomeCharacter character     = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		backpack.addItem(item, 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest, ((characterResponse, actionResponse) -> {
			try {
				assertSame(character, characterResponse);
				assertSame(chest, actionResponse.getChest());
				assertFalse(actionResponse.hasException());
				assertEquals(1, chest.getNumberOfItems());
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(item, actionResponse.getItems().get(0));
				assertEquals(item, actionResponse.getChest().getItem(0));
			} catch (Exception e) {
				fail();
			}
		}));

		action.perform(gameInterface, character, new String[]{"0"});
	}

	@Test
	public void performArgumentsThrowsNumberFormatException() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface();
		Item          item          = new SomeItem();
		Backpack      backpack      = new Backpack();
		SomeCharacter character     = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		backpack.addItem(item, 0);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, chest.getNumberOfItems());

		DepositItemsIntoChestAction action = new DepositItemsIntoChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(NumberFormatException.class));
			assertEquals(0, actionResponse.getItems().size());
			assertEquals(0, chest.getNumberOfItems());
			assertEquals(1, backpack.getNumberOfItems());
		}));

		action.perform(gameInterface, character, new String[]{"NOT_INTEGER"});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new DepositItemsIntoChestAction(null, null));
	}
}