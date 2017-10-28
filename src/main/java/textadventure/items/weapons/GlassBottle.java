package textadventure.items.weapons;

import textadventure.items.Item;

public class GlassBottle implements StabWeapon, Item
{
	/**
	 * Returns the name of the {@link GlassBottle}.
	 *
	 * @return The name of the {@link GlassBottle}.
	 */
	@Override public String getItemName()
	{
		return "Glass bottle";
	}

	/**
	 * Returns the description of the {@link GlassBottle}.
	 *
	 * @return The description of the {@link GlassBottle}.
	 */
	@Override public String getItemDescription()
	{
		return "Very fragile, but lies around everywhere.";
	}

	/**
	 * Returns the amount of damage done by the {@link GlassBottle} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link GlassBottle} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
