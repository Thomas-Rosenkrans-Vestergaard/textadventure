package textadventure.characters;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class IncompleteCharacterExceptionTest
{
	@Test
	public void getCharacterTemplate() throws Exception
	{
		CharacterCreationTemplate    character = new CharacterCreationTemplate();
		IncompleteCharacterException exception = new IncompleteCharacterException(character);
		assertSame(character, exception.getCharacterTemplate());
	}
}