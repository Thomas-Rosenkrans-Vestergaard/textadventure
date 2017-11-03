package textadventure.items;

import textadventure.characters.EquipAction;
import textadventure.characters.UnEquipAction;

/**
 * An interface declaring the {@link Item}s that can be equipped or unequipped using the {@link EquipAction} or
 * {@link UnEquipAction}.
 */
public interface EquipableItem extends Item, Equipable
{

	/**
	 * Returns the name of the {@link EquipableItem}.
	 *
	 * @return The name of the {@link EquipableItem}.
	 */
	@Override default String getEquipableName()
	{
		return getItemTypeName();
	}

	/**
	 * Returns the description of the {@link EquipableItem}.
	 *
	 * @return The description of the {@link EquipableItem}.
	 */
	@Override default String getEquipableDescription()
	{
		return getItemTypeDescription();
	}
}
