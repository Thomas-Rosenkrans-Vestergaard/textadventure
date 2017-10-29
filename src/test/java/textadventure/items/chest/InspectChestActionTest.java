package textadventure.items.chest;

import org.junit.Test;
import textadventure.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.lock.Lock;
import textadventure.ui.GameInterface;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class InspectChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface();
		Character     character     = new SomeCharacter();
		Chest         chest         = new Chest(10, Chest.State.OPEN, new Lock(null, Lock.State.UNLOCKED));

		InspectChestAction action = new InspectChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertFalse(actionResponse.hasException());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsChestClosedException() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface();
		Character     character     = new SomeCharacter();
		Chest         chest         = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.UNLOCKED));

		InspectChestAction action = new InspectChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(ChestClosedException.class));
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new InspectChestAction(null, null));
	}
}