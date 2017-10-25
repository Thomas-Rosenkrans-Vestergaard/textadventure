package textadventure.items;

import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.stream.Stream;

public class BaseInventory implements Inventory
{

	/**
	 * The {@link InventorySlot}s in the {@link Inventory}.
	 */
	private HashMap<Integer, InventorySlot> slots = new HashMap<>();
	private int numberOfSlots;

	/**
	 * Creates a new {@link BaseInventory}.
	 *
	 * @param slots The maximum number of slots in the {@link Inventory}.
	 */
	public BaseInventory(int slots)
	{
		if (slots < 1) {
			throw new IllegalArgumentException("Number of slots in the inventory must be positive.");
		}

		this.numberOfSlots = slots;
		populateSlots();
	}

	private void populateSlots()
	{
		for (int x = 0; x < numberOfSlots; x++)
			if (!this.slots.containsKey(x))
				this.slots.put(x, new InventorySlot(this));
	}

	/**
	 * Adds the provided {@link Item} to the {@link Inventory}. The {@link Item} is added to the first available stack.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @throws InventoryFullException When the number of slots in the {@link Inventory} prevents the {@link Item}
	 *                                from being added to the {@link Inventory}.
	 */
	@Override public void addItem(Item item) throws InventoryFullException
	{

	}

	/**
	 * Returns the {@link InventorySlot} at the given position. The positions range from <code>0</code> to the number of
	 * slots - 1.
	 *
	 * @param position The position of the {@link InventorySlot} to retrieve.
	 * @return The {@link InventorySlot} at the provided position.
	 * @throws UnknownItemSlotException When the given position is invalid.
	 */
	@Override public InventorySlot getSlot(int position) throws UnknownItemSlotException
	{
		if (!slots.containsKey(position))
			throw new UnknownItemSlotException(this);

		return slots.get(position);
	}

	/**
	 * Returns a collection of the {@link InventorySlot}s in the {@link Inventory}.
	 *
	 * @return The collections.
	 */
	@Override public ImmutableMap<Integer, InventorySlot> getSlots()
	{
		return new ImmutableMap.Builder<Integer, InventorySlot>().putAll(slots).build();
	}

	@Override public boolean isEmpty()
	{
		for (InventorySlot inventorySlot : slots.values()) {
			if (!inventorySlot.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the number of {@link Item}s in the {@link Inventory}.
	 *
	 * @return The number of {@link Item}s in the {@link Inventory}.
	 */
	@Override public int getNumberOfItems()
	{
		return 0;
	}

	/**
	 * Counts the number of {@link Item}s of the provided <code>type</code>.
	 *
	 * @param type The <code>type</code> of {@link Item} to count.
	 * @return The number of {@link Item}s of the provided <code>type</code>.
	 */
	@Override public int getNumberOfItems(Class<? extends Item> type)
	{
		return 0;
	}

	/**
	 * Returns the number of slots in the {@link Inventory}.
	 *
	 * @return The number of slots in the {@link Inventory}.
	 */
	@Override public int getNumberOfSlots()
	{
		return 0;
	}

	/**
	 * Expands the number of slots in the {@link Inventory}.
	 *
	 * @param slots The number of slots to expand the {@link Inventory} with.
	 */
	@Override public void expand(int slots)
	{
		if (slots < 1)
			throw new IllegalArgumentException("Number of slots to expand with must be positive.");

		this.numberOfSlots += slots;
		populateSlots();
	}
}
