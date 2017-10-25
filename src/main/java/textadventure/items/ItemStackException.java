package textadventure.items;

public class ItemStackException extends InventoryException
{
	private InventorySlot stack;

	public ItemStackException(Inventory inventory, InventorySlot stack)
	{
		super(inventory);
		this.stack = stack;
	}

	public InventorySlot getStack()
	{
		return this.stack;
	}
}
