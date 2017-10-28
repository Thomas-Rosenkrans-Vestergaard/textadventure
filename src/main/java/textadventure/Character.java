package textadventure;

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
	 * The default number of slots the {@link Character} has in their {@link Backpack} at the start of the game.
	 */
	int DEFAULT_SLOTS = 10;

	/**
	 * The default maximum amount of HP the {@link Character} has at the start of the game.
	 */
	int DEFAULT_MAX_HP = 100;

	/**
	 * The default level of the {@link Character} at the start of the game.
	 */
	int DEFAULT_LEVEL = 1;

	/**
	 * The default sanity of the {@link Character} at the start of the game.
	 */
	int DEFAULT_SANITY = 100;

	/**
	 * The default strength of the {@link Character} at the start of the game.
	 */
	int DEFAULT_STRENGTH = 1;

	/**
	 * The default dexterity of the {@link Character} at the start of the game.
	 */
	int DEFAULT_DEXTERITY = 1;

	/**
	 * The default intelligence of the {@link Character} at the start of the game.
	 */
	int DEFAULT_INTELLIGENCE = 1;

	/**
	 * The default stealth of the {@link Character} at the start of the game.
	 */
	int DEFAULT_STEALTH = 1;

	/**
	 * The default amount of money the {@link Character} has at the start of the game.
	 */
	int DEFAULT_MONEY = 0;

	/**
	 * Returns the name of the {@link Character}.
	 *
	 * @return The name of the {@link Character}.
	 */
	String getName();

	/**
	 * Returns the {@link Backpack} of the {@link Character}.
	 *
	 * @return The {@link Backpack} of the {@link Character}.
	 */
	Backpack getBackpack();

	/**
	 * Returns the {@link Boots} of the {@link Character}.
	 *
	 * @return The {@link Boots} of the {@link Character}.
	 */
	Boots getBoots();

	/**
	 * Sets the {@link Boots} of the {@link Character}.
	 *
	 * @param boots The {@link Boots} to set.
	 */
	void setBoots(Boots boots);

	/**
	 * Returns the {@link Torsowear} of the {@link Character}.
	 *
	 * @return The {@link Torsowear} of the {@link Character}.
	 */
	Torsowear getTorso();

	/**
	 * Sets the {@link Torsowear} of the {@link Character}.
	 *
	 * @param torso The {@link Torsowear} to set.
	 */
	void setTorso(Torsowear torso);

	/**
	 * Returns the {@link Gloves} of the {@link Character}.
	 *
	 * @return The {@link Gloves} of the {@link Character}.
	 */
	Gloves getGloves();

	/**
	 * Sets the {@link Gloves} of the {@link Character}.
	 *
	 * @param gloves The {@link Gloves} to set.
	 */
	void setGloves(Gloves gloves);
	
	/**
	 * Returns the {@link Headwear} of the {@link Character}.
	 *
	 * @return The {@link Headwear} of the {@link Character}.
	 */
	Headwear getHeadwear();

	/**
	 * Sets the {@link Headwear} of the {@link Character}.
	 *
	 * @param headwear The {@link Headwear} to set.
	 */
	void setHeadwear(Headwear headwear);
	
	/**
	 * Returns the {@link Gloves} of the {@link Character}.
	 *
	 * @return The {@link Gloves} of the {@link Character}.
	 */
	Pants getPants();

	/**
	 * Sets the {@link Pants} of the {@link Character}.
	 *
	 * @param pants The {@link Pants} to set.
	 */
	void setPants(Pants pants);
	
	/**
	 * return the {@link Weapon} of the {@link Character}
	 *
	 * @return The {@link Weapon} of the {@link Character}
	 */
	Weapon getWeapon();

	/**
	 * Sets the {@link Weapon} of the {@link Character}.
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
}