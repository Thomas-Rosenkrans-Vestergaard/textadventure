package textadventure.characters;

import textadventure.combat.Faction;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.rooms.Room;

import java.util.Objects;

/**
 * Readonly {@link Character}. The {@link Object#hashCode()} and {@link Object#equals(Object)} methods delegate to the
 * internal {@link Character} {@link Object#hashCode()} and {@link Object#equals(Object)} methods.
 */
public class CharacterInformation
{

	/**
	 * The {@link Character} that contains the information.
	 */
	private Character character;

	/**
	 * Creates a new {@link CharacterInformation}.
	 *
	 * @param character The {@link Character}.
	 */
	public CharacterInformation(Character character)
	{
		this.character = character;
	}

	/**
	 * Returns the name of the {@link Character}.
	 *
	 * @return The name of the {@link Character}.
	 */
	public String getName()
	{
		return character.getName();
	}

	/**
	 * Returns the {@link Faction} the {@link Character} belongs to.
	 *
	 * @return The {@link Faction} the {@link Character} belongs to.
	 */
	public Faction getFaction()
	{
		return character.getFaction();
	}

	/**
	 * Returns the {@link Backpack} of the {@link Character}.
	 *
	 * @return The {@link Backpack} of the {@link Character}.
	 */
	public Backpack getBackpack()
	{
		return character.getBackpack();
	}

	/**
	 * 1
	 * Return the double representing the protective factor of the {@link Wearable}s on the {@link Character}.
	 *
	 * @return The double representing the protective factor of the {@link Wearable}s on the {@link Character}.
	 */
	public double getProtectiveFactor()
	{
		return character.getProtectiveFactor();
	}

	/**
	 * Returns the {@link HeadWear} worn by the {@link Character}.
	 *
	 * @return The {@link HeadWear} worn by the {@link Character}.
	 */
	public HeadWear getHeadWear()
	{
		return character.getHeadWear();
	}

	/**
	 * Returns the {@link TorsoWear} worn by the {@link Character}.
	 *
	 * @return The {@link TorsoWear} worn by the {@link Character}.
	 */
	public TorsoWear getTorsoWear()
	{
		return character.getTorsoWear();
	}

	/**
	 * Returns the {@link Gloves} worn by the {@link Character}.
	 *
	 * @return The {@link Gloves} worn by the {@link Character}.
	 */
	public Gloves getGloves()
	{
		return character.getGloves();
	}

	/**
	 * Returns the {@link Gloves} worn by the {@link Character}.
	 *
	 * @return The {@link Gloves} worn by the {@link Character}.
	 */
	public Pants getPants()
	{
		return character.getPants();
	}

	/**
	 * Returns the {@link Boots} worn by the {@link Character}.
	 *
	 * @return The {@link Boots} worn by the {@link Character}.
	 */
	public Boots getBoots()
	{
		return character.getBoots();
	}

	/**
	 * return the {@link Weapon} equipped by the {@link Character}
	 *
	 * @return The {@link Weapon} equipped by the {@link Character}
	 */
	public Weapon getWeapon()
	{
		return character.getWeapon();
	}

	/**
	 * Returns the {@link Room} the {@link Character} is currently in.
	 *
	 * @return The {@link Room} the {@link Character} is currently in.
	 */
	public Room getCurrentLocation()
	{
		return character.getCurrentLocation();
	}

	/**
	 * Returns the current number of hit points that the {@link Character} has.
	 *
	 * @return The current number of hit points that the {@link Character} has.
	 */
	public int getCurrentHP()
	{
		return character.getCurrentHP();
	}

	/**
	 * Returns the maximum number of hit points that the {@link Character} can has.
	 *
	 * @return The maximum number of hit points that the {@link Character} can has.
	 */
	public int getMaxHP()
	{
		return character.getMaxHP();
	}

	/**
	 * Returns the level of the {@link Character}.
	 *
	 * @return The level of the {@link Character}.
	 */
	public int getLevel()
	{
		return character.getLevel();
	}

	/**
	 * Returns the level of sanity of the {@link Character}.
	 *
	 * @return The level of sanity of the {@link Character}.
	 */
	public int getSanity()
	{
		return character.getSanity();
	}

	/**
	 * Returns the strength level of the {@link Character}. Determines the damage done by the {@link Character} and the
	 * carry capacity of the {@link Character}.
	 *
	 * @return The strength level of the {@link Character}. Determines the damage done by the {@link Character} and the
	 * carry capacity of the {@link Character}.
	 */
	public int getStrength()
	{
		return character.getStrength();
	}

	/**
	 * Returns the dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 *
	 * @return The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 */
	public int getDexterity()
	{
		return character.getDexterity();
	}

	/**
	 * Returns the intelligence level of the {@link Character}.
	 *
	 * @return The intelligence level of the {@link Character}.
	 */
	public int getIntelligence()
	{
		return character.getIntelligence();
	}

	/**
	 * Returns the stealth level of the {@link Character}.
	 *
	 * @return The stealth level of the {@link Character}.
	 */
	public int getStealth()
	{
		return character.getStealth();
	}

	/**
	 * Returns the amount of money the {@link Character} has.
	 *
	 * @return The amount of money the {@link Character} has.
	 */
	public int getMoney()
	{
		return character.getMoney();
	}

	@Override public boolean equals(Object o)
	{
		if (o instanceof Character || o instanceof CharacterInformation) {
			return character.hashCode() == o.hashCode();
		}

		return false;
	}

	@Override public int hashCode()
	{
		return Objects.hash(character);
	}
}
