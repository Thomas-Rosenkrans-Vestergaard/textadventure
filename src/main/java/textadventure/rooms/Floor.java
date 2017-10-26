package textadventure.rooms;

import textadventure.items.BaseInventory;
import textadventure.items.Item;

/**
 * Represents an {@link textadventure.items.Inventory} containing {@link textadventure.items.Item}s on the ground.
 */
public class Floor extends BaseInventory
{

	/**
	 * Creates a new {@link Floor}.
	 *
	 * @param numberOfSlots The number of available slots in the {@link textadventure.items.Inventory}.
	 */
	public Floor(int numberOfSlots)
	{
		super(numberOfSlots);
	}
}
