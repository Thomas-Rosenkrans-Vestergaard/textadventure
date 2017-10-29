package textadventure.items.weapons;

import textadventure.items.Item;

public class Knife implements EdgedWeapon, StabWeapon, Item
{

	/**
	 * Returns the name of the {@link Knife}.
	 *
	 * @return The name of the {@link Knife}.
	 */
	@Override public String getItemTypeName()
	{
		return "Knife";
	}

	/**
	 * Returns the description of the {@link Knife}.
	 *
	 * @return The description of the {@link Knife}.
	 */
	@Override public String getItemTypeDescription()
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

	/**
	 * Increases the damage done by the {@link EdgedWeapon}.
	 *
	 * @param whetstone The {@link Whetstone} used to sharpen the {@link EdgedWeapon}.
	 * @return The damage increase.
	 */
	@Override public int sharpen(Whetstone whetstone)
	{
		return 0;
	}
}
