package textadventure.items.chest;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.lock.Lock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class CloseChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		assertEquals(Chest.State.OPEN, chest.getState());
		CloseChestAction action = new CloseChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onCloseChestAction(Character character, CloseChestAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Chest.State.CLOSED, chest.getState());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onCloseChestAction(same(character), same(action));
	}

	@Test
	public void performThrowsChestLockedException() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.LOCKED));

		assertEquals(Chest.State.OPEN, chest.getState());
		CloseChestAction action = new CloseChestAction(chest);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onCloseChestAction(Character character, CloseChestAction action)
			{
				assertTrue(action.hasException(ChestLockedException.class));
				assertEquals(Chest.State.OPEN, chest.getState());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onCloseChestAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new CloseChestAction(null));
	}
}