package textadventure.items.backpack;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.select.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class ExpandBackpackActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack backpack = new Backpack(5);
		backpack.addItem(new BackpackExpansion(5));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());
		ExpandBackpackAction action = new ExpandBackpackAction(backpack);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
			{
				assertFalse(action.hasException());
				assertEquals(10, backpack.getNumberOfPositions());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onExpandBackpackAction(same(character), same(action));
	}

	@Test
	public void getBackpackExpansion() throws Exception
	{
		Backpack          backpack          = new Backpack(5);
		BackpackExpansion backpackExpansion = new BackpackExpansion(5);
		backpack.addItem(backpackExpansion);
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
			{
				assertSame(backpackExpansion, action.getBackpackExpansion());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onExpandBackpackAction(same(character), same(action));
	}

	@Test
	public void getBackpack() throws Exception
	{
		Backpack          backpack          = new Backpack(5);
		BackpackExpansion backpackExpansion = new BackpackExpansion(5);
		backpack.addItem(backpackExpansion);
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
			{
				assertSame(backpack, action.getBackpack());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onExpandBackpackAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new ExpandBackpackAction(null));
	}
}