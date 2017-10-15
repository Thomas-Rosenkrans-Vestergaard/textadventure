package textadventure;

import org.junit.Before;
import org.junit.Test;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;

import static org.junit.Assert.*;

public class BaseCharacterTest
{

	Backpack backpack;
	Room room;
	Character character;

	@Before
	public void setUp() throws Exception
	{
		backpack = new Backpack(10);
		room = new BaseRoom("roomName", "roomDescription");
		character = new BaseCharacter("name", backpack, room, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	}

	@Test
	public void getName() throws Exception
	{
		assertEquals("name", character.getName());
	}

	@Test
	public void getInventory() throws Exception
	{
		assertEquals(backpack, character.getBackpack());
	}

	@Test
	public void getCurrentLocation() throws Exception
	{
		assertEquals(room, character.getCurrentLocation());
	}

	@Test
	public void setCurrentLocation() throws Exception
	{
		assertEquals(room, character.getCurrentLocation());
		Room room = new BaseRoom("roomName", "roomDescription");
		character.setCurrentLocation(room);
		assertSame(room, character.getCurrentLocation());
	}

	@Test
	public void getCurrentHP() throws Exception
	{
		assertEquals(2, character.getCurrentHP());
	}

	@Test
	public void getMaxHP() throws Exception
	{
		assertEquals(1, character.getMaxHP());
	}

	@Test
	public void getLevel() throws Exception
	{
		assertEquals(3, character.getLevel());
	}

	@Test void getSanity() throws Exception
	{
		assertEquals(4, character.getSanity());
	}

	@Test
	public void getStrength() throws Exception
	{
		assertEquals(5, character.getStrength());
	}

	@Test
	public void getDexterity() throws Exception
	{
		assertEquals(6, character.getDexterity());
	}

	@Test
	public void getIntelligence() throws Exception
	{
		assertEquals(7, character.getIntelligence());
	}

	@Test
	public void getStealth() throws Exception
	{
		assertEquals(8, character.getStealth());
	}

	@Test
	public void getMoney() throws Exception
	{
		assertEquals(9, character.getMoney());
	}
}