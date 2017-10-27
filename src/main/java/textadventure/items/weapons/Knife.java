package textadventure.items.weapons;

import textadventure.items.Item;

public class Knife implements EdgedWeapon
{
	/**
	 * Returns the name of the {@link Knife}.
	 *
	 * @return The name of the {@link Knife}.
	 */
	@Override public String getItemName()
	{
		return "Knife";
	}

	/**
	 * Returns the description of the {@link Knife}.
	 *
	 * @return The description of the {@link Knife}.
	 */
	@Override public String getItemDescription()
	{
		return "Good for butter, if there was any.";
	}

	/**
	 * Returns the amount of damage done by the {@link Knife} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Knife} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}


	@Override public int sharpen(Item whetstone)
	{
		return 0;
	}
}
