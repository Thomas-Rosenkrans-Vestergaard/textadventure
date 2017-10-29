package textadventure.items;

/**
 * Thrown when an {@link Item} of a wrong type added to a position.
 */
public class TypeMismatchException extends InventoryException
{

	/**
	 * The position of the position where the exception occurred.
	 */
	private int position;

	/**
	 * The {@link ItemType} the position expected.
	 */
	private ItemType expected;

	/**
	 * The {@link ItemType} the position received.
	 */
	private ItemType actual;

	/**
	 * Creates a new {@link TypeMismatchException}.
	 *
	 * @param inventory The {@link Inventory} where the exception occurred.
	 * @param position  The position of the position where the exception occurred.
	 * @param expected  The {@link ItemType} the position expected.
	 * @param actual    The {@link ItemType} the position received.
	 */
	public TypeMismatchException(Inventory inventory, int position, ItemType expected, ItemType actual)
	{
		super(inventory);
		this.position = position;
		this.expected = expected;
		this.actual = actual;
	}

	/**
	 * Returns the position of the position where the exception occurred.
	 *
	 * @return The position of the position where the exception occurred.
	 */
	public int getPosition()
	{
		return this.position;
	}

	/**
	 * Returns the {@link ItemType} the position expected.
	 *
	 * @return The {@link ItemType} the position expected.
	 */
	public ItemType getExpected()
	{
		return this.expected;
	}

	/**
	 * Returns the {@link ItemType} the position received.
	 *
	 * @return The {@link ItemType} the position received.
	 */
	public ItemType getActual()
	{
		return this.actual;
	}
}
