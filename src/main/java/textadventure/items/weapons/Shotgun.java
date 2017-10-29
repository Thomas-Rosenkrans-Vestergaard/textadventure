package textadventure.items.weapons;

import textadventure.items.Item;

public class Shotgun implements ProjectileWeapon, Item
{

	/**
	 * Returns the name of the {@link Shotgun}.
	 *
	 * @return The name of the {@link Shotgun}.
	 */
	@Override public String getItemTypeName()
	{
		return "Shotgun";
	}

	/**
	 * Returns the description of the {@link Shotgun}.
	 *
	 * @return The description of the {@link Shotgun}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Apparently not used for hunting.";
	}

	/**
	 * Returns the amount of damage done by the {@link Shotgun} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Shotgun} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
