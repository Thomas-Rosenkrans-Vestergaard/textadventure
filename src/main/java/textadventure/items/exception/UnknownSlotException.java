package textadventure.items.exception;

import textadventure.items.Inventory;

public class UnknownSlotException extends InventoryException
{
	public UnknownSlotException(Inventory inventory)
	{
		super(inventory);
	}
}
