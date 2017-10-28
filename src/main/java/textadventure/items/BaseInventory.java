package textadventure.items;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import textadventure.ui.BaseOption;
import textadventure.ui.Option;

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
	 * The {@link ItemType} of a slot mapped to its position in the {@link Inventory}.
	 */
	private HashMap<Integer, ItemType> types;

	/**
	 * The number of slots the {@link Inventory} can contain.
	 */
	private int numberOfSlots;

	/**
	 * The number of empty slots the {@link Inventory} has.
	 */
	private int numberOfEmptySlots;

	/**
	 * The number of {@link Item}s contained in the {@link Inventory}.
	 */
	private int numberOfItems;

	/**
	 * Creates a new {@link BaseInventory} using <code>Integer.MAX_VALUE</code> as the number of slots the
	 * {@link Inventory} can contain.
	 */
	public BaseInventory()
	{
		this(Integer.MAX_VALUE);
	}

	/**
	 * Creates a new {@link BaseInventory}.
	 *
	 * @param numberOfSlots The number of slots the {@link Inventory} can contain.
	 */
	public BaseInventory(int numberOfSlots)
	{
		this.items = new HashMap<>(numberOfSlots);
		this.types = new HashMap<>(numberOfSlots);
		this.numberOfSlots = numberOfSlots;
		this.numberOfEmptySlots = numberOfSlots;
		this.numberOfEmptySlots = numberOfSlots;
		this.numberOfItems = 0;
	}

	/**
	 * Returns an {@link ImmutableMap} of the slots in the {@link Inventory}. The map entries is information about
	 * the {@link Item} in the slot mapped to the position of the slot in the {@link Inventory}. Only non-empty slots
	 * are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	@Override public ImmutableMap<Integer, ItemType> getSlots()
	{
		ImmutableMap.Builder<Integer, ItemType> builder = new ImmutableMap.Builder<>();
		for (Map.Entry<Integer, Stack<Item>> entry : items.entrySet()) {
			builder.put(entry.getKey(), entry.getValue().peek());
		}

		return builder.build();
	}

	/**
	 * Returns an {@link ImmutableMap} of the slots in the {@link Inventory}. The map entries is the amount of
	 * {@link Item}s in the slot mapped to the position of the slot in the {@link Inventory}. Only non-empty slots
	 * are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	@Override public ImmutableMap<Integer, Integer> getSlotAmounts()
	{
		ImmutableMap.Builder<Integer, Integer> builder = new ImmutableMap.Builder<>();
		for (Map.Entry<Integer, Stack<Item>> entry : items.entrySet()) {
			builder.put(entry.getKey(), entry.getValue().size());
		}

		return builder.build();
	}

	/**
	 * Adds the provided {@link Item} to the first available {@link Inventory} slot.
	 *
	 * @param item The {@link Item} to put to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Item} could not be added.
	 */
	@Override public void addItem(Item item) throws InventoryFullException
	{
		for (Map.Entry<Integer, ItemType> entry : types.entrySet()) {
			int      index    = entry.getKey();
			ItemType slotType = entry.getValue();
			if (slotType.instanceOf(item)) {
				items.get(index).push(item);
				numberOfItems++;
				return;
			}
		}

		for (int index = 0; index < numberOfSlots; index++) {
			if (!types.containsKey(index)) {
				types.put(index, item);
				Stack<Item> stack = new Stack<>();
				stack.push(item);
				items.put(index, stack);
				numberOfItems++;
				numberOfEmptySlots--;
				return;
			}
		}

		throw new InventoryFullException(this, item);
	}

	/**
	 * Adds the provided {@link Item} to the slot at the provided position.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @param slot The position of the slot where the {@link Item} should be added.
	 * @throws SlotOutOfRangeException        When the provided slot position is out of the legal range.
	 * @throws InventoryTypeMismatchException When the type of the {@link Item} to insert doesn't match the type of
	 *                                        the slot where the {@link Item} should be added.
	 */
	@Override public void addItem(Item item, int slot) throws SlotOutOfRangeException, InventoryTypeMismatchException
	{
		validateSlot(slot);
		ItemType slotType = types.get(slot);

		if (slotType == null) {
			types.put(slot, item);
			Stack<Item> stack = new Stack<>();
			stack.push(item);
			items.put(slot, stack);
			numberOfItems++;
			numberOfEmptySlots--;
			return;
		}

		Stack<Item> stack = items.get(slot);
		stack.push(item);
		this.numberOfItems++;
	}


	/**
	 * Returns the last inserted {@link Item} from the slot at the provided position. The {@link Item} is removed from
	 * the slot afterwards.
	 *
	 * @param slot The position of the slot from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws SlotOutOfRangeException When the provided slot position is out of the legal range.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	@Override public Item takeItem(int slot) throws SlotOutOfRangeException, NotEnoughItemsException
	{
		return takeItem(slot, 1).pop();
	}

	/**
	 * Returns one or more {@link Item}s from the slot at the provided position. The {@link Item}s are removed from the slot
	 * afterwards.
	 *
	 * @param slot   The position of the slot from where to take the last {@link Item}.
	 * @param amount The amount of {@link Item}s to take from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws SlotOutOfRangeException When the provided slot position is out of the legal range.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	@Override public Stack<Item> takeItem(int slot, int amount) throws SlotOutOfRangeException, NotEnoughItemsException
	{
		validateSlot(slot);
		Stack<Item> result = new Stack<>();
		Stack<Item> stack  = items.get(slot);
		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, types.get(slot), amount, stack.size());

		int moved = 0;
		while (moved < amount) {
			Item item = stack.pop();
			result.push(item);
			moved++;
			if (stack.size() == 0) {
				items.remove(slot);
				types.remove(slot);
				numberOfEmptySlots++;
			}
		}

		numberOfItems -= moved;

		return result;
	}

	/**
	 * Returns the last inserted {@link Item} from the slot at the provided position.
	 *
	 * @param slot The position of the slot from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws SlotOutOfRangeException When the provided slot position is out of the legal range.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	@Override public Item getItem(int slot) throws SlotOutOfRangeException, NotEnoughItemsException
	{
		return getItem(slot, 1).pop();
	}

	/**
	 * Returns one or more {@link Item}s from the provided slot position.
	 *
	 * @param slot   The position of the slot from where to get the last {@link Item}.
	 * @param amount The amount of {@link Item}s to get from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws SlotOutOfRangeException When the provided slot position is out of the legal range.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	@Override public Stack<Item> getItem(int slot, int amount) throws SlotOutOfRangeException, NotEnoughItemsException
	{
		validateSlot(slot);
		ItemType    slotType = types.get(slot);
		Stack<Item> result   = new Stack<>();
		Stack<Item> stack    = items.get(slot);
		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, slotType, amount, stack.size());

		int moved = 0;
		while (moved < amount) {
			result.push(stack.peek());
			numberOfItems--;
			moved++;
		}

		return result;
	}

	/**
	 * Returns the number of {@link Item}s contained in the {@link Inventory}.
	 *
	 * @return The number of {@link Item}s contained in the {@link Inventory}.
	 */
	@Override public int getNumberOfItems()
	{
		return numberOfItems;
	}

	/**
	 * Returns the number of {@link Item}s contained in the slot at the provided position.
	 *
	 * @param slot The position of the slot from where to return the number of {@link Item}s.
	 * @return The number of {@link Item}s contained in the slot with the provided position.
	 * @throws SlotOutOfRangeException When the provided slot position is out of the legal range.
	 */
	@Override public int getNumberOfItems(int slot) throws SlotOutOfRangeException
	{
		validateSlot(slot);

		Stack<Item> stack = items.get(slot);

		return stack == null ? 0 : stack.size();
	}

	/**
	 * Returns the maximum number of slots in the {@link Inventory}.
	 *
	 * @return The maximum number of slots in the {@link Inventory}.
	 */
	@Override public int getNumberOfSlots()
	{
		return numberOfSlots;
	}

	/**
	 * Returns the number of empty slots in the {@link Inventory}.
	 *
	 * @return The number of empty slots in the {@link Inventory}.
	 */
	@Override public int getNumberOfEmptySlots()
	{
		return numberOfEmptySlots;
	}

	/**
	 * Returns the number of non-empty slots in the {@link Inventory}.
	 *
	 * @return The number of non-empty slots in the {@link Inventory}.
	 */
	@Override public int getNumberOfNonEmptySlots()
	{
		return numberOfSlots - numberOfEmptySlots;
	}

	/**
	 * Expands the number of slots in the {@link Inventory} with the provided number of slots.
	 *
	 * @param slots The number of slots to expand the {@link Inventory} with.
	 * @return Whether or not the number of slots in the {@link Inventory} could be expanded.
	 */
	@Override public boolean expand(int slots)
	{
		if (numberOfSlots + slots < 0)
			return false;

		numberOfSlots += slots;
		return true;
	}

	/**
	 * Returns the slots in the {@link Inventory} as {@link Option}s.
	 *
	 * @return The {@link ImmutableSet} of {@link Option}s.
	 */
	@Override public ImmutableSet<Option<Item>> asOptions()
	{
		ImmutableSet.Builder<Option<Item>> builder = new ImmutableSet.Builder<>();
		items.forEach((position, stack) -> {
			Item item = stack.peek();
			builder.add(new BaseOption<>(position, item.getItemName(), item.getItemDescription(), item));
		});

		return builder.build();
	}

	/**
	 * Returns the slots in the {@link Inventory} with the provided {@link Class} type.
	 *
	 * @param type The type of the {@link Item} to return.
	 * @return The slots in the {@link Inventory} with the provided {@link Class} type.
	 */
	@Override public <T extends Item> ImmutableSet<Option<T>> asOptions(Class<T> type)
	{
		ImmutableSet.Builder<Option<T>> builder = new ImmutableSet.Builder<>();
		items.forEach((position, stack) -> {
			Item item = stack.peek();
			if (type.isInstance(item))
				builder.add(new BaseOption<>(position, item.getItemName(), item.getItemDescription(), type.cast(item)));
		});

		return builder.build();
	}

	/**
	 * Validates that the provided slot position is within a legal range. The valid slot position range is from
	 * <code>0</code> to <code>numberOfSlots-1</code>.
	 *
	 * @param slot The slot position to validate.
	 * @throws SlotOutOfRangeException When the slot position is out of range.
	 */
	private void validateSlot(int slot) throws SlotOutOfRangeException
	{
		if (slot < 0)
			throw new SlotOutOfRangeException(this, slot);

		if (slot >= numberOfSlots)
			throw new SlotOutOfRangeException(this, slot);
	}
}
