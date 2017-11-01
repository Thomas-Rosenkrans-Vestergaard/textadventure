package textadventure.items.chest;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.lock.Lock;

import static org.junit.Assert.*;

public class CloseChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		assertEquals(Chest.State.OPEN, chest.getState());
		CloseChestAction action = new CloseChestAction(chest);
		action.perform(character, new String[0], new SomeActionResponses()
		{
			@Override public void onCloseChestAction(Character character, CloseChestAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Chest.State.CLOSED, chest.getState());
			}
		});
	}

	@Test
	public void performThrowsChestLockedException() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.LOCKED));

		assertEquals(Chest.State.OPEN, chest.getState());
		CloseChestAction action = new CloseChestAction(chest);
		action.perform(character, new String[0], new SomeActionResponses()
		{
			@Override public void onCloseChestAction(Character character, CloseChestAction action)
			{
				assertTrue(action.hasException(ChestLockedException.class));
				assertEquals(Chest.State.OPEN, chest.getState());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new CloseChestAction(null));
	}
}