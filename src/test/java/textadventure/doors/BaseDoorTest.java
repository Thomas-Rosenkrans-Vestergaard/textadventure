package textadventure.doors;

import org.junit.Test;
import textadventure.lock.Lock;
import textadventure.rooms.MockRoom;
import textadventure.rooms.Room;

import static org.junit.Assert.*;

public class BaseDoorTest
{

	@Test
	public void open() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new UnlockedLock();
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		door.open();
	}

	@Test(expected = DoorAlreadyOpenException.class)
	public void openDoorAlreadyOpenException() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new UnlockedLock();
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);
		door.open();
	}

	@Test(expected = DoorLockedException.class)
	public void openDoorLockedException() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new LockedLock();
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		door.open();
	}

	@Test
	public void close() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new UnlockedLock();
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);
		door.close();
	}

	@Test(expected = DoorAlreadyClosedException.class)
	public void closeDoorAlreadyClosedException() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new UnlockedLock();
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		door.close();
	}

	@Test(expected = DoorLockedException.class)
	public void closeDoorLockedException() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new LockedLock();
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);
		door.close();
	}

	@Test
	public void getState() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new LockedLock();
		assertEquals(Door.State.OPEN, new BaseDoor(Door.State.OPEN, lock, a, b).getState());
		assertEquals(Door.State.CLOSED, new BaseDoor(Door.State.CLOSED, lock, a, b).getState());
	}

	@Test
	public void getLock() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new LockedLock();
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		assertSame(lock, door.getLock());
	}

	@Test
	public void getInverseRoom() throws Exception
	{
		Room a    = new MockRoom();
		Room b    = new MockRoom();
		Lock lock = new LockedLock();
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		assertSame(a, door.getInverseRoom(b));
		assertSame(b, door.getInverseRoom(a));
	}

	class LockedLock extends Lock
	{
		LockedLock()
		{
			super("MockLockCode", State.LOCKED);
		}
	}

	class UnlockedLock extends Lock
	{
		UnlockedLock()
		{
			super("MockLockCode", State.UNLOCKED);
		}
	}
}