package textadventure.items;

/**
 * Thrown when an provided position number is outside the permitted range of the {@link Inventory}.
 */
public class PositionRangeException extends InventoryException
{

	/**
	 * The provided position number.
	 */
	private int providedPosition;

	/**
	 * Creates a new {@link PositionRangeException}.
	 *
	 * @param inventory        The {@link Inventory} where the exception occurred.
	 * @param providedPosition The provided position number.
	 */
	public PositionRangeException(Inventory inventory, int providedPosition)
	{
		super(inventory);

		this.providedPosition = providedPosition;
	}

	/**
	 * Returns the provided position number.
	 *
	 * @return The provided position number.
	 */
	public int getProvidedPosition()
	{
		return this.providedPosition;
	}
}
