package textadventure.select.characterSelection;

import org.junit.Test;
import textadventure.characters.TooManyCharactersException;

import static org.junit.Assert.assertEquals;

public class TooManyCharactersExceptionTest
{
	@Test
	public void getMaximum() throws Exception
	{
		TooManyCharactersException exception = new TooManyCharactersException(5);
		assertEquals(5, exception.getMaximum());
	}
}