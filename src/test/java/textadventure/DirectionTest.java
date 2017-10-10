package textadventure;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest
{
	@Test
	public void getInverse()
	{
		assertEquals(Direction.NORTH.getInverse(), Direction.SOUTH);
		assertEquals(Direction.SOUTH.getInverse(), Direction.NORTH);
		assertEquals(Direction.EAST.getInverse(), Direction.WEST);
		assertEquals(Direction.WEST.getInverse(), Direction.EAST);
	}
}