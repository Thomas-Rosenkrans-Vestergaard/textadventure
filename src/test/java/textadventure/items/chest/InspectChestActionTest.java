package textadventure.items.chest;

import org.junit.Test;
import textadventure.characters.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.lock.Lock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InspectChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		InspectChestAction action = new InspectChestAction(chest);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onInspectChestAction(Character character, InspectChestAction action)
			{
				assertFalse(action.hasException());
			}
		});
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		Character character = new SomeCharacter();
		Chest     chest     = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.UNLOCKED));

		InspectChestAction action = new InspectChestAction(chest);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onInspectChestAction(Character character, InspectChestAction action)
			{
				assertTrue(action.hasException(ChestClosedException.class));
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new InspectChestAction(null));
	}
}