package textadventure.items;

public class InventoryFullException extends InventoryException
{
	public InventoryFullException(Inventory inventory)
	{
		super(inventory);
	}
}
