package textadventure.characters;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class CharacterCreationTemplateTest
{
	@Test
	public void getName() throws Exception
	{
		String                    expected = "Name";
		CharacterCreationTemplate template = new CharacterCreationTemplate();
		assertNull(template.getName());
		template.setName(expected);
		assertSame(expected, template.getName());
	}

	@Test
	public void setName() throws Exception
	{
		String                    expected = "Name";
		CharacterCreationTemplate template = new CharacterCreationTemplate();
		assertNull(template.getName());
		template.setName(expected);
		assertSame(expected, template.getName());
	}
}