package textadventure.rooms.features;

import textadventure.items.DefaultInventory;
import textadventure.items.Inventory;

public class EquipmentBox
{

	/**
	 * The {@link Inventory} of the {@link EquipmentBox}.
	 */
	private Inventory inventory;

	/**
	 * Creates a new {@link EquipmentBox} with an empty {@link Inventory}.
	 * @param numberOfSlots number of slots in EquipmentBox.
	 */
	public EquipmentBox(int numberOfSlots)
	{
		this(new DefaultInventory(numberOfSlots));
	}

	/**
	 * Creates a new {@link EquipmentBox} with the provided {@link Inventory}.
	 *
	 * @param inventory The {@link Inventory} of the {@link EquipmentBox}.
	 */
	public EquipmentBox(Inventory inventory)
	{
		this.inventory = inventory;
	}
}
