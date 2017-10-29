package textadventure.ui.characterSelection;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class IncompleteCharacterExceptionTest
{
	@Test
	public void getCharacter() throws Exception
	{
		CharacterCreationTemplate    characterCreationTemplate = new CharacterCreationTemplate();
		IncompleteCharacterException exception                 = new IncompleteCharacterException(characterCreationTemplate);
		assertSame(characterCreationTemplate, exception.getCharacter());
	}
}