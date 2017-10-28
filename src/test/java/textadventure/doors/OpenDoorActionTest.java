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

public class OpenDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.CLOSED, door.getState());
		OpenDoorAction openDoorAction = new OpenDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertEquals(Door.State.OPEN, action.getDoor().getState());
		}));

		openDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performDoorAlreadyOpenException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.OPEN, door.getState());
		OpenDoorAction openDoorAction = new OpenDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertEquals(Door.State.OPEN, action.getDoor().getState());
			assertTrue(action.hasException(DoorAlreadyOpenException.class));
		}));

		openDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performDoorLockedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.LOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertEquals(Door.State.CLOSED, door.getState());
		OpenDoorAction openDoorAction = new OpenDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertEquals(Door.State.CLOSED, action.getDoor().getState());
			assertTrue(action.hasException(DoorLockedException.class));
		}));

		openDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void getDoor() throws Exception
	{
		Door           expected       = new BaseDoor(null, null, null, null);
		OpenDoorAction openDoorAction = new OpenDoorAction(expected, null);
		assertSame(expected, openDoorAction.getDoor());
	}

	@Test
	public void setException() throws Exception
	{
		Exception      expected       = new Exception();
		OpenDoorAction openDoorAction = new OpenDoorAction(null, null);
		openDoorAction.setException(expected);
		assertSame(expected, openDoorAction.getException());
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

		OpenDoorAction openDoorAction = new OpenDoorAction(null, null);
		openDoorAction.setException(expected);
		assertSame(expected, openDoorAction.getException());
	}

	@Test
	public void hasException() throws Exception
	{
		OpenDoorAction openDoorAction = new OpenDoorAction(null, null);

		Exception expected = new Exception();
		openDoorAction.setException(expected);
		assertTrue(openDoorAction.hasException(Exception.class));
		assertFalse(openDoorAction.hasException(IllegalStateException.class));

		expected = new IllegalStateException();
		openDoorAction.setException(expected);
		assertTrue(openDoorAction.hasException(IllegalStateException.class));
	}
}