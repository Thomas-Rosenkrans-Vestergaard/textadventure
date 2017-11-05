package textadventure.items.backpack;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.select.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.same;

public class InspectBackpackActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack backpack = new Backpack(5);
		backpack.addItem(new BackpackExpansion(5));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());
		InspectBackpackAction action = new InspectBackpackAction(backpack);

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
		Mockito.verify(mock, Mockito.times(1)).onInspectBackpackAction(same(character), same(action));
	}
}