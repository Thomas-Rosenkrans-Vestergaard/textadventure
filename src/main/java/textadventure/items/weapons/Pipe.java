package textadventure.items.weapons;

import textadventure.items.EquipableItem;
import textadventure.items.Item;

public class Pipe extends AbstractBluntWeapon implements BluntWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link Pipe}.
	 */
	public Pipe()
	{
		super(10);
	}

	/**
	 * Returns the name of the {@link Pipe}.
	 *
	 * @return The name of the {@link Pipe}.
	 */
	@Override public String getItemTypeName()
	{
		return "Pipe";
	}

	/**
	 * Returns the description of the {@link Pipe}.
	 *
	 * @return The description of the {@link Pipe}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Warning, not for tobacco, unless you're desperate.";
	}
}
