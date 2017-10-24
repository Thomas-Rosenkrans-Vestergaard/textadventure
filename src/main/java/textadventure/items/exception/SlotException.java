package textadventure.items.exception;

import textadventure.items.Inventory;
import textadventure.items.Slot;

public class SlotException extends InventoryException
{

	/**
	 * The {@link Slot} being acted upon.
	 */
	private Slot slot;

	/**
	 * Creates a new {@link SlotException}.
	 *
	 * @param inventory The {@link Inventory} being acted upon.
	 * @param slot      The {@link Slot} being acted upon.
	 */
	public SlotException(Inventory inventory, Slot slot)
	{
		super(inventory);
		this.slot = slot;
	}

	/**
	 * Returns the {@link Slot} being acted upon.
	 *
	 * @return The {@link Slot} being acted upon.
	 */
	public Slot getSlot()
	{
		return this.slot;
	}
}
