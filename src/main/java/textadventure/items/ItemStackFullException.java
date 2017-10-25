package textadventure.items;

public class ItemStackFullException extends ItemStackException
{
	private int amount;

	public ItemStackFullException(Inventory inventory, InventorySlot stack, int amount)
	{
		super(inventory, stack);

		this.amount = amount;
	}

	public int getAmount()
	{
		return this.amount;
	}
}
