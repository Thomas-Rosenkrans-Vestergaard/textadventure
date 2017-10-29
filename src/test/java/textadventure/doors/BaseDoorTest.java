package textadventure.doors;

import org.junit.Test;
import textadventure.lock.Lock;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import static org.junit.Assert.*;

public class BaseDoorTest
{

	@Test
	public void open() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.UNLOCKED);
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		door.open();
	}

	@Test(expected = DoorAlreadyOpenException.class)
	public void openDoorAlreadyOpenException() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.UNLOCKED);
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);
		door.open();
	}

	@Test(expected = DoorLockedException.class)
	public void openDoorLockedException() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.LOCKED);
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		door.open();
	}

	@Test
	public void close() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.UNLOCKED);
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);
		door.close();
	}

	@Test(expected = DoorAlreadyClosedException.class)
	public void closeDoorAlreadyClosedException() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.UNLOCKED);
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		door.close();
	}

	@Test(expected = DoorLockedException.class)
	public void closeDoorLockedException() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.LOCKED);
		Door door = new BaseDoor(Door.State.OPEN, lock, a, b);
		door.close();
	}

	@Test
	public void getState() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.LOCKED);
		assertEquals(Door.State.OPEN, new BaseDoor(Door.State.OPEN, lock, a, b).getState());
		assertEquals(Door.State.CLOSED, new BaseDoor(Door.State.CLOSED, lock, a, b).getState());
	}

	@Test
	public void getLock() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.LOCKED);
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		assertSame(lock, door.getLock());
	}

	@Test
	public void getRoomA() throws Exception
	{
		Room room = new BaseRoom(null, null);
		Door door = new BaseDoor(null, null, null, null);
		assertNull(door.getRoomA());
		door.setRoomA(room);
		assertSame(room, door.getRoomA());
	}

	@Test
	public void getRoomB() throws Exception
	{
		Room room = new BaseRoom(null, null);
		Door door = new BaseDoor(null, null, null, null);
		assertNull(door.getRoomB());
		door.setRoomB(room);
		assertSame(room, door.getRoomB());
	}

	@Test
	public void setRoomA() throws Exception
	{
		Room room = new BaseRoom(null, null);
		Door door = new BaseDoor(null, null, null, null);
		assertNull(door.getRoomA());
		door.setRoomA(room);
		assertSame(room, door.getRoomA());
	}

	@Test
	public void setRoomB() throws Exception
	{
		Room room = new BaseRoom(null, null);
		Door door = new BaseDoor(null, null, null, null);
		assertNull(door.getRoomB());
		door.setRoomB(room);
		assertSame(room, door.getRoomB());
	}

	@Test
	public void getInverseRoom() throws Exception
	{
		Room a    = new SomeRoom();
		Room b    = new SomeRoom();
		Lock lock = new Lock(null, Lock.State.LOCKED);
		Door door = new BaseDoor(Door.State.CLOSED, lock, a, b);
		assertSame(a, door.getInverseRoom(b));
		assertSame(b, door.getInverseRoom(a));
	}
}