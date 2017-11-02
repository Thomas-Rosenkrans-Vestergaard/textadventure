package textadventure.rooms;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import textadventure.characters.SomeCharacter;
import textadventure.characters.Character;

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

	@Test
	public void addCharacter() throws Exception
	{
		Room      room      = new BaseRoom(null, null);
		Character character = new SomeCharacter();
		assertFalse(room.hasCharacter(character));
		room.addCharacter(character);
		assertTrue(room.hasCharacter(character));
	}

	@Test
	public void hasCharacter() throws Exception
	{
		Room      room      = new BaseRoom(null, null);
		Character character = new SomeCharacter();
		assertFalse(room.hasCharacter(character));
		room.addCharacter(character);
		assertTrue(room.hasCharacter(character));
	}

	@Test
	public void removeCharacter() throws Exception
	{
		Room      room      = new BaseRoom(null, null);
		Character character = new SomeCharacter();
		assertFalse(room.hasCharacter(character));
		room.addCharacter(character);
		assertTrue(room.hasCharacter(character));
		room.removeCharacter(character);
		assertFalse(room.hasCharacter(character));
	}

	@Test
	public void getCharacters() throws Exception
	{
		Room room = new BaseRoom(null, null);

		Character a = new SomeCharacter();
		Character b = new SomeCharacter();
		Character c = new SomeCharacter();

		room.addCharacter(a);
		room.addCharacter(b);
		room.addCharacter(c);

		ImmutableSet<Character> output = room.getCharacters();
		assertEquals(3, output.size());
		assertTrue(output.contains(a));
		assertTrue(output.contains(b));
		assertTrue(output.contains(c));
	}
}