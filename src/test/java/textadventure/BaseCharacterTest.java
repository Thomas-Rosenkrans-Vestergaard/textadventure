package textadventure;

import org.junit.Test;
import textadventure.combat.Faction;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;
import textadventure.ui.GameInterface;
import textadventure.ui.SomeGameInterface;
import textadventure.ui.characterSelection.CharacterCreationTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BaseCharacterTest
{
	@Test
	public void fromTemplate() throws Exception
	{
		Room                      room                      = new SomeRoom();
		GameInterface             gameInterface             = new SomeGameInterface();
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		String                    name                      = "Name";
		characterCreationTemplate.setName(name);
		Character character = BaseCharacter.factory(null, gameInterface, characterCreationTemplate, Faction.ESCAPEE, room);
		room.hasCharacter(character);
		assertSame(name, character.getName());
		assertEquals(Faction.ESCAPEE, character.getFaction());
		assertSame(room, character.getCurrentLocation());
	}

	@Test
	public void getProperties() throws Exception
	{
	}

	@Test
	public void getName() throws Exception
	{
	}

	@Test
	public void getFaction() throws Exception
	{
	}

	@Test
	public void getBackpack() throws Exception
	{
	}

	@Test
	public void getHeadWear() throws Exception
	{
	}

	@Test
	public void getTorsoWear() throws Exception
	{
	}

	@Test
	public void getGloves() throws Exception
	{
	}

	@Test
	public void getPants() throws Exception
	{
	}

	@Test
	public void getBoots() throws Exception
	{
	}

	@Test
	public void getWeapon() throws Exception
	{
	}

	@Test
	public void setHeadWear() throws Exception
	{
	}

	@Test
	public void setTorsoWear() throws Exception
	{
	}

	@Test
	public void setGloves() throws Exception
	{
	}

	@Test
	public void setPants() throws Exception
	{
	}

	@Test
	public void setBoots() throws Exception
	{
	}

	@Test
	public void setWeapon() throws Exception
	{
	}

	@Test
	public void getCurrentLocation() throws Exception
	{
	}

	@Test
	public void setCurrentLocation() throws Exception
	{
	}

	@Test
	public void getCurrentHP() throws Exception
	{
	}

	@Test
	public void getMaxHP() throws Exception
	{
	}

	@Test
	public void getLevel() throws Exception
	{
	}

	@Test
	public void getSanity() throws Exception
	{
	}

	@Test
	public void getStrength() throws Exception
	{
	}

	@Test
	public void getDexterity() throws Exception
	{
	}

	@Test
	public void getIntelligence() throws Exception
	{
	}

	@Test
	public void getStealth() throws Exception
	{
	}

	@Test
	public void getMoney() throws Exception
	{
	}

}