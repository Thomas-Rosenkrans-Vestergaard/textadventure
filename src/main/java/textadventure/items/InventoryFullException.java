package textadventure.items;

/**
 * Thrown when an {@link Item} cannot fit int the {@link Inventory} on insertion.
 */
public class InventoryFullException extends InventoryException
{

	/**
	 * The {@link Item} that couldn't be inserted.
	 */
	private Item item;

	/**
	 * Creates a new {@link InventoryFullException}.
	 *
	 * @param inventory The {@link Inventory} where the exception occurred.
	 * @param item      The {@link Item} that couldn't be inserted.
	 */
	public InventoryFullException(Inventory inventory, Item item)
	{
		super(inventory);

		this.item = item;
	}

	/**
	 * Returns the {@link Item} that couldn't be inserted.
	 *
	 * @return The {@link Item} that couldn't be inserted.
	 */
	public Item getItem()
	{
		return this.item;
	}
}
