package textadventure.items;

/**
 * Thrown when a position is empty in the {@link Inventory}.
 */
public class EmptyPositionException extends InventoryException
{

	/**
	 * The unknown position.
	 */
	private int position;

	/**
	 * Creates a new {@link EmptyPositionException}.
	 *
	 * @param inventory The {@link Inventory} where the exception occurred.
	 * @param position  The unknown position.
	 */
	public EmptyPositionException(Inventory inventory, int position)
	{
		super(inventory);
	}

	/**
	 * Returns the unknown position.
	 *
	 * @return The unknown position.
	 */
	public int getPosition()
	{
		return this.position;
	}
}
