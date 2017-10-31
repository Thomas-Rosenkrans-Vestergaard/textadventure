package textadventure.items.weapons;

import textadventure.items.Item;

public class LegendaryRock extends AbstractProjectileWeapon implements Weapon, Item
{
	/**
	 * Creates a new {@link LegendaryRock}.
	 */
	public LegendaryRock()
	{
		super(1);
	}

	/**
	 * Returns the name of the {@link LegendaryRock}.
	 *
	 * @return The name of the {@link LegendaryRock}.
	 */
	@Override public String getItemTypeName()
	{
		return "The Legendary Rock";
	}

	/**
	 * Returns the description of the {@link LegendaryRock}.
	 *
	 * @return The description of the {@link LegendaryRock}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "It's a rock fact!";
	}
}
