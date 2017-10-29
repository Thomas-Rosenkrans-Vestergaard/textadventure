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

public class CloseDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character     character     = new BaseCharacter(null, null, null, null);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.OPEN, door.getState());
		CloseDoorAction action = new CloseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertFalse(actionResponse.hasException());
			assertEquals(Door.State.CLOSED, actionResponse.getDoor().getState());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsDoorAlreadyClosedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character     character     = new BaseCharacter(null, null, null, null);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.CLOSED, door.getState());
		CloseDoorAction action = new CloseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertTrue(actionResponse.hasException(DoorAlreadyClosedException.class));
			assertEquals(Door.State.CLOSED, actionResponse.getDoor().getState());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performThrowsDoorLockedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.LOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character     character     = new BaseCharacter(null, null, null, null);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.OPEN, door.getState());
		CloseDoorAction action = new CloseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertTrue(actionResponse.hasException(DoorLockedException.class));
			assertEquals(Door.State.OPEN, actionResponse.getDoor().getState());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new CloseDoorAction(null, null));
	}
}