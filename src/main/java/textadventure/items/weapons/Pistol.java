package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class Pistol implements ProjectileWeapon, Item
{
	/**
	 * Returns the name of the {@link Pistol}.
	 *
	 * @return The name of the {@link Pistol}.
	 */
	@Override public String getItemTypeName()
	{
		return "Pistol";
	}

	/**
	 * Returns the description of the {@link Pistol}.
	 *
	 * @return The description of the {@link Pistol}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Not fully assembled, but it might work.";
	}

	/**
	 * Returns the amount of damage done by the {@link Pistol} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Pistol} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
