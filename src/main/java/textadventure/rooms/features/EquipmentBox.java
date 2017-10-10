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
	 */
	public EquipmentBox()
	{
		this(new DefaultInventory());
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
