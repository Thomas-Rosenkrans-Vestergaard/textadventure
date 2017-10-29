package textadventure.ui.characterSelection;

import org.junit.Test;
import textadventure.combat.Faction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CharacterCreationTemplateTest
{
	@Test
	public void getName() throws Exception
	{
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		assertNull(characterCreationTemplate.getName());
		characterCreationTemplate.setName("Name");
		assertEquals("Name", characterCreationTemplate.getName());
	}

	@Test
	public void setName() throws Exception
	{
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		assertNull(characterCreationTemplate.getName());
		characterCreationTemplate.setName("Name");
		assertEquals("Name", characterCreationTemplate.getName());
	}

	@Test
	public void getFaction() throws Exception
	{
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		assertNull(characterCreationTemplate.getName());
		characterCreationTemplate.setFaction(Faction.ESCAPEE);
		assertEquals(Faction.ESCAPEE, characterCreationTemplate.getFaction());
	}

	@Test
	public void setFaction() throws Exception
	{
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		assertNull(characterCreationTemplate.getName());
		characterCreationTemplate.setFaction(Faction.ESCAPEE);
		assertEquals(Faction.ESCAPEE, characterCreationTemplate.getFaction());
	}
}