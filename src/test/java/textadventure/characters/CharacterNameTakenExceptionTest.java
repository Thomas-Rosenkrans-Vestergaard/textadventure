package textadventure.characters;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class CharacterNameTakenExceptionTest
{
	@Test
	public void getName() throws Exception
	{
		String                    expected = "Name";
		CharacterCreationTemplate template = new CharacterCreationTemplate();
		template.setName(expected);
		CharacterNameTakenException exception = new CharacterNameTakenException(template);
		assertSame(expected, exception.getName());
	}

	@Test
	public void getCharacter() throws Exception
	{
		CharacterCreationTemplate   template  = new CharacterCreationTemplate();
		CharacterNameTakenException exception = new CharacterNameTakenException(template);
		assertSame(template, exception.getCharacterTemplate());
	}
}