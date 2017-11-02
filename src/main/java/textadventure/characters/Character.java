package textadventure.characters;

import textadventure.PropertyContainer;
import textadventure.combat.DamageSource;
import textadventure.combat.Faction;
import textadventure.combat.HealingSource;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.rooms.Room;

/**
 * Represents a controllable in-game person.
 */
public interface Character extends PropertyContainer
{

	/**
	 * Returns the name of the {@link Character}.
	 *
	 * @return The name of the {@link Character}.
	 */
	String getName();

	/**
	 * Returns the {@link Faction} the {@link Character} belongs to.
	 *
	 * @return The {@link Faction} the {@link Character} belongs to.
	 */
	Faction getFaction();

	/**
	 * Returns the {@link Backpack} of the {@link Character}.
	 *
	 * @return The {@link Backpack} of the {@link Character}.
	 */
	Backpack getBackpack();

	/**
	 * Return the double representing the protective factor of the {@link Wearable}s on the {@link Character}.
	 *
	 * @return The double representing the protective factor of the {@link Wearable}s on the {@link Character}.
	 */
	double getProtectiveFactor();

	/**
	 * Returns the {@link HeadWear} worn by the {@link Character}.
	 *
	 * @return The {@link HeadWear} worn by the {@link Character}.
	 */
	HeadWear getHeadWear();

	/**
	 * Returns the {@link TorsoWear} worn by the {@link Character}.
	 *
	 * @return The {@link TorsoWear} worn by the {@link Character}.
	 */
	TorsoWear getTorsoWear();

	/**
	 * Returns the {@link Gloves} worn by the {@link Character}.
	 *
	 * @return The {@link Gloves} worn by the {@link Character}.
	 */
	Gloves getGloves();

	/**
	 * Returns the {@link Gloves} worn by the {@link Character}.
	 *
	 * @return The {@link Gloves} worn by the {@link Character}.
	 */
	Pants getPants();

	/**
	 * Returns the {@link Boots} worn by the {@link Character}.
	 *
	 * @return The {@link Boots} worn by the {@link Character}.
	 */
	Boots getBoots();

	/**
	 * return the {@link Weapon} equipped by the {@link Character}
	 *
	 * @return The {@link Weapon} equipped by the {@link Character}
	 */
	Weapon getWeapon();

	/**
	 * Sets the {@link HeadWear} worn by the {@link Character}.
	 *
	 * @param headWear The {@link HeadWear} to set.
	 */
	void setHeadWear(HeadWear headWear);

	/**
	 * Sets the {@link TorsoWear} worn by the {@link Character}.
	 *
	 * @param torsoWear The {@link TorsoWear} to set.
	 */
	void setTorsoWear(TorsoWear torsoWear);

	/**
	 * Sets the {@link Gloves} worn by the {@link Character}.
	 *
	 * @param gloves The {@link Gloves} to set.
	 */
	void setGloves(Gloves gloves);

	/**
	 * Sets the {@link Pants} worn by the {@link Character}.
	 *
	 * @param pants The {@link Pants} to set.
	 */
	void setPants(Pants pants);

	/**
	 * Sets the {@link Boots} worn by the {@link Character}.
	 *
	 * @param boots The {@link Boots} to set.
	 */
	void setBoots(Boots boots);

	/**
	 * Sets the {@link Weapon} equipped by the {@link Character}.
	 *
	 * @param weapon The {@link Weapon} to set.
	 */
	void setWeapon(Weapon weapon);

	/**
	 * Returns the {@link Room} the {@link Character} is currently in.
	 *
	 * @return The {@link Room} the {@link Character} is currently in.
	 */
	Room getCurrentLocation();

	/**
	 * Updates the {@link Room} the {@link Character} is currently in.
	 *
	 * @param room The {@link Room} to update to.
	 */
	void setCurrentLocation(Room room);

	/**
	 * Returns the current number of hit points that the {@link Character} has.
	 *
	 * @return The current number of hit points that the {@link Character} has.
	 */
	int getCurrentHP();

	/**
	 * Returns the maximum number of hit points that the {@link Character} can has.
	 *
	 * @return The maximum number of hit points that the {@link Character} can has.
	 */
	int getMaxHP();

	/**
	 * Returns the level of the {@link Character}.
	 *
	 * @return The level of the {@link Character}.
	 */
	int getLevel();

	/**
	 * Returns the level of sanity of the {@link Character}.
	 *
	 * @return The level of sanity of the {@link Character}.
	 */
	int getSanity();

	/**
	 * Returns the strength level of the {@link Character}. Determines the damage done by the {@link Character} and the
	 * carry capacity of the {@link Character}.
	 *
	 * @return The strength level of the {@link Character}. Determines the damage done by the {@link Character} and the
	 * carry capacity of the {@link Character}.
	 */
	int getStrength();

	/**
	 * Returns the dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 *
	 * @return The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 */
	int getDexterity();

	/**
	 * Returns the intelligence level of the {@link Character}.
	 *
	 * @return The intelligence level of the {@link Character}.
	 */
	int getIntelligence();

	/**
	 * Returns the stealth level of the {@link Character}.
	 *
	 * @return The stealth level of the {@link Character}.
	 */
	int getStealth();

	/**
	 * Returns the amount of money the {@link Character} has.
	 *
	 * @return The amount of money the {@link Character} has.
	 */
	int getMoney();

	/**
	 * Causes the {@link Character} to take damage from the provided {@link DamageSource}.
	 *
	 * @param damageSource The {@link DamageSource}.
	 * @return How much damage was taken.
	 */
	int takeDamage(DamageSource damageSource);

	/**
	 * Causes the {@link Character} to receive hp from the provided {@link HealingSource}.
	 *
	 * @param healingSource The {@link HealingSource}.
	 * @return How much the {@link Character} healed.
	 */
	int takeHealing(HealingSource healingSource);

	enum Status
	{
		DEAD,
		ALIVE,
	}

	/**
	 * Returns the life status of the character
	 *
	 * @return if the character is dead or alive
	 */
	 Status getStatus();
}