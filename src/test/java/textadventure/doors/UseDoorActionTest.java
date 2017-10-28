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

public class UseDoorActionTest
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

		assertSame(a, character.getCurrentLocation());
		UseDoorAction useDoorAction = new UseDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertSame(b, character.getCurrentLocation());
		}));

		useDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performDoorClosedException() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new MockRoom();
		Room          b             = new MockRoom();
		Door          door          = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character     character     = new MockCharacter(a);
		GameInterface gameInterface = new MockGameInterface();

		assertSame(a, character.getCurrentLocation());
		UseDoorAction useDoorAction = new UseDoorAction(door, ((characterResponse, action) -> {
			assertSame(character, characterResponse);
			assertSame(a, character.getCurrentLocation());
			assertTrue(action.hasException(DoorClosedException.class));
		}));

		useDoorAction.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void getDoor() throws Exception
	{
		Door          expected      = new BaseDoor(null, null, null, null);
		UseDoorAction useDoorAction = new UseDoorAction(expected, null);
		assertSame(expected, useDoorAction.getDoor());
	}

	@Test
	public void setException() throws Exception
	{
		Exception     expected      = new Exception();
		UseDoorAction useDoorAction = new UseDoorAction(null, null);
		useDoorAction.setException(expected);
		assertSame(expected, useDoorAction.getException());
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

		UseDoorAction useDoorAction = new UseDoorAction(null, null);
		useDoorAction.setException(expected);
		assertSame(expected, useDoorAction.getException());
	}

	@Test
	public void hasException() throws Exception
	{
		UseDoorAction useDoorAction = new UseDoorAction(null, null);

		Exception expected = new Exception();
		useDoorAction.setException(expected);
		assertTrue(useDoorAction.hasException(Exception.class));
		assertFalse(useDoorAction.hasException(IllegalStateException.class));

		expected = new IllegalStateException();
		useDoorAction.setException(expected);
		assertTrue(useDoorAction.hasException(IllegalStateException.class));
	}
}