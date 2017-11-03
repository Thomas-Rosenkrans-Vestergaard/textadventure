package textadventure.items.weapons;

import textadventure.items.EquipableItem;
import textadventure.items.Item;

public class SemiAutomaticPistol extends AbstractProjectileWeapon implements ProjectileWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link SemiAutomaticPistol}.
	 */
	public SemiAutomaticPistol()
	{
		super(30);
	}

	/**
	 * Returns the name of the {@link SemiAutomaticPistol}.
	 *
	 * @return The name of the {@link SemiAutomaticPistol}.
	 */
	@Override public String getItemTypeName()
	{
		return "Semi-Automatic Pistol";
	}

	/**
	 * Returns the description of the {@link SemiAutomaticPistol}.
	 *
	 * @return The description of the {@link SemiAutomaticPistol}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Hope i can handle the Recoil";
	}

}
