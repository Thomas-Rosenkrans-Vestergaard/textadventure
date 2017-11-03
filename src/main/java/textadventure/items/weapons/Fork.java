package textadventure.items.weapons;

import textadventure.items.EquipableItem;
import textadventure.items.Item;

public class Fork extends AbstractStabWeapon implements StabWeapon, EquipableItem, WeaponItem
{
	/**
	 * Creates a new {@link Fork}.
	 */
	public Fork()
	{
		super(6);
	}

	/**
	 * Returns the name of the {@link Fork}.
	 *
	 * @return The name of the {@link Fork}.
	 */
	@Override public String getItemTypeName()
	{
		return "Fork";
	}

	/**
	 * Returns the description of the {@link Fork}.
	 *
	 * @return The description of the {@link Fork}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "A substitute for chopsticks.";
	}
}
