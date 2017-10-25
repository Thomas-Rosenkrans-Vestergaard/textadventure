package textadventure.items;

public class NotEnoughItemsException extends InventoryException
{
	private int type;
	private int requested;
	private int actual;

	public NotEnoughItemsException(Inventory inventory, int type, int requested, int actual)
	{
		super(inventory);
		this.type = type;
		this.requested = requested;
		this.actual = actual;
	}

	public int getType()
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
