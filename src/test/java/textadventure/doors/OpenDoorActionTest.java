package textadventure.doors;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import static org.junit.Assert.*;

public class OpenDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock      lock      = new MockLock(Lock.State.UNLOCKED);
		Room      a         = new SomeRoom();
		Room      b         = new SomeRoom();
		Door      door      = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character character = new SomeCharacter();

		assertEquals(Door.State.CLOSED, door.getState());
		OpenDoorAction action = new OpenDoorAction(door);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Door.State.OPEN, action.getDoor().getState());
			}
		});
	}

	@Test
	public void performDoorAlreadyOpenException() throws Exception
	{
		Lock      lock      = new MockLock(Lock.State.UNLOCKED);
		Room      a         = new SomeRoom();
		Room      b         = new SomeRoom();
		Door      door      = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character character = new SomeCharacter();

		assertEquals(Door.State.OPEN, door.getState());
		OpenDoorAction action = new OpenDoorAction(door);

		action.perform(character, new SomeActionResponses()
		{
			@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
			{
				assertTrue(action.hasException(DoorAlreadyOpenException.class));
				assertEquals(Door.State.OPEN, action.getDoor().getState());
			}
		});
	}

	@Test
	public void performThrowsDoorLockedException() throws Exception
	{
		Lock      lock      = new MockLock(Lock.State.LOCKED);
		Room      a         = new SomeRoom();
		Room      b         = new SomeRoom();
		Door      door      = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character character = new SomeCharacter();

		assertEquals(Door.State.CLOSED, door.getState());
		OpenDoorAction action = new OpenDoorAction(door);

		action.perform(character, new SomeActionResponses()
		{
			@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
			{
				assertTrue(action.hasException(DoorLockedException.class));
				assertEquals(Door.State.CLOSED, action.getDoor().getState());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new OpenDoorAction(null));
	}
}