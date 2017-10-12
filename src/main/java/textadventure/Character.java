package textadventure;

import textadventure.items.Inventory;
import textadventure.rooms.Landmine;
import textadventure.rooms.Room;

public interface Character
{

	/**
	 * Returns the name of the {@link Character}.
	 *
	 * @return The name of the {@link Character}.
	 */
	String getName();

	/**
	 * Returns the {@link Inventory} of the {@link Character}.
	 *
	 * @return The {@link Inventory} of the {@link Character}.
	 */
	Inventory getInventory();

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
	 * Returns the intelligence level of the {@link Character}. Determines the chance to disarm
	 * {@link Landmine}s.
	 *
	 * @return The intelligence level of the {@link Character}. Determines the chance to disarm
	 * {@link Landmine}s.
	 */
	int getIntelligence();

	/**
	 * Returns the stealth level of the {@link Character}. Determines the chance to pickpocket other {@link Person}s.
	 *
	 * @return The stealth level of the {@link Character}. Determines the chance to pickpocket other {@link Person}s.
	 */
	int getStealth();

	/**
	 * Returns the amount of money the {@link Character} has.
	 *
	 * @return The amount of money the {@link Character} has.
	 */
	int getMoney();
}

