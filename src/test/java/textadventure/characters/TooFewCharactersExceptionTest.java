package textadventure.characters;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class TooFewCharactersExceptionTest
{
	@Test
	public void getMinimum() throws Exception
	{
		int                       expected  = 76;
		TooFewCharactersException exception = new TooFewCharactersException(expected, 0);
		assertSame(expected, exception.getMinimum());
	}

	@Test
	public void getActual() throws Exception
	{
		int                       expected  = 76;
		TooFewCharactersException exception = new TooFewCharactersException(100, expected);
		assertSame(expected, exception.getActual());
	}
}