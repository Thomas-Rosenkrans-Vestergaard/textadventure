package textadventure.characters;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.*;

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

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction action = new DropItemAction();
		action.perform(character, new String[0], new SomeActionResponses()
		{

			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onDropItemAction(Character character, DropItemAction action)
			{
				assertFalse(action.hasException());
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(1, floor.getNumberOfItems());
				assertEquals(1, action.getItems().size());

				try {
					assertSame(item, floor.getItem(0));
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
		backpack.addItem(a, 0);
		backpack.addItem(b, 1);

		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction action = new DropItemAction();
		action.perform(character, new String[]{"0", "1"}, new SomeActionResponses()
		{
			@Override public void onDropItemAction(Character character, DropItemAction action)
			{
				assertFalse(action.hasException());
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(2, floor.getNumberOfItems());
				assertEquals(2, action.getItems().size());

				try {
					assertSame(b, floor.takeItem(0));
					assertSame(a, floor.takeItem(0));
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

		backpack.addItem(new SomeItem());
		backpack.addItem(new SomeItem());

		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction action = new DropItemAction();
		action.perform(character, new String[]{"NOT AN INTEGER"}, new SomeActionResponses()
		{
			@Override public void onDropItemAction(Character character, DropItemAction action)
			{
				assertTrue(action.hasException(NumberFormatException.class));
				assertEquals(2, backpack.getNumberOfItems());
				assertEquals(0, floor.getNumberOfItems());
				assertEquals(0, action.getItems().size());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(DropItemAction::new);
	}
}