package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class GlassBottle implements StabWeapon, Item
{
	/**
	 * Returns the name of the {@link GlassBottle}.
	 *
	 * @return The name of the {@link GlassBottle}.
	 */
	@Override public String getItemTypeName()
	{
		return "Glass bottle";
	}

	/**
	 * Returns the description of the {@link GlassBottle}.
	 *
	 * @return The description of the {@link GlassBottle}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Very fragile, but lies around everywhere.";
	}

	/**
	 * Returns the amount of damage done by the {@link GlassBottle} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link GlassBottle} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
