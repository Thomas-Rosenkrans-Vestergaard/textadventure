package textadventure.ui.characterSelection;

import org.junit.Test;
import textadventure.characters.CharacterCreationTemplate;
import textadventure.characters.CharacterNameTakenException;

import static org.junit.Assert.assertSame;

public class CharacterNameTakenExceptionTest
{
	@Test
	public void getName() throws Exception
	{
		String                    name                      = "Name";
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		characterCreationTemplate.setName("Name");
		CharacterNameTakenException exception = new CharacterNameTakenException(characterCreationTemplate);
		assertSame(name, exception.getName());
	}

	@Test
	public void getCharacter() throws Exception
	{
		CharacterCreationTemplate   characterCreationTemplate = new CharacterCreationTemplate();
		CharacterNameTakenException exception                 = new CharacterNameTakenException(characterCreationTemplate);
		assertSame(characterCreationTemplate, exception.getCharacterTemplate());
	}
}