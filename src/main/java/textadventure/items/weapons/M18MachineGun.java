package textadventure.items.weapons;

import textadventure.items.EquipableItem;

public class M18MachineGun extends AbstractProjectileWeapon implements ProjectileWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link SubMachineGun}.
	 */
	public M18MachineGun()
	{
		super(55);
	}

	/**
	 * Returns the name of the {@link M18MachineGun}.
	 *
	 * @return The name of the {@link M18MachineGun}.
	 */
	@Override public String getItemTypeName()
	{
		return "M18 machine gun.";
	}

	/**
	 * Returns the description of the {@link M18MachineGun}.
	 *
	 * @return The description of the {@link M18MachineGun}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "High fire rate, and very high recoil.";
	}

}
