package textadventure.items;

import java.util.Stack;

public class InventorySlot
{
	private final Inventory             inventory;
	private       Class<? extends Item> type;
	private       int                   maxItemCount;
	private       int                   itemCount;
	private       Stack<Item>           items;

	public InventorySlot(Inventory inventory)
	{
		this.inventory = inventory;
	}

	public void addItem(Item item) throws ItemStackFullException, InventoryTypeMismatchException
	{
		if (type == null) {
			type = item.getClass();
			maxItemCount = item.getStackSize();
		}

		if (itemCount == maxItemCount) {
			throw new ItemStackFullException(inventory, this, itemCount);
		}

		if (type.equals(item.getClass())) {
			throw new InventoryTypeMismatchException(inventory, this, type, item.getClass());
		}

		this.items.push(item);
		this.itemCount++;
	}

	public <T extends Item> Stack<T> takeItem(Class<T> type) throws
			InventoryTypeMismatchException,
			NotEnoughItemsException
	{
		return this.takeItem(type, 1);
	}

	public <T extends Item> Stack<T> takeItem(Class<T> type, int amount) throws
			InventoryTypeMismatchException,
			NotEnoughItemsException
	{
		if (!this.type.equals(type))
			throw new InventoryTypeMismatchException(inventory, this, this.type, type);

		if (amount > maxItemCount)
			throw new IllegalArgumentException("Requested amount greater than maxItemCount.");

		if (itemCount < amount)
			throw new NotEnoughItemsException(inventory, type, amount, itemCount);

		Stack<T> result = new Stack<>();
		for (int x = 0; x < amount; x++)
			result.push(type.cast(this.items.pop()));

		return result;
	}

	public Class<? extends Item> getType()
	{
		return this.type;
	}

	public int getMaxItemCount()
	{
		return this.maxItemCount;
	}

	public int getItemCount()
	{
		return this.itemCount;
	}

	public boolean isEmpty()
	{
		return this.itemCount == 0;
	}

	public boolean isFull()
	{
		return this.itemCount == maxItemCount;
	}
}
