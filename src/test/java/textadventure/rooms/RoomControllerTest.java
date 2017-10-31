package textadventure.rooms;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import textadventure.Property;
import textadventure.actions.Action;
import textadventure.characters.Character;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RoomControllerTest
{
	@Test
	public void add() throws Exception
	{
		RoomController rooms      = new RoomController();
		Coordinate     coordinate = new Coordinate(3, 4);
		Room           room       = new SomeRoom();
		rooms.add(coordinate, room);
		assertSame(room, rooms.get(coordinate));
	}

	@Test(expected = UnknownRoomException.class)
	public void addThrowsUnknownRoomException() throws Exception
	{
		RoomController rooms = new RoomController();
		rooms.get(Coordinate.of(3, 7));
	}

	@Test
	public void get() throws Exception
	{
		RoomController rooms = new RoomController();
		Coordinate     coordinate = new Coordinate(3, 4);
		Room           room       = new SomeRoom();
		rooms.add(coordinate,room);
		assertSame(room, rooms.get(coordinate));
	}

	@Test
	public void getRooms() throws Exception
	{
		RoomController rooms = new RoomController();

		assertEquals(0, rooms.getRooms().size());

		Room           a       = new SomeRoom();
		Room           b       = new SomeRoom();
		Room           c       = new SomeRoom();

		rooms.add(Coordinate.of(1, 5), a);
		rooms.add(Coordinate.of(2, 5), b);
		rooms.add(Coordinate.of(3, 5), c);

		assertEquals(3, rooms.getRooms().size());

		assertSame(a, rooms.getRooms().get(Coordinate.of(1, 5)));
		assertSame(b, rooms.getRooms().get(Coordinate.of(2, 5)));
		assertSame(c, rooms.getRooms().get(Coordinate.of(3, 5)));
	}

}