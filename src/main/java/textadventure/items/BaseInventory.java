package textadventure.items;

import java.util.HashMap;
import java.util.Stack;

public class BaseInventory implements Inventory
{

	private HashMap<Integer, Stack<Item>> items;
	private HashMap<Integer, Integer>     types;
	private int                           numberOfSlots;
	private int                           numberOfItems;

	public BaseInventory(int numberOfSlots)
	{
		this.items = new HashMap<>(numberOfSlots);
		this.types = new HashMap<>(numberOfSlots);
		this.numberOfSlots = numberOfSlots;
		this.numberOfItems = 0;
	}

	private void validateSlot(int slot) throws UnknownItemSlotException
	{
		if (slot < 0)
			throw new UnknownItemSlotException(this);

		if (slot >= numberOfSlots)
			throw new UnknownItemSlotException(this);
	}

	@Override public void addItem(Item item) throws InventoryFullException
	{
		Integer itemType = item.getType();
		for (int x = 0; x < numberOfSlots; x++) {
			Integer slotType = types.get(x);
			if (slotType == itemType) {
				items.get(x).push(item);
				numberOfItems++;
				return;
			}
		}

		for (int x = 0; x < numberOfSlots; x++) {

		}
	}

	@Override
	public void addItem(Item item, int slot) throws UnknownItemSlotException, InventoryTypeMismatchException, ItemSlotFullException
	{
		validateSlot(slot);
		Stack<Item> stack    = items.get(slot);
		int         size     = stack.size();
		Integer     slotType = types.get(slot);
		int         itemType = item.getType();

		if (slotType == null) {
			types.put(slot, itemType);
			stack.push(item);
			numberOfItems++;
			return;
		}

		if (slotType != itemType) {
			throw new InventoryTypeMismatchException(this, slot, slotType, itemType);
		}

		if (size == numberOfSlots) {
			throw new ItemSlotFullException(this, numberOfSlots);
		}

		stack.push(item);
		this.numberOfItems++;
	}

	@Override public void takeItem(Item item) throws NotEnoughItemsException
	{

	}

	@Override public void takeItem(int slot, int amount) throws UnknownItemSlotException, NotEnoughItemsException
	{

	}

	@Override public int getNumberOfItems()
	{
		return 0;
	}

	@Override public int getNumberOfItems(int slot) throws UnknownItemSlotException
	{
		return 0;
	}

	@Override public int getNumberOfSlots()
	{
		return 0;
	}

	@Override public int getNumberOfEmptySlots()
	{
		return 0;
	}

	@Override public int getNumberOfNonEmptySlots()
	{
		return 0;
	}

	@Override public int isEmpty()
	{
		return 0;
	}

	@Override public int isEmpty(int slot) throws UnknownItemSlotException
	{
		return 0;
	}

	@Override public int isFull()
	{
		return 0;
	}

	@Override public int isFull(int slot) throws UnknownItemSlotException
	{
		return 0;
	}

	@Override public void expand(int slots)
	{
		this.numberOfSlots += slots;
	}
}
