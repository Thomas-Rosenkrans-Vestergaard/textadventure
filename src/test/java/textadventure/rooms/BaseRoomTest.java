package textadventure.rooms;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRoomTest
{

	@Test
	public void getRoomName() throws Exception
	{
		String expected = "RoomName";
		Room   room     = new BaseRoom(expected, null);
		assertSame(expected, room.getRoomName());
	}

	@Test
	public void getRoomDescription() throws Exception
	{
		String expected = "RoomDescription";
		Room   room     = new BaseRoom(null, expected);
		assertSame(expected, room.getRoomDescription());
	}

	@Test
	public void getFloor() throws Exception
	{
		Floor expected = new Floor();
		Room  room     = new BaseRoom(null, null, expected);
		assertSame(expected, room.getRoomFloor());
	}
}