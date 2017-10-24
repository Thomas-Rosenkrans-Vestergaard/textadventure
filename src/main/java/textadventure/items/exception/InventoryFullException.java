package textadventure.items.exception;

import textadventure.items.Inventory;

public class InventoryFullException extends InventoryException
{
	public InventoryFullException(Inventory inventory)
	{
		super(inventory);
	}
}
