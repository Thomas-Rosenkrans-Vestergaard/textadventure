package textadventure.items.weapons;

import textadventure.items.Item;

public class Plunger implements BluntWeapon, Item
{
	/**
	 * Returns the name of the {@link Plunger}.
	 *
	 * @return The name of the {@link Plunger}.
	 */
	@Override public String getItemTypeName()
	{
		return "Plunger";
	}

	/**
	 * Returns the description of the {@link Plunger}.
	 *
	 * @return The description of the {@link Plunger}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "The smell alone is dangerous.";
	}

	/**
	 * Returns the amount of damage done by the {@link Plunger} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Plunger} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}


}
