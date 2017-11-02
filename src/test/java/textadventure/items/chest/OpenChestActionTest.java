package textadventure.items.chest;

import org.junit.Test;
import textadventure.characters.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.lock.Lock;

import static org.junit.Assert.*;

public class OpenChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.UNLOCKED));

		assertEquals(Chest.State.CLOSED, chest.getState());
		OpenChestAction action = new OpenChestAction(chest);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onOpenChestAction(Character character, OpenChestAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Chest.State.OPEN, chest.getState());
			}
		});
	}

	@Test
	public void performThrowsChestLockedException() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.LOCKED));

		assertEquals(Chest.State.CLOSED, chest.getState());
		OpenChestAction action = new OpenChestAction(chest);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onOpenChestAction(Character character, OpenChestAction action)
			{
				assertTrue(action.hasException(ChestLockedException.class));
				assertEquals(Chest.State.CLOSED, chest.getState());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new OpenChestAction(null));
	}
}