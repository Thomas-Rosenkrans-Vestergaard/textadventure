package textadventure.items.backpack;

import org.junit.Test;
import textadventure.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class DropItemActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack backpack = new Backpack(5);
		Item     item     = new SomeItem();
		backpack.addItem(item);
		Room          currentLocation = new BaseRoom(null, null);
		Floor         floor           = currentLocation.getRoomFloor();
		SomeCharacter character       = new SomeCharacter();
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);

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

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction action = new DropItemAction((characterResponse, actionResponse) -> {
			assertFalse(actionResponse.hasException());
			assertEquals(0, backpack.getNumberOfItems());
			assertEquals(1, floor.getNumberOfItems());
			assertEquals(1, actionResponse.getItems().size());

			try {
				assertSame(item, floor.getItem(0));
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

		backpack.addItem(a, 0);
		backpack.addItem(b, 1);

		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction action = new DropItemAction((characterResponse, actionResponse) -> {
			assertFalse(actionResponse.hasException());
			assertEquals(0, backpack.getNumberOfItems());
			assertEquals(2, floor.getNumberOfItems());
			assertEquals(2, actionResponse.getItems().size());

			try {
				assertSame(b, floor.takeItem(0));
				assertSame(a, floor.takeItem(0));
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

		backpack.addItem(new SomeItem());
		backpack.addItem(new SomeItem());

		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction action = new DropItemAction((characterResponse, actionResponse) -> {
			assertTrue(actionResponse.hasException(NumberFormatException.class));
			assertEquals(2, backpack.getNumberOfItems());
			assertEquals(0, floor.getNumberOfItems());
			assertEquals(0, actionResponse.getItems().size());
		});

		action.perform(gameInterface, character, new String[]{"NOT AN INTEGER"}); // <-----------
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new DropItemAction(null));
	}
}