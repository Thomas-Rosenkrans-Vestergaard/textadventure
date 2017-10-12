package textadventure.rooms.features;

import textadventure.actions.AbstractFocusable;
import textadventure.actions.Focusable;
import textadventure.actions.doors.InspectLockAction;
import textadventure.actions.doors.LockLockAction;
import textadventure.actions.doors.UnlockLockAction;
import textadventure.items.DefaultInventory;
import textadventure.items.Inventory;
import textadventure.rooms.features.lock.Lock;
import textadventure.rooms.features.lock.Lockable;

public class EquipmentBox extends AbstractFocusable implements Lockable
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
		this.addAction(new InspectLockAction());
		this.addAction(new UnlockLockAction());
		this.addAction(new LockLockAction());
	}

	/**
	 * Returns the identifier use the identify the {@link Focusable} object. This identifier used to focus on the
	 * {@link Focusable} object using the <code>focus</code> command.
	 *
	 * @return The identifier use the identify the {@link Focusable} object. This identifier used to focus on the
	 * {@link Focusable} object using the <code>focus</code> command.
	 */
	@Override public String getIdentifier()
	{
		return "equipment_box";
	}

	/**
	 * Returns the {@link Lock} on the {@link EquipmentBox}.
	 *
	 * @return The {@link Lock} on the {@link EquipmentBox}.
	 */
	@Override public Lock getLock()
	{
		return this.lock;
	}
}
