package textadventure.items.weapons;

import textadventure.items.Item;

public class ScrewDriver implements StabWeapon, Item
{

	/**
	 * Returns the name of the {@link ScrewDriver}.
	 *
	 * @return The name of the {@link ScrewDriver}.
	 */
	@Override public String getItemTypeName()
	{
		return "Screwdriver";
	}

	/**
	 * Returns the description of the {@link ScrewDriver}.
	 *
	 * @return The description of the {@link ScrewDriver}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "It's pointy.";
	}

	/**
	 * Returns the amount of damage done by the {@link ScrewDriver} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link ScrewDriver} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}

