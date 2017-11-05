package textadventure;

import textadventure.items.Equipable;
import textadventure.items.weapons.Weapon;

/**
 * The intersection between a {@link Weapon} and an {@link Equipable} object.
 */
public interface EquipableWeapon extends Equipable, Weapon
{

	/**
	 * Returns the name of the {@link EquipableWeapon}.
	 *
	 * @return The name of the {@link EquipableWeapon}.
	 */
	default String getEquipableName()
	{
		return getWeaponName();
	}

	/**
	 * Returns the description of the {@link EquipableWeapon}.
	 *
	 * @return The description of the {@link EquipableWeapon}.
	 */
	default String getEquipableDescription()
	{
		return getWeaponDescription();
	}
}
