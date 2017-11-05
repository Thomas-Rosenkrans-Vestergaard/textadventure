package textadventure.items.weapons;

import textadventure.items.EquipableItem;

public class SubMachineGun extends AbstractProjectileWeapon implements ProjectileWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link SubMachineGun}.
	 */
	public SubMachineGun()
	{
		super(45);
	}

	/**
	 * Returns the name of the {@link SubMachineGun}.
	 *
	 * @return The name of the {@link SubMachineGun}.
	 */
	@Override public String getItemTypeName()
	{
		return "Sub machine gun";
	}

	/**
	 * Returns the description of the {@link SubMachineGun}.
	 *
	 * @return The description of the {@link SubMachineGun}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "High fire rate, may get too hot to the touch.";
	}
}
