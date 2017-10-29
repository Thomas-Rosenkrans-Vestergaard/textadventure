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
	 * The type the position expected.
	 */
	private Class expected;

	/**
	 * The type the position received.
	 */
	private Class actual;

	/**
	 * Creates a new {@link TypeMismatchException}.
	 *
	 * @param inventory The {@link Inventory} where the exception occurred.
	 * @param position  The position of the position where the exception occurred.
	 * @param expected  The type the position expected.
	 * @param actual    The type the position received.
	 */
	public TypeMismatchException(Inventory inventory, int position, Class expected, Class actual)
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
	 * Returns the type the position expected.
	 *
	 * @return The type the position expected.
	 */
	public Class getExpected()
	{
		return this.expected;
	}

	/**
	 * Returns the type the position received.
	 *
	 * @return The type the position received.
	 */
	public Class getActual()
	{
		return this.actual;
	}
}
