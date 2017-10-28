package textadventure.items.weapons;

import textadventure.items.Item;

public class Pipe implements BluntWeapon, Item
{
	/**
	 * Returns the name of the {@link Pipe}.
	 *
	 * @return The name of the {@link Pipe}.
	 */
	@Override public String getItemName()
	{
		return "Pipe";
	}

	/**
	 * Returns the description of the {@link Pipe}.
	 *
	 * @return The description of the {@link Pipe}.
	 */
	@Override public String getItemDescription()
	{
		return "Warning, not for tobacco, unless you're desperate.";
	}

	/**
	 * Returns the amount of damage done by the {@link Pipe} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Pipe} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}

}
