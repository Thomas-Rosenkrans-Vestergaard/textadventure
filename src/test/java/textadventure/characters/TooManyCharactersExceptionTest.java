package textadventure.characters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TooManyCharactersExceptionTest
{
	@Test
	public void getMaximum() throws Exception
	{
		int                        expected  = 342;
		TooManyCharactersException exception = new TooManyCharactersException(expected);
		assertEquals(expected, exception.getMaximum());
	}
}