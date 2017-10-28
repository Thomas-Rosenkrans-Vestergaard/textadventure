package textadventure.items.weapons;

import textadventure.items.Item;

public class Fork implements StabWeapon, Item
{

	/**
	 * Returns the name of the {@link Fork}.
	 *
	 * @return The name of the {@link Fork}.
	 */
	@Override public String getItemName()
	{
		return "Fork";
	}

	/**
	 * Returns the description of the {@link Fork}.
	 *
	 * @return The description of the {@link Fork}.
	 */
	@Override public String getItemDescription()
	{
		return "A substitute for chopsticks.";
	}

	/**
	 * Returns the amount of damage done by the {@link Fork} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Fork} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
