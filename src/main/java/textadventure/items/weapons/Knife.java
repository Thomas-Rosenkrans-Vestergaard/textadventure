package textadventure.items.weapons;

import textadventure.items.EquipableItem;

public class Knife extends AbstractEdgedWeapon implements StabWeapon, EdgedWeapon, EquipableItem, WeaponItem
{

	/**
	 * Creates a new {@link Knife}.
	 */
	public Knife()
	{
		super(10);
	}

	/**
	 * Returns the name of the {@link Knife}.
	 *
	 * @return The name of the {@link Knife}.
	 */
	@Override public String getItemTypeName()
	{
		return "Knife";
	}

	/**
	 * Returns the description of the {@link Knife}.
	 *
	 * @return The description of the {@link Knife}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Good for butter, if there was any.";
	}
}
