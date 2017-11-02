package textadventure.doors;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class CloseDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock      lock      = new MockLock(Lock.State.UNLOCKED);
		Room      a         = new SomeRoom();
		Room      b         = new SomeRoom();
		Door      door      = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character character = new SomeCharacter();

		assertEquals(Door.State.OPEN, door.getState());
		CloseDoorAction action = new CloseDoorAction(door);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Door.State.CLOSED, action.getDoor().getState());
			}
		});
	}

	@Test
	public void performThrowsDoorAlreadyClosedException() throws Exception
	{
		Lock      lock      = new MockLock(Lock.State.UNLOCKED);
		Room      a         = new SomeRoom();
		Room      b         = new SomeRoom();
		Door      door      = new BaseDoor(Door.State.CLOSED, lock, a, b);
		Character character = new SomeCharacter();

		assertEquals(Door.State.CLOSED, door.getState());
		CloseDoorAction action = new CloseDoorAction(door);
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
			{
				assertTrue(action.hasException(DoorAlreadyClosedException.class));
				assertEquals(Door.State.CLOSED, action.getDoor().getState());
			}
		};

		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onCloseDoorAction(same(character), same(action));
	}

	@Test
	public void performThrowsDoorLockedException() throws Exception
	{
		Lock      lock      = new MockLock(Lock.State.LOCKED);
		Room      a         = new SomeRoom();
		Room      b         = new SomeRoom();
		Door      door      = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character character = new SomeCharacter();

		assertEquals(Door.State.OPEN, door.getState());
		CloseDoorAction action = new CloseDoorAction(door);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
			{
				assertTrue(action.hasException(DoorLockedException.class));
				assertEquals(Door.State.OPEN, action.getDoor().getState());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new CloseDoorAction(null));
	}
}