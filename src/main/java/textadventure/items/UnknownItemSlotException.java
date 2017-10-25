package textadventure.items;

/**
 * Thrown when an illegal slot number is provided to the {@link Inventory}.
 */
public class UnknownItemSlotException extends InventoryException
{

	/**
	 * The unknown slot number.
	 */
	private int unknownSlot;

	/**
	 * Creates a new {@link UnknownItemSlotException}.
	 *
	 * @param inventory
	 * @param unknownSlot
	 */
	public UnknownItemSlotException(Inventory inventory, int unknownSlot)
	{
		super(inventory);

		this.unknownSlot = unknownSlot;
	}

	public int getUnknownSlot()
	{
		return this.unknownSlot;
	}
}
