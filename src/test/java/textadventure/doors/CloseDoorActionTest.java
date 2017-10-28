package textadventure.doors;

import org.junit.Test;
import textadventure.Character;
import textadventure.MockCharacter;
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
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.OPEN, door.getState());
		CloseDoorAction closeDoorAction = new CloseDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertEquals(Door.State.CLOSED, action.getDoor().getState());
		}));

		closeDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performDoorAlreadyClosedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.CLOSED, door.getState());
		CloseDoorAction closeDoorAction = new CloseDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertEquals(Door.State.CLOSED, action.getDoor().getState());
			assertTrue(action.hasException(DoorAlreadyClosedException.class));
		}));

		closeDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performDoorLockedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.LOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.OPEN, door.getState());
		CloseDoorAction closeDoorAction = new CloseDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertEquals(Door.State.OPEN, action.getDoor().getState());
			assertTrue(action.hasException(DoorLockedException.class));
		}));

		closeDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void getDoor() throws Exception
	{
		Door            expected        = new BaseDoor(null, null, null, null);
		CloseDoorAction closeDoorAction = new CloseDoorAction(expected, null);
		assertSame(expected, closeDoorAction.getDoor());
	}

	@Test
	public void setException() throws Exception
	{
		Exception       expected        = new Exception();
		CloseDoorAction closeDoorAction = new CloseDoorAction(null, null);
		closeDoorAction.setException(expected);
		assertSame(expected, closeDoorAction.getException());
	}

	@Test
	public void onException() throws Exception
	{
		// TODO TEST
	}

	@Test
	public void onSuccess() throws Exception
	{
		//TODO TEST
	}

	@Test
	public void getException() throws Exception
	{
		Exception expected = new Exception();

		CloseDoorAction closeDoorAction = new CloseDoorAction(null, null);
		closeDoorAction.setException(expected);
		assertSame(expected, closeDoorAction.getException());
	}

	@Test
	public void hasException() throws Exception
	{
		CloseDoorAction closeDoorAction = new CloseDoorAction(null, null);

		Exception expected = new Exception();
		closeDoorAction.setException(expected);
		assertTrue(closeDoorAction.hasException(Exception.class));
		assertFalse(closeDoorAction.hasException(IllegalStateException.class));

		expected = new IllegalStateException();
		closeDoorAction.setException(expected);
		assertTrue(closeDoorAction.hasException(IllegalStateException.class));
	}
}