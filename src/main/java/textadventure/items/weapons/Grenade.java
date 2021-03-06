package textadventure.items.weapons;

import textadventure.items.EquipableItem;

public class Grenade extends AbstractSplashWeapon implements SplashWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link Grenade}.
	 */
	public Grenade()
	{
		super(20, 3);
	}

	/**
	 * Returns the name of the {@link Grenade}.
	 *
	 * @return The name of the {@link Grenade}.
	 */
	@Override public String getItemTypeName()
	{
		return "Grenade.";
	}

	/**
	 * Returns the description of the {@link Grenade}.
	 *
	 * @return The description of the {@link Grenade}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Watch out for the explosion.";
	}
}
