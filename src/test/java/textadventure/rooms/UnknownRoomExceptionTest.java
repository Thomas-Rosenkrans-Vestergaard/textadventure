package textadventure.rooms;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnknownRoomExceptionTest
{
	@Test
	public void getCoordinate() throws Exception
	{
		Coordinate           coordinate = Coordinate.of(4, 5);
		UnknownRoomException exception  = new UnknownRoomException(coordinate);
		assertSame(coordinate, exception.getCoordinate());
	}
}