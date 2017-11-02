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
		action.perform(character, new SomeActionResponses()
		{

			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onDropItemAction(Character character, DropItemAction action)
			{
				System.out.println(action.getException());
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
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(DropItemAction::new);
	}
}