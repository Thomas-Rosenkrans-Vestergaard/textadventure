package textadventure.items;

import textadventure.GameException;

/**
 * Thrown when exception occurs related to {@link Inventory}s.
 */
public abstract class InventoryException extends GameException
{

	/**
	 * The {@link Inventory} that caused the {@link InventoryException}.
	 */
	private Inventory inventory;

	/**
	 * Creates a new {@link InventoryException}.
	 *
	 * @param inventory The {@link Inventory} that caused the {@link InventoryException}.
	 */
	public InventoryException(Inventory inventory)
	{
		this.inventory = inventory;
	}

	/**
	 * Returns the {@link Inventory} that caused the {@link InventoryException}.
	 *
	 * @return The {@link Inventory} that caused the {@link InventoryException}.
	 */
	public Inventory getInventory()
	{
		return this.inventory;
	}
}
