package textadventure.doors;

import org.junit.Test;
import textadventure.BaseCharacter;
import textadventure.Character;
import textadventure.actions.ActionTest;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.rooms.MockRoom;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.MockGameInterface;

import static org.junit.Assert.*;

public class UseDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character     character     = new BaseCharacter(null, null, null, a);
		GameInterface gameInterface = new MockGameInterface();

		assertSame(a, character.getCurrentLocation());
		UseDoorAction action = new UseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertFalse(actionResponse.hasException());
			assertSame(b, character.getCurrentLocation());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsDoorClosedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character     character     = new BaseCharacter(null, null, null, a);
		GameInterface gameInterface = new MockGameInterface();

		assertSame(a, character.getCurrentLocation());
		UseDoorAction action = new UseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertTrue(actionResponse.hasException(DoorClosedException.class));
			assertSame(a, character.getCurrentLocation());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new UseDoorAction(null, null));
	}
}