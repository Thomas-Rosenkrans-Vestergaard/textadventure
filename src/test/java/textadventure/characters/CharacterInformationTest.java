package textadventure.characters;

import org.junit.Test;
import textadventure.combat.Faction;
import textadventure.combat.SomeFaction;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import java.awt.*;

import static org.junit.Assert.*;

public class CharacterInformationTest
{
	@Test
	public void getName() throws Exception
	{
		String               expected    = "Name";
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setName(expected);
		assertSame(expected, information.getName());
	}

	@Test
	public void getFaction() throws Exception
	{
		Faction              expected    = new SomeFaction();
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setFaction(expected);
		assertSame(expected, information.getFaction());
	}

	@Test
	public void getBackpack() throws Exception
	{
		Backpack             expected    = new Backpack();
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setBackpack(expected);
		assertSame(expected, information.getBackpack());
	}

	@Test
	public void getProtectiveFactor() throws Exception
	{
		double               expected    = 5.0;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setProtectiveFactor(expected);
		assertEquals(expected, information.getProtectiveFactor(), 0.001);
	}

	@Test
	public void getHeadWear() throws Exception
	{
		HeadWear expected = new HeadWear()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
			}

			@Override public String getItemTypeName()
			{
				return null;
			}

			@Override public String getItemTypeDescription()
			{
				return null;
			}
		};
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setHeadWear(expected);
		assertSame(expected, information.getHeadWear());
	}

	@Test
	public void getTorsoWear() throws Exception
	{
		TorsoWear expected = new TorsoWear()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
			}

			@Override public String getItemTypeName()
			{
				return null;
			}

			@Override public String getItemTypeDescription()
			{
				return null;
			}
		};
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setTorsoWear(expected);
		assertSame(expected, information.getTorsoWear());
	}

	@Test
	public void getGloves() throws Exception
	{
		Gloves expected = new Gloves()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
			}

			@Override public String getItemTypeName()
			{
				return null;
			}

			@Override public String getItemTypeDescription()
			{
				return null;
			}
		};
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setGloves(expected);
		assertSame(expected, information.getGloves());
	}

	@Test
	public void getPants() throws Exception
	{
		Pants expected = new Pants()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
			}

			@Override public String getItemTypeName()
			{
				return null;
			}

			@Override public String getItemTypeDescription()
			{
				return null;
			}
		};
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setPants(expected);
		assertSame(expected, information.getPants());
	}

	@Test
	public void getBoots() throws Exception
	{
		Boots expected = new Boots()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
			}

			@Override public String getItemTypeName()
			{
				return null;
			}

			@Override public String getItemTypeDescription()
			{
				return null;
			}
		};
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setBoots(expected);
		assertSame(expected, information.getBoots());
	}

	@Test
	public void getWeapon() throws Exception
	{
		Weapon expected = new Weapon()
		{
			@Override public int getNumberOfTargets()
			{
				return 0;
			}

			@Override public int getDamage()
			{
				return 0;
			}

			@Override public String getWeaponName()
			{
				return null;
			}

			@Override public String getWeaponDescription()
			{
				return null;
			}
		};
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setWeapon(expected);
		assertSame(expected, information.getWeapon());
	}

	@Test
	public void getCurrentLocation() throws Exception
	{
		Room                 expected    = new SomeRoom();
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setCurrentLocation(expected);
		assertSame(expected, information.getCurrentLocation());
	}

	@Test
	public void getCurrentHP() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setCurrentHP(expected);
		assertEquals(expected, information.getCurrentHP());
	}

	@Test
	public void getMaxHP() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setMaxHP(expected);
		assertEquals(expected, information.getMaxHP());
	}

	@Test
	public void getLevel() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setLevel(expected);
		assertEquals(expected, information.getLevel());
	}

	@Test
	public void getSanity() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setSanity(expected);
		assertEquals(expected, information.getSanity());
	}

	@Test
	public void getStrength() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setStrength(expected);
		assertEquals(expected, information.getStrength());
	}

	@Test
	public void getDexterity() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setDexterity(expected);
		assertEquals(expected, information.getDexterity());
	}

	@Test
	public void getIntelligence() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setIntelligence(expected);
		assertEquals(expected, information.getIntelligence());
	}

	@Test
	public void getStealth() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setStealth(expected);
		assertEquals(expected, information.getStealth());
	}

	@Test
	public void getMoney() throws Exception
	{
		int                  expected    = 12;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setMoney(expected);
		assertEquals(expected, information.getMoney());
	}

	@Test
	public void getStatus() throws Exception
	{
		Character.Status     expected    = Character.Status.ALIVE;
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		character.setStatus(expected);
		assertSame(expected, information.getStatus());
	}

	@Test
	public void equals() throws Exception
	{
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		assertTrue(information.equals(character));
	}

	@Test
	public void testHashCode() throws Exception
	{
		SomeCharacter        character   = new SomeCharacter();
		CharacterInformation information = new CharacterInformation(character);
		assertEquals(character.hashCode(), information.hashCode());
	}
}