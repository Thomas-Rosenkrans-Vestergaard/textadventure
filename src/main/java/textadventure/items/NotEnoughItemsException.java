package textadventure.items;

/**
 * Thrown when some request for {@link Item}s could not be served by the {@link Inventory}.
 */
public class NotEnoughItemsException extends InventoryException
{

	/**
	 * The {@link ItemType} of the requested {@link Item}.
	 */
	private ItemType type;

	/**
	 * The amount of {@link Item}s that was requested.
	 */
	private int requested;

	/**
	 * The number of {@link Item}s that could be requested.
	 */
	private int actual;

	/**
	 * Creates a new {@link NotEnoughItemsException}.
	 *
	 * @param inventory The {@link Inventory} where the exception occurred.
	 * @param type      The {@link ItemType} of the requested {@link Item}.
	 * @param requested The amount of {@link Item}s that was requested.
	 * @param actual    The number of {@link Item}s that could be requested.
	 */
	public NotEnoughItemsException(Inventory inventory, ItemType type, int requested, int actual)
	{
		super(inventory);
		this.type = type;
		this.requested = requested;
		this.actual = actual;
	}

	/**
	 * Returns the {@link ItemType} of the requested {@link Item}.
	 *
	 * @return The {@link ItemType} of the requested {@link Item}.
	 */
	public ItemType getType()
	{
		return this.type;
	}

	/**
	 * Returns the amount of {@link Item}s that was requested.
	 *
	 * @return The amount of {@link Item}s that was requested.
	 */
	public int getRequested()
	{
		return this.requested;
	}

	/**
	 * Returns the number of {@link Item}s that could be requested.
	 *
	 * @return The number of {@link Item}s that could be requested.
	 */
	public int getActual()
	{
		return this.actual;
	}
}
