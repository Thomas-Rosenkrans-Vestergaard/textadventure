package textadventure.rooms;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest
{
	@Test
	public void of() throws Exception
	{
		Coordinate expected = new Coordinate(3, 6);
		Coordinate actual   = Coordinate.of(3, 6);
		assertEquals(expected, actual);
	}

	@Test
	public void getX() throws Exception
	{
		int        expected   = 4;
		Coordinate coordinate = new Coordinate(expected, 5);
		assertEquals(coordinate.getX(), expected);
	}

	@Test
	public void getY() throws Exception
	{
		int        expected   = 5;
		Coordinate coordinate = new Coordinate(4, expected);
		assertEquals(coordinate.getY(), expected);
	}

	@Test
	public void equals() throws Exception
	{
		Coordinate a = new Coordinate(3, 4);
		Coordinate b = new Coordinate(3, 4);
		Coordinate c = new Coordinate(4, 3);
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
	}

	@Test
	public void testHashCode() throws Exception
	{
		assertEquals(Coordinate.of(3, 4), Coordinate.of(3, 4));
		assertNotEquals(Coordinate.of(1, 2), Coordinate.of(2, 1));
	}

}