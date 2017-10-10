package textadventure;

import static org.junit.Assert.*;

import org.junit.Test;
import textadventure.rooms.RoomController;

public class RoomControllerTest
{
	@Test
	public void addRoom()
	{
		RoomController roomTracker = new RoomController();
		roomTracker.addRoom(new MockRoom());
		assertEquals(1, roomTracker.getRoomCount());
		roomTracker.addRoom(new MockRoom());
		assertEquals(2, roomTracker.getRoomCount());
	}

	@Test
	public void getRoomCount()
	{
		RoomController roomTracker = new RoomController();
		roomTracker.addRoom(new MockRoom());
		assertEquals(1, roomTracker.getRoomCount());
		roomTracker.addRoom(new MockRoom());
		assertEquals(2, roomTracker.getRoomCount());
	}

	@Test
	public void getRoom() throws UnknownRoomException
	{
		RoomController roomTracker = new RoomController();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		roomTracker.addConnection(a, b, Direction.NORTH);

		assertSame(b, roomTracker.getRoom(a, Direction.NORTH));
		assertSame(a, roomTracker.getRoom(b, Direction.SOUTH));
		assertNull(roomTracker.getRoom(a, Direction.EAST));
	}

	@Test
	public void hasConnection() throws UnknownRoomException
	{
		RoomController roomTracker = new RoomController();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		roomTracker.addConnection(a, b, Direction.NORTH);

		assertTrue(roomTracker.hasConnection(a, Direction.NORTH));
		assertTrue(roomTracker.hasConnection(b, Direction.SOUTH));
		assertFalse(roomTracker.hasConnection(a, Direction.EAST));
	}

	@Test
	public void getDirection() throws UnknownRoomException
	{
		RoomController roomTracker = new RoomController();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();
		MockRoom c = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		roomTracker.addConnection(a, b, Direction.NORTH);

		assertEquals(Direction.NORTH, roomTracker.getDirection(a, b));
		assertEquals(Direction.SOUTH, roomTracker.getDirection(b, a));
		assertNull(roomTracker.getDirection(a, c));
	}

	@Test
	public void addConnection() throws UnknownRoomException
	{
		RoomController roomTracker = new RoomController();

		MockRoom a = new MockRoom();
		MockRoom b = new MockRoom();

		roomTracker.addRoom(a);
		roomTracker.addRoom(b);

		assertNull(roomTracker.getDirection(a, b));
		roomTracker.addConnection(a, b, Direction.NORTH);
		assertEquals(Direction.NORTH, roomTracker.getDirection(a, b));
		assertEquals(Direction.SOUTH, roomTracker.getDirection(b, a));
	}
}