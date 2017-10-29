package textadventure.items.weapons;

import textadventure.items.Item;

public class Wrench implements BluntWeapon, Item
{

	/**
	 * Returns the name of the {@link Wrench}.
	 *
	 * @return The name of the {@link Wrench}.
	 */
	@Override public String getItemTypeName()
	{
		return "Wrench";
	}

	/**
	 * Returns the description of the {@link Wrench}.
	 *
	 * @return The description of the {@link Wrench}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Seems to be made of aluminium.";
	}

	/**
	 * Returns the amount of damage done by the {@link Wrench} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Wrench} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}

}
