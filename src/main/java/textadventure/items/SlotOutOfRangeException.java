package textadventure.items;

/**
 * Thrown when an provided slot number is outside the permitted range of the {@link Inventory}.
 */
public class SlotOutOfRangeException extends InventoryException
{

	/**
	 * The provided slot number.
	 */
	private int providedSlot;

	/**
	 * Creates a new {@link SlotOutOfRangeException}.
	 *
	 * @param inventory    The {@link Inventory} where the exception occurred.
	 * @param providedSlot The provided slot number.
	 */
	public SlotOutOfRangeException(Inventory inventory, int providedSlot)
	{
		super(inventory);

		this.providedSlot = providedSlot;
	}

	/**
	 * Returns the provided slot number.
	 *
	 * @return The provided slot number.
	 */
	public int getProvidedSlot()
	{
		return this.providedSlot;
	}
}
