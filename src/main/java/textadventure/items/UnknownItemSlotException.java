package textadventure.items;

public class UnknownItemSlotException extends InventoryException
{
	public UnknownItemSlotException(Inventory inventory)
	{
		super(inventory);
	}
}
