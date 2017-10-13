package textadventure.items.equipmentbox;

import textadventure.AbstractPropertyContainer;
import textadventure.items.inventory.Inventory;
import textadventure.lock.Lock;

public class EquipmentBox extends AbstractPropertyContainer
{

	/**
	 * The {@link Inventory} of the {@link EquipmentBox}.
	 */
	private Inventory inventory;

	/**
	 * The {@link Lock} on the {@link EquipmentBox}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link EquipmentBox} with the provided {@link Inventory}.
	 *
	 * @param inventory The {@link Inventory} of the {@link EquipmentBox}.
	 * @param lock      The {@link Lock} on the {@link EquipmentBox}.
	 */
	public EquipmentBox(Inventory inventory, Lock lock)
	{
		this.inventory = inventory;
		this.lock = lock;

		addProperty(lock);
	}

	/**
	 * Returns the name of the {@link textadventure.Property}. This name is used when accessing the
	 * {@link textadventure.Property}.
	 *
	 * @return The name of the {@link textadventure.Property}. This name is used when accessing the
	 * {@link textadventure.Property}.
	 */
	@Override public String getPropertyName()
	{
		return "equipment_box";
	}
}
