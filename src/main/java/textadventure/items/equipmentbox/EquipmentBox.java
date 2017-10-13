package textadventure.items.equipmentbox;

import textadventure.AbstractPropertyContainer;
import textadventure.items.backpack.Backpack;
import textadventure.lock.Lock;

public class EquipmentBox extends AbstractPropertyContainer
{

	/**
	 * The {@link Backpack} of the {@link EquipmentBox}.
	 */
	private Backpack backpack;

	/**
	 * The {@link Lock} on the {@link EquipmentBox}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link EquipmentBox} with the provided {@link Backpack}.
	 *
	 * @param backpack The {@link Backpack} of the {@link EquipmentBox}.
	 * @param lock      The {@link Lock} on the {@link EquipmentBox}.
	 */
	public EquipmentBox(Backpack backpack, Lock lock)
	{
		this.backpack = backpack;
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
