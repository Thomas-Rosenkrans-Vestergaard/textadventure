package textadventure.items.chest;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.items.SomeTypedItem;
import textadventure.items.backpack.Backpack;
import textadventure.lock.Lock;
import textadventure.ui.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class TakeItemFromChestActionTest
{

	@Test
	public void perform() throws Exception
	{
		Backpack      backpack  = new Backpack();
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		Item a = new SomeTypedItem(1);
		Item b = new SomeTypedItem(2);

		backpack.addItem(a, 0);
		chest.addItem(b, 1);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(1);
			}

			@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
			{
				try {
					assertFalse(action.hasException());
					assertEquals(0, chest.getNumberOfItems());
					assertEquals(2, backpack.getNumberOfItems());
					assertEquals(b, action.getItems().get(0));
					assertEquals(b, backpack.getItem(1));
				} catch (Exception e) {
					fail();
				}
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onTakeItemFromChestAction(same(character), same(action));
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		Backpack      backpack  = new Backpack();
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		Chest chest = new Chest(10, Chest.State.CLOSED, null);

		chest.addItem(new SomeItem(), 0);

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, chest.getNumberOfItems());

		TakeItemFromChestAction action = new TakeItemFromChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
			{
				assertTrue(action.hasException(ChestClosedException.class));
				assertEquals(0, action.getItems().size());
				assertEquals(1, chest.getNumberOfItems());
				assertEquals(0, backpack.getNumberOfItems());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onTakeItemFromChestAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new TakeItemFromChestAction(null));
	}
}