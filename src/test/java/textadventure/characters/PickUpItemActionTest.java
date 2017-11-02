package textadventure.characters;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.*;

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

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, floor.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction();
		action.perform(character, new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
			{
				assertFalse(action.hasException());
				assertEquals(0, floor.getNumberOfItems());
				assertEquals(1, backpack.getNumberOfItems());
				assertEquals(1, action.getItems().size());

				try {
					assertSame(item, backpack.getItem(0));
				} catch (Exception e) {
					fail();
				}
			}
		});
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

		floor.addItem(a, 0);
		floor.addItem(b, 1);

		assertEquals(2, floor.getNumberOfItems());
		assertEquals(0, backpack.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction();
		action.perform(character, new SomeActionResponses()
		{

			@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
			{
				assertFalse(action.hasException());
				assertEquals(0, floor.getNumberOfItems());
				assertEquals(2, backpack.getNumberOfItems());
				assertEquals(2, action.getItems().size());

				try {
					assertSame(b, backpack.takeItem(0));
					assertSame(a, backpack.takeItem(0));
				} catch (Exception e) {
					fail();
				}
			}
		});
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
		Item item = new SomeItem();
		floor.addItem(item, 0);
		floor.addItem(item, 1);
		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(2, floor.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction();
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
			{
				assertTrue(action.hasException(NumberFormatException.class));
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(2, floor.getNumberOfItems());
				assertEquals(0, action.getItems().size());
			}
		});
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

		floor.addItem(new SomeItem(), 0);
		floor.addItem(new SomeItem(), 1);

		assertEquals(2, floor.getNumberOfItems());
		assertEquals(0, backpack.getNumberOfItems());

		PickUpItemAction action = new PickUpItemAction();
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
			{
				assertTrue(action.hasException(InventoryFullException.class));
				assertEquals(2, floor.getNumberOfItems());
				assertEquals(0, character.getBackpack().getNumberOfItems());
				assertEquals(0, action.getItems().size());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(PickUpItemAction::new);
	}
}