package textadventure.items;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BaseInventory implements Inventory
{

	/**
	 * The {@link Item}s in the {@link Inventory}.
	 */
	private HashMap<Integer, Stack<Item>> items;

	/**
	 * The types of {@link Item}s in the {@link Inventory}.
	 */
	private HashMap<Integer, Integer> types;

	private int numberOfSlots;
	private int numberOfItems;

	public BaseInventory(int numberOfSlots)
	{
		this.items = new HashMap<>(numberOfSlots);
		this.types = new HashMap<>(numberOfSlots);
		this.numberOfSlots = numberOfSlots;
		this.numberOfItems = 0;
		populateMaps();
	}

	/**
	 * Returns the available in the {@link textadventure.ui.Select}.
	 *
	 * @return The available in the {@link textadventure.ui.Select}.
	 */
	@Override public ImmutableMap<Integer, Item> getOptions()
	{
		return getSlots();
	}

	@Override public ImmutableMap<Integer, Item> getSlots()
	{
		ImmutableMap.Builder<Integer, Item> builder = new ImmutableMap.Builder<>();
		for (Map.Entry<Integer, Stack<Item>> entry : this.items.entrySet()) {
			if (entry.getValue().size() > 0)
				builder.put(entry.getKey(), entry.getValue().peek());
		}

		return builder.build();
	}

	private void populateMaps()
	{
		for (int x = 0; x < numberOfSlots; x++) {
			if (!items.containsKey(x)) items.put(x, new Stack<>());
			if (!types.containsKey(x)) types.put(x, null);
		}
	}

	private void validateSlot(int slot) throws UnknownItemSlotException
	{
		if (slot < 0)
			throw new UnknownItemSlotException(this, slot);

		if (slot >= numberOfSlots)
			throw new UnknownItemSlotException(this, slot);
	}

	@Override public void addItem(Item item) throws InventoryFullException
	{
		Integer itemType = item.getType();
		for (int x = 0; x < numberOfSlots; x++) {
			Integer slotType = types.get(x);
			if (slotType != null && slotType.equals(itemType)) {
				items.get(x).push(item);
				numberOfItems++;
				return;
			}
		}

		for (int x = 0; x < numberOfSlots; x++) {
			Integer slotType = types.get(x);
			if (slotType == null) {
				types.put(x, item.getType());
				items.get(x).push(item);
				numberOfItems++;
				return;
			}
		}

		throw new InventoryFullException(this);
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

	@Override public Item takeItem(int slot) throws UnknownItemSlotException, NotEnoughItemsException
	{
		return takeItem(slot, 1).pop();
	}

	@Override public Stack<Item> takeItem(int slot, int amount) throws UnknownItemSlotException, NotEnoughItemsException
	{
		validateSlot(slot);
		Stack<Item> result = new Stack<>();
		Stack<Item> stack  = items.get(slot);
		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, 0, amount, stack.size());

		int moved = 0;
		while (moved < amount) {
			result.push(stack.pop());
			numberOfItems--;
			moved++;
		}

		return result;
	}

	@Override public int getNumberOfItems()
	{
		return numberOfItems;
	}

	@Override public int getNumberOfItems(int slot) throws UnknownItemSlotException
	{
		validateSlot(slot);
		return items.get(slot).size();
	}

	@Override public int getNumberOfSlots()
	{
		return numberOfSlots;
	}

	@Override public void expand(int slots)
	{
		this.numberOfSlots += slots;
		populateMaps();
	}
}
