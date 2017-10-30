package textadventure.items.backpack;

import org.junit.Test;
import textadventure.characters.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class PickUpItemActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack      backpack        = new Backpack();
		Item          item            = new SomeItem();
		Room          currentLocation = new BaseRoom(null, null);
		Floor         floor           = currentLocation.getRoomFloor();
		SomeCharacter character       = new SomeCharacter();
		character.setCurrentLocation(currentLocation);
		character.setBackpack(backpack);

		floor.addItem(item);

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

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, floor.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction((characterResponse, actionResponse) -> {
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
		Item          a               = new SomeItem();
		Item          b               = new SomeItem();
		Room          currentLocation = new BaseRoom(null, null);
		Floor         floor           = currentLocation.getRoomFloor();
		SomeCharacter character       = new SomeCharacter();
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);
		GameInterface gameInterface = new SomeGameInterface();

		floor.addItem(a, 0);
		floor.addItem(b, 1);

		assertEquals(2, floor.getNumberOfItems());
		assertEquals(0, backpack.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction((characterResponse, actionResponse) -> {
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
		SomeCharacter character       = new SomeCharacter();
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);
		GameInterface gameInterface = new SomeGameInterface();
		Item          item          = new SomeItem();
		floor.addItem(item, 0);
		floor.addItem(item, 1);
		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(2, floor.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction((characterResponse, actionResponse) -> {
			assertTrue(actionResponse.hasException(NumberFormatException.class));
			assertEquals(0, backpack.getNumberOfItems());
			assertEquals(2, floor.getNumberOfItems());
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
		SomeCharacter character       = new SomeCharacter();
		character.setCurrentLocation(currentLocation);
		character.setBackpack(backpack);
		GameInterface gameInterface = new SomeGameInterface();

		floor.addItem(new SomeItem(), 0);
		floor.addItem(new SomeItem(), 1);

		assertEquals(2, floor.getNumberOfItems());
		assertEquals(0, backpack.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction((characterResponse, actionResponse) -> {
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
		ActionTest.test(() -> new PickUpItemAction(null));
	}
}