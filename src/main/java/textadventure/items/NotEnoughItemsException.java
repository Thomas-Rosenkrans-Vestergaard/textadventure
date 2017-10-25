package textadventure.items;

public class NotEnoughItemsException extends InventoryException
{
	private Class<? extends Item> type;
	private int requested;
	private int actual;

	public NotEnoughItemsException(Inventory inventory, Class<? extends Item> type, int requested, int actual)
	{
		super(inventory);
		this.type = type;
		this.requested = requested;
		this.actual = actual;
	}

	public Class<? extends Item> getType()
	{
		return this.type;
	}

	public int getRequested()
	{
		return this.requested;
	}

	public int getActual()
	{
		return this.actual;
	}
}
