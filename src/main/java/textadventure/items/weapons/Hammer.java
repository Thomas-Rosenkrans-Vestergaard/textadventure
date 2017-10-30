package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class Hammer implements BluntWeapon, Item
{
	/**
	 * Returns the name of the {@link Hammer}.
	 *
	 * @return The name of the {@link Hammer}.
	 */
	@Override public String getItemTypeName()
	{
		return "Hammer";
	}

	/**
	 * Returns the description of the {@link Hammer}.
	 *
	 * @return The description of the {@link Hammer}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Has broken many nails.";
	}

	/**
	 * Returns the amount of damage done by the {@link Hammer} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Hammer} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}

}
