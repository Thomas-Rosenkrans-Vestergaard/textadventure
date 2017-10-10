package textadventure;

import org.junit.Test;

import static org.junit.Assert.*;

class DirectionTest
{
	@Test
	void getInverse()
	{
		assertEquals(Direction.NORTH.getInverse(), Direction.SOUTH);
		assertEquals(Direction.SOUTH.getInverse(), Direction.NORTH);
		assertEquals(Direction.EAST.getInverse(), Direction.WEST);
		assertEquals(Direction.WEST.getInverse(), Direction.EAST);
	}
}