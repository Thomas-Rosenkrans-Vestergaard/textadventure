package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class Pipe implements BluntWeapon, Item
{
	/**
	 * Returns the name of the {@link Pipe}.
	 *
	 * @return The name of the {@link Pipe}.
	 */
	@Override public String getItemTypeName()
	{
		return "Pipe";
	}

	/**
	 * Returns the description of the {@link Pipe}.
	 *
	 * @return The description of the {@link Pipe}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Warning, not for tobacco, unless you're desperate.";
	}

	/**
	 * Returns the amount of damage done by the {@link Pipe} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Pipe} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}

}
