package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class Baton implements BluntWeapon, Item
{

	/**
	 * Returns the name of the {@link Baton}.
	 *
	 * @return The name of the {@link Baton}.
	 */
	@Override public String getItemTypeName()
	{
		return "Baton";
	}

	/**
	 * Returns the description of the {@link Baton}.
	 *
	 * @return The description of the {@link Baton}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Efficient for handling riots.";
	}

	/**
	 * Returns the amount of damage done by the {@link Baton} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Baton} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
