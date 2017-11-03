package textadventure.items.weapons;

import textadventure.items.EquipableItem;
import textadventure.items.Item;

public class Plunger extends AbstractBluntWeapon implements BluntWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link Plunger}.
	 */
	public Plunger()
	{
		super(6);
	}

	/**
	 * Returns the name of the {@link Plunger}.
	 *
	 * @return The name of the {@link Plunger}.
	 */
	@Override public String getItemTypeName()
	{
		return "Plunger";
	}

	/**
	 * Returns the description of the {@link Plunger}.
	 *
	 * @return The description of the {@link Plunger}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "The smell alone is dangerous.";
	}
}
