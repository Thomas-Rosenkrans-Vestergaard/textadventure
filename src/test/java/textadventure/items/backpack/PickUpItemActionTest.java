package textadventure.items.backpack;

import org.junit.Test;
import textadventure.BaseCharacter;
import textadventure.Character;
import textadventure.actions.ActionTest;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.inventory.MockItem;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.MockGameInterface;
import textadventure.ui.Select;

import static org.junit.Assert.*;

public class PickUpItemActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack  backpack        = new Backpack();
		Item      item            = new MockItem();
		Room      currentLocation = new BaseRoom(null, null);
		Floor     floor           = currentLocation.getRoomFloor();
		Character character       = new BaseCharacter(null, backpack, currentLocation);

		floor.addItem(item);

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

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, floor.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(backpack, actionResponse.getBackpack());
			assertFalse(actionResponse.hasException());
			assertEquals(0, floor.getNumberOfItems());
			assertEquals(1, backpack.getNumberOfItems());
			assertEquals(1, actionResponse.getItems().size());

			try {
				assertSame(item, backpack.getItem(0));
			} catch (Exception e) {
				fail();
			}
		});

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performArgument() throws Exception
	{
		Backpack      backpack        = new Backpack(5);
		Item          a               = new MockItem();
		Item          b               = new MockItem();
		Room          currentLocation = new BaseRoom(null, null);
		Floor         floor           = currentLocation.getRoomFloor();
		Character     character       = new BaseCharacter(null, backpack, currentLocation);
		GameInterface gameInterface   = new MockGameInterface();

		floor.addItem(a, 0);
		floor.addItem(b, 1);

		assertEquals(2, floor.getNumberOfItems());
		assertEquals(0, backpack.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(backpack, actionResponse.getBackpack());
			assertFalse(actionResponse.hasException());
			assertEquals(0, floor.getNumberOfItems());
			assertEquals(2, backpack.getNumberOfItems());
			assertEquals(2, actionResponse.getItems().size());

			try {
				assertSame(b, backpack.takeItem(0));
				assertSame(a, backpack.takeItem(0));
			} catch (Exception e) {
				fail();
			}
		});

		action.perform(gameInterface, character, new String[]{"0", "1"});
	}

	@Test
	public void performArgumentThrowsNumberFormatException() throws Exception
	{
		Backpack      backpack        = new Backpack(5);
		Room          currentLocation = new BaseRoom(null, null);
		Floor         floor           = currentLocation.getRoomFloor();
		Character     character       = new BaseCharacter(null, backpack, currentLocation);
		GameInterface gameInterface   = new MockGameInterface();
		Item          item            = new MockItem();
		backpack.addItem(item, 0);
		backpack.addItem(item, 1);
		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(backpack, actionResponse.getBackpack());
			assertTrue(actionResponse.hasException(NumberFormatException.class));
			assertEquals(2, backpack.getNumberOfItems());
			assertEquals(0, floor.getNumberOfItems());
			assertEquals(0, actionResponse.getItems().size());
		});

		action.perform(gameInterface, character, new String[]{"NOT AN INTEGER"}); // <-----------
	}

	@Test
	public void performArgumentThrowsInventoryFullException() throws Exception
	{
		Backpack      backpack        = new Backpack(0); // <------
		Room          currentLocation = new BaseRoom(null, null);
		Floor         floor           = currentLocation.getRoomFloor();
		Character     character       = new BaseCharacter(null, backpack, currentLocation);
		GameInterface gameInterface   = new MockGameInterface();

		floor.addItem(new MockItem(), 0);
		floor.addItem(new MockItem(), 1);

		assertEquals(2, floor.getNumberOfItems());
		assertEquals(0, backpack.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(backpack, actionResponse.getBackpack());
			assertTrue(actionResponse.hasException(InventoryFullException.class));
			assertEquals(2, floor.getNumberOfItems());
			assertEquals(0, backpack.getNumberOfItems());
			assertEquals(0, actionResponse.getItems().size());
		});

		action.perform(gameInterface, character, new String[]{"0", "1"});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new PickUpItemAction(null, null));
	}
}