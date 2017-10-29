package textadventure.items.chest;

import org.junit.Test;
import textadventure.BaseCharacter;
import textadventure.Character;
import textadventure.actions.ActionTest;
import textadventure.lock.Lock;
import textadventure.ui.GameInterface;
import textadventure.ui.MockGameInterface;

import static org.junit.Assert.*;

public class OpenChestActionTest
{
	@Test
	public void perform() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface();
		Character     character     = new BaseCharacter(null, null, null);
		Chest         chest         = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.UNLOCKED));

		assertEquals(Chest.State.CLOSED, chest.getState());
		OpenChestAction action = new OpenChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertFalse(actionResponse.hasException());
			assertEquals(Chest.State.OPEN, chest.getState());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsChestLockedException() throws Exception
	{
		GameInterface gameInterface = new MockGameInterface();
		Character     character     = new BaseCharacter(null, null, null);
		Chest         chest         = new Chest(10, Chest.State.CLOSED, new Lock(null, Lock.State.LOCKED));

		assertEquals(Chest.State.CLOSED, chest.getState());
		OpenChestAction action = new OpenChestAction(chest, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(chest, actionResponse.getChest());
			assertTrue(actionResponse.hasException(ChestLockedException.class));
			assertEquals(Chest.State.CLOSED, chest.getState());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new OpenChestAction(null, null));
	}
}