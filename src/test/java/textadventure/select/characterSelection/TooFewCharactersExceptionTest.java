package textadventure.select.characterSelection;

import org.junit.Test;
import textadventure.characters.TooFewCharactersException;

import static org.junit.Assert.assertEquals;

public class TooFewCharactersExceptionTest
{

	@Test
	public void getMinimum() throws Exception
	{
		TooFewCharactersException exception = new TooFewCharactersException(5, 1);
		assertEquals(5, exception.getMinimum());
	}

	@Test
	public void getActual() throws Exception
	{
		TooFewCharactersException exception = new TooFewCharactersException(5, 1);
		assertEquals(1, exception.getActual());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorThrowsException()
	{
		new TooFewCharactersException(1, 5);
	}
}