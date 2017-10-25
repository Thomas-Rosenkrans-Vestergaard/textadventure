package textadventure.items;

public class ItemSlotFullException extends InventoryException
{
	private int amount;

	public ItemSlotFullException(Inventory inventory, int amount)
	{
		super(inventory);

		this.amount = amount;
	}

	public int getAmount()
	{
		return this.amount;
	}
}
