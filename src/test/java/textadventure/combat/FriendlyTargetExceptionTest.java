package textadventure.combat;

import org.junit.Test;
import textadventure.characters.SomeCharacter;
import textadventure.characters.Character;

import static org.junit.Assert.assertSame;

public class FriendlyTargetExceptionTest
{
	@Test
	public void getInstigator() throws Exception
	{
		Character               expected  = new SomeCharacter();
		FriendlyTargetException exception = new FriendlyTargetException(expected, null);
		assertSame(expected, exception.getInstigator());
	}

	@Test
	public void getTarget() throws Exception
	{
		Character               expected  = new SomeCharacter();
		FriendlyTargetException exception = new FriendlyTargetException(null, expected);
		assertSame(expected, exception.getTarget());
	}
}