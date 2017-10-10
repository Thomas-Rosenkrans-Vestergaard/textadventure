package textadventure;

import static org.junit.Assert.*;

import org.junit.Test;
import textadventure.exception.UnknownRoomException;

class RoomConnectionTrackerTest
{
	@Test
	void addRoom()
	{
		RoomTracker roomTracker = new RoomTracker();
		roomTracker.addRoom(new MockRoom());
		assertEquals(1, roomTracker.getRoomCount());
		roomTracker.addRoom(new MockRoom());
		assertEquals(2, roomTracker.getRoomCount());
	}

	@Test
	void getRoomCount()
	{
		RoomTracker roomTracker = new RoomTracker();
		roomTracker.addRoom(new MockRoom());
		assertEquals(1, roomTracker.getRoomCount());
		roomTracker.addRoom(new MockRoom());
		assertEquals(2, roomTracker.getRoomCount());
	}

	@Test
	void getRoom() throws UnknownRoomException
	{
		RoomTracker roomTracker = new RoomTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		roomTracker.addMutualConnection(a, b, Direction.NORTH);

		assertSame(b, roomTracker.getRoom(a, Direction.NORTH));
		assertSame(a, roomTracker.getRoom(b, Direction.SOUTH));
		assertNull(roomTracker.getRoom(a, Direction.EAST));
	}

	@Test
	void hasConnection() throws UnknownRoomException
	{
		RoomTracker roomTracker = new RoomTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		roomTracker.addMutualConnection(a, b, Direction.NORTH);

		assertTrue(roomTracker.hasConnection(a, Direction.NORTH));
		assertTrue(roomTracker.hasConnection(b, Direction.SOUTH));
		assertFalse(roomTracker.hasConnection(a, Direction.EAST));
	}

	@Test
	void getDirection() throws UnknownRoomException
	{
		RoomTracker roomTracker = new RoomTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();
		MockRoom c = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		roomTracker.addMutualConnection(a, b, Direction.NORTH);

		assertEquals(Direction.NORTH, roomTracker.getDirection(a, b));
		assertEquals(Direction.SOUTH, roomTracker.getDirection(b, a));
		assertNull(roomTracker.getDirection(a, c));
	}

	@Test
	void addConnection() throws UnknownRoomException
	{
		RoomTracker roomTracker = new RoomTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		assertNull(roomTracker.getDirection(a, b));
		roomTracker.addConnection(a, b, Direction.NORTH);
		assertEquals(Direction.NORTH, roomTracker.getDirection(a, b));
		assertNull(roomTracker.getDirection(b, a));
	}

	@Test
	void addMutualConnection() throws UnknownRoomException
	{
		RoomTracker roomTracker = new RoomTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		assertNull(roomTracker.getDirection(a, b));
		roomTracker.addMutualConnection(a, b, Direction.NORTH);
		assertEquals(Direction.NORTH, roomTracker.getDirection(a, b));
		assertEquals(Direction.SOUTH, roomTracker.getDirection(b, a));
	}
}