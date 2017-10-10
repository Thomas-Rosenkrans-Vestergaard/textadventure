package textadventure;

import org.junit.jupiter.api.Test;
import textadventure.exception.UnknownRoomException;

import static org.junit.jupiter.api.Assertions.*;

class RoomConnectionTrackerTest
{
	@Test
	void addRoom()
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();
		roomConnectionTracker.addRoom(new MockRoom());
		assertEquals(1, roomConnectionTracker.getRoomCount());
		roomConnectionTracker.addRoom(new MockRoom());
		assertEquals(2, roomConnectionTracker.getRoomCount());
	}

	@Test
	void getRoomCount()
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();
		roomConnectionTracker.addRoom(new MockRoom());
		assertEquals(1, roomConnectionTracker.getRoomCount());
		roomConnectionTracker.addRoom(new MockRoom());
		assertEquals(2, roomConnectionTracker.getRoomCount());
	}

	@Test
	void getRoom() throws UnknownRoomException
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomConnectionTracker.addRoom(a);
		roomConnectionTracker.addRoom(b);

		roomConnectionTracker.addMutualConnection(a, b, Direction.NORTH);

		assertSame(b, roomConnectionTracker.getRoom(a, Direction.NORTH));
		assertSame(a, roomConnectionTracker.getRoom(b, Direction.SOUTH));
		assertNull(roomConnectionTracker.getRoom(a, Direction.EAST));
	}

	@Test
	void hasConnection() throws UnknownRoomException
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomConnectionTracker.addRoom(a);
		roomConnectionTracker.addRoom(b);

		roomConnectionTracker.addMutualConnection(a, b, Direction.NORTH);

		assertTrue(roomConnectionTracker.hasConnection(a, Direction.NORTH));
		assertTrue(roomConnectionTracker.hasConnection(b, Direction.SOUTH));
		assertFalse(roomConnectionTracker.hasConnection(a, Direction.EAST));
	}

	@Test
	void getDirection() throws UnknownRoomException
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();
		MockRoom c = new MockRoom();

		roomConnectionTracker.addRoom(a);
		roomConnectionTracker.addRoom(b);

		roomConnectionTracker.addMutualConnection(a, b, Direction.NORTH);

		assertEquals(Direction.NORTH, roomConnectionTracker.getDirection(a, b));
		assertEquals(Direction.SOUTH, roomConnectionTracker.getDirection(b, a));
		assertNull(roomConnectionTracker.getDirection(a, c));
	}

	@Test
	void addConnection() throws UnknownRoomException
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomConnectionTracker.addRoom(a);
		roomConnectionTracker.addRoom(b);

		assertNull(roomConnectionTracker.getDirection(a, b));
		roomConnectionTracker.addConnection(a, b, Direction.NORTH);
		assertEquals(Direction.NORTH, roomConnectionTracker.getDirection(a, b));
		assertNull(roomConnectionTracker.getDirection(b, a));
	}

	@Test
	void addMutualConnection() throws UnknownRoomException
	{
		RoomConnectionTracker roomConnectionTracker = new RoomConnectionTracker();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomConnectionTracker.addRoom(a);
		roomConnectionTracker.addRoom(b);

		assertNull(roomConnectionTracker.getDirection(a, b));
		roomConnectionTracker.addMutualConnection(a, b, Direction.NORTH);
		assertEquals(Direction.NORTH, roomConnectionTracker.getDirection(a, b));
		assertEquals(Direction.SOUTH, roomConnectionTracker.getDirection(b, a));
	}
}