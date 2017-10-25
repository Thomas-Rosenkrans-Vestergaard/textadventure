package textadventure.items;

public class InventoryTypeMismatchException extends InventoryException
{
	private InventorySlot inventorySlot;
	private Class<? extends Item> expected;
	private Class<? extends Item> actual;
	public InventoryTypeMismatchException(Inventory inventory, InventorySlot inventorySlot, Class<? extends Item>
			expected, Class<? extends Item> actual)
	{
		super(inventory);

		this.inventorySlot = inventorySlot;
		this.expected = expected;
		this.actual = actual;

	}

	public InventorySlot getInventorySlot()
	{
		return this.inventorySlot;
	}

	public Class<? extends Item> getExpected()
	{
		return this.expected;
	}

	public Class<? extends Item> getActual()
	{
		return this.actual;
	}
}
