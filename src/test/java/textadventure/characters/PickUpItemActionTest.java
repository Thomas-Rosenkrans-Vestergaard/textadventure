package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
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
import static org.mockito.Matchers.same;

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
		SomeActionResponses actionResponses = new SomeActionResponses()
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
		};

		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onPickUpItemAction(same(character), same(action));
	}

	@Test
	public void performThrowsInventoryFullException() throws Exception
	{
		Backpack      backpack        = new Backpack(0);
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
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
			{
				assertTrue(action.hasException(InventoryFullException.class));
				assertEquals(1, floor.getNumberOfItems());
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(0, action.getItems().size());

				try {
					assertSame(item, backpack.getItem(0));
				} catch (Exception e) {
					fail();
				}
			}
		};

		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onPickUpItemAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(PickUpItemAction::new);
	}
}