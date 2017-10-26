package textadventure.items;

/**
 * Thrown when an {@link Item} of a wrong type added to a slot.
 */
public class InventoryTypeMismatchException extends InventoryException
{

	/**
	 * The position of the slot where the exception occurred.
	 */
	private int inventorySlot;

	/**
	 * The {@link ItemType} the slot expected.
	 */
	private ItemType expectedType;

	/**
	 * The {@link ItemType} the slot received.
	 */
	private ItemType actualType;

	/**
	 * Creates a new {@link InventoryTypeMismatchException}.
	 *
	 * @param inventory     The {@link Inventory} where the exception occurred.
	 * @param inventorySlot The position of the slot where the exception occurred.
	 * @param expectedType  The {@link ItemType} the slot expected.
	 * @param actualType    The {@link ItemType} the slot received.
	 */
	public InventoryTypeMismatchException(Inventory inventory, int inventorySlot, ItemType expectedType, ItemType actualType)
	{
		super(inventory);
		this.inventorySlot = inventorySlot;
		this.expectedType = expectedType;
		this.actualType = actualType;
	}

	/**
	 * Returns the position of the slot where the exception occurred.
	 *
	 * @return The position of the slot where the exception occurred.
	 */
	public int getInventorySlot()
	{
		return this.inventorySlot;
	}

	/**
	 * Returns the {@link ItemType} the slot expected.
	 *
	 * @return The {@link ItemType} the slot expected.
	 */
	public ItemType getExpectedType()
	{
		return this.expectedType;
	}

	/**
	 * Returns the {@link ItemType} the slot received.
	 *
	 * @return The {@link ItemType} the slot received.
	 */
	public ItemType getActual()
	{
		return this.actualType;
	}
}
