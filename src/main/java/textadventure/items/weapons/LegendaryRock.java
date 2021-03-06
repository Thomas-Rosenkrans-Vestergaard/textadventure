package textadventure.items.weapons;

import textadventure.items.EquipableItem;

public class LegendaryRock extends AbstractProjectileWeapon implements Weapon, EquipableItem, WeaponItem
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
