package textadventure.items.weapons;

import textadventure.items.EquipableItem;
import textadventure.items.Item;


public class Shotgun extends AbstractProjectileWeapon implements ProjectileWeapon, EquipableItem, WeaponItem
{

	/**
	 * Creates a new {@link Shotgun}.
	 */
	public Shotgun()
	{
		super(40);
	}

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
}

