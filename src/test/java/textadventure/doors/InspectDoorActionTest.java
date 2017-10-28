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

public class InspectDoorActionTest
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
		InspectDoorAction inspectDoorAction = new InspectDoorAction(door, ((characterResponse, action) -> {
			// TODO MOCK
		}));

		inspectDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void getDoor() throws Exception
	{
		Lock lock = new MockLock(Lock.State.LOCKED);
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);

		InspectDoorAction inspectDoorAction = new InspectDoorAction(door, null);
		assertSame(door, inspectDoorAction.getDoor());
	}

	@Test
	public void setException() throws Exception
	{
		Exception expected = new Exception();

		InspectDoorAction inspectDoorAction = new InspectDoorAction(null, null);
		inspectDoorAction.setException(expected);
		assertSame(expected, inspectDoorAction.getException());
	}

	@Test
	public void getException() throws Exception
	{
		Exception expected = new Exception();

		InspectDoorAction inspectDoorAction = new InspectDoorAction(null, null);
		inspectDoorAction.setException(expected);
		assertSame(expected, inspectDoorAction.getException());
	}

	@Test
	public void hasException() throws Exception
	{
		InspectDoorAction inspectDoorAction = new InspectDoorAction(null, null);

		Exception expected = new Exception();
		inspectDoorAction.setException(expected);
		assertTrue(inspectDoorAction.hasException(Exception.class));
		assertFalse(inspectDoorAction.hasException(IllegalStateException.class));

		expected = new IllegalStateException();
		inspectDoorAction.setException(expected);
		assertTrue(inspectDoorAction.hasException(IllegalStateException.class));
	}
}