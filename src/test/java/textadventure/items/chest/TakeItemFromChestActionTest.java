package textadventure.items.chest;

import org.junit.Test;
import textadventure.BaseCharacter;
import textadventure.Character;
import textadventure.actions.ActionTest;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.items.inventory.MockItem;
import textadventure.lock.Lock;
import textadventure.ui.GameInterface;
import textadventure.ui.MockGameInterface;
import textadventure.ui.Select;

import static org.junit.Assert.*;

public class TakeItemFromChestActionTest
{

	@Test
	public void perform() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface()
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

		Item      item      = new MockItem();
		Backpack  backpack  = new Backpack();
		Character character = new BaseCharacter(null, backpack, null);
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		chest.addItem(item, 0);

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest, ((characterResponse, actionResponse) -> {
			try {
				assertSame(character, characterResponse);
				assertSame(chest, actionResponse.getChest());
				assertFalse(actionResponse.hasException());
				assertEquals(0, chest.getNumberOfItems());
				assertEquals(1, backpack.getNumberOfItems());
				assertEquals(item, actionResponse.getItems().get(0));
				assertEquals(item, backpack.getItem(0));
			} catch (Exception e) {
				fail();
			}
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface();
		Backpack      backpack      = new Backpack();
		Character     character     = new BaseCharacter(null, backpack, null);
		Chest         chest         = new Chest(10, Chest.State.CLOSED, null);

		chest.addItem(new MockItem(), 0);

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(ChestClosedException.class));
			assertEquals(0, actionResponse.getItems().size());
			assertEquals(1, chest.getNumberOfItems());
			assertEquals(0, backpack.getNumberOfItems());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performArguments() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface();
		Item          item          = new MockItem();
		Backpack      backpack      = new Backpack();
		Character     character     = new BaseCharacter(null, backpack, null);
		Chest         chest         = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		chest.addItem(item, 0);

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest, ((characterResponse, actionResponse) -> {
			try {
				assertSame(character, characterResponse);
				assertSame(chest, actionResponse.getChest());
				assertFalse(actionResponse.hasException());
				assertEquals(0, chest.getNumberOfItems());
				assertEquals(1, backpack.getNumberOfItems());
				assertEquals(item, actionResponse.getItems().get(0));
				assertEquals(item, backpack.getItem(0));
			} catch (Exception e) {
				fail();
			}
		}));

		action.perform(gameInterface, character, new String[]{"0"});
	}

	@Test
	public void performArgumentsThrowsNumberFormatException() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface();
		Item          item          = new MockItem();
		Backpack      backpack      = new Backpack();
		Character     character     = new BaseCharacter(null, backpack, null);
		Chest         chest         = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		chest.addItem(item, 0);

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(NumberFormatException.class));
			assertEquals(0, actionResponse.getItems().size());
			assertEquals(1, chest.getNumberOfItems());
			assertEquals(0, backpack.getNumberOfItems());
		}));

		action.perform(gameInterface, character, new String[]{"NOT_INTEGER"});
	}

	@Test
	public void performArgumentsThrowsInventoryFullException() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface();
		Item          item          = new MockItem();
		Backpack      backpack      = new Backpack(0);
		Character     character     = new BaseCharacter(null, backpack, null);
		Chest         chest         = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		chest.addItem(item, 0);

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(InventoryFullException.class));
			assertEquals(0, actionResponse.getItems().size());
			assertEquals(1, chest.getNumberOfItems());
			assertEquals(0, backpack.getNumberOfItems());
		}));

		action.perform(gameInterface, character, new String[]{"0"});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new TakeItemFromChestAction(null, null));
	}
}