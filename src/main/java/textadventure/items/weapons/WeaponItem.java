package textadventure.items.weapons;

import textadventure.items.EquipableItem;


public interface WeaponItem extends EquipableItem, Weapon
{
	/**
	 * Returns the name of the {@link WeaponItem}.
	 *
	 * @return The name of the {@link WeaponItem}.
	 */
	default String getWeaponName()
	{
		return getItemTypeName();
	}

	/**
	 * Returns the description of the {@link WeaponItem}.
	 *
	 * @return The description of the {@link WeaponItem}.
	 */
	default String getWeaponDescription()
	{
		return getItemTypeDescription();
	}
}
