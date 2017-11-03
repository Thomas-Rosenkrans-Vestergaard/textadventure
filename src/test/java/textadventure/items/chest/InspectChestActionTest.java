package textadventure.items.chest;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.lock.Lock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.same;

public class InspectChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		InspectChestAction action = new InspectChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onInspectChestAction(Character character, InspectChestAction action)
			{
				assertFalse(action.hasException());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onInspectChestAction(same(character), same(action));
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.UNLOCKED));

		InspectChestAction action = new InspectChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onInspectChestAction(Character character, InspectChestAction action)
			{
				assertTrue(action.hasException(ChestClosedException.class));
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onInspectChestAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new InspectChestAction(null));
	}
}