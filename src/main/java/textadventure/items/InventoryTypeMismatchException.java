package textadventure.items;

public class InventoryTypeMismatchException extends InventoryException
{
	private int inventorySlot;
	private int expected;
	private int actual;

	public InventoryTypeMismatchException(Inventory inventory, int inventorySlot, int expected, int actual)
	{
		super(inventory);
		this.inventorySlot = inventorySlot;
		this.expected = expected;
		this.actual = actual;
	}

	public int getInventorySlot()
	{
		return this.inventorySlot;
	}

	public int getExpected()
	{
		return this.expected;
	}

	public int getActual()
	{
		return this.actual;
	}
}
