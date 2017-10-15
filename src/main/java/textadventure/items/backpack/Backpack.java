package textadventure.items.backpack;

import textadventure.items.BaseInventory;
import textadventure.items.Inventory;

/**
 * Represents a {@link Inventory} the {@link textadventure.Player} can carry.
 */
public class Backpack extends BaseInventory
{

	/**
	 * Creates a new {@link Backpack}.
	 *
	 * @param countSlots The number of slots in the {@link Backpack}.
	 */
	public Backpack(int countSlots)
	{
		super(countSlots);
	}
}
