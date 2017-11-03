package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.*;

import java.lang.Character;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class TransferItemActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack backpack = new Backpack(5);
		Backpack targetBackpack = new Backpack(5);
		Item     item     = new SomeItem();
		backpack.addItem(item);
		Room          currentLocation = new BaseRoom(null, null);
		SomeCharacter character       = new SomeCharacter();
		SomeCharacter target = new SomeCharacter();
		character.setBackpack(backpack);
		target.setBackpack(targetBackpack);
		character.setCurrentLocation(currentLocation);
		target.setCurrentLocation(currentLocation);

		currentLocation.addCharacter(character);
		currentLocation.addCharacter(target);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, targetBackpack.getNumberOfItems());

		TransferItemAction action = new TransferItemAction();
		SomeActionResponses actionResponses = new SomeActionResponses(){

			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override
			public void onTransferItemAction(textadventure.characters.Character character, TransferItemAction action)
			{
				assertFalse(action.hasException());
				assertEquals(0, backpack.getNumberOfItems());
				assertEquals(1, targetBackpack.getNumberOfItems());

				try {
					assertSame(item, targetBackpack.getItem(0));
				} catch (Exception e) {
					fail();
				}
			}
		};

		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onTransferItemAction(same(character), same(action));

	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(TransferItemAction::new);
	}
}