package textadventure.items;

import textadventure.GameException;

/**
 * Base exception when handling {@link Inventory} errors.
 */
public abstract class InventoryException extends GameException
{

	/**
	 * The {@link Inventory} being acted upon.
	 */
	private Inventory inventory;

	/**
	 * Creates a new {@link InventoryException}.
	 *
	 * @param inventory The {@link Inventory} being acted upon.
	 */
	public InventoryException(Inventory inventory)
	{
		this.inventory = inventory;
	}

	/**
	 * Returns the {@link Inventory} being acted upon.
	 *
	 * @return The {@link Inventory} being acted upon.
	 */
	public Inventory getInventory()
	{
		return this.inventory;
	}
}
