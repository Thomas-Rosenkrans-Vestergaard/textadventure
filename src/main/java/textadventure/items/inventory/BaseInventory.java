package textadventure.items.inventory;

import com.google.common.collect.ImmutableMap;
import textadventure.AbstractProperty;
import textadventure.items.Item;

public class BaseInventory extends AbstractProperty implements Inventory
{

	/**
	 * The items in the {@link Inventory}.
	 */
	private Item[] items;

	/**
	 * The number of items in the {@link Inventory}.
	 */
	private int countSlots;

	/**
	 * The number of filled items in the {@link Inventory}.
	 */
	private int countFilled;

	/**
	 * The number of empty items in the {@link Inventory}.
	 */
	private int countEmpty;

	/**
	 * Creates a new {@link BaseInventory}.
	 *
	 * @param countSlots The number of items in the {@link Inventory}.
	 */
	public BaseInventory(int countSlots)
	{
		this.items = new Item[countSlots];
		this.countSlots = countSlots;
		this.countFilled = 0;
		this.countEmpty = countSlots;

		addAction(new InventoryInspectAction());
	}

	/**
	 * Returns the {@link Item} in the provided slot.
	 *
	 * @param slot The identifier of the slot to insert.
	 *
	 * @return The {@link Item} in the provided slot.
	 */
	@Override public Item getItem(int slot)
	{
		if (slot < 0 || slot > countSlots)
			throw new IllegalArgumentException("Slot out of range.");

		return items[slot];
	}

	/**
	 * Returns <code>true</code> when an {@link Item} exists in the provided inventory slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided inventory slot.
	 *
	 * @param slot The inventory slot to check for.
	 *
	 * @return <code>True</code> when an {@link Item} exists in the provided inventory slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided inventory slot.
	 */
	@Override public boolean hasItem(int slot)
	{
		return items[slot] != null;
	}

	/**
	 * Adds a new {@link Item} to the {@link Inventory}.
	 *
	 * @param slot The slot the add the {@link Item} to.
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 */
	@Override public void addItem(int slot, Item item)
	{
		this.items[slot] = item;
	}

	/**
	 * Removes the {@link Item} in the provided inventory slot.
	 *
	 * @param slot The inventory slot identifier.
	 */
	@Override public void removeItem(int slot)
	{
		this.items[slot] = null;
	}

	/**
	 * Returns the number of items in the {@link Inventory}.
	 *
	 * @return The number of items in the {@link Inventory}.
	 */
	@Override public int countSlots()
	{
		return countSlots;
	}

	/**
	 * Returns the number of non-empty items.
	 *
	 * @return The number of non-empty items.
	 */
	@Override public int countNonEmptySlots()
	{
		return countFilled;
	}

	/**
	 * Returns the number of empty items.
	 *
	 * @return The number of empty items.
	 */
	@Override public int countEmptySlots()
	{
		return countEmpty;
	}

	/**
	 * Returns the {@link Item}s available in the {@link Inventory}.
	 *
	 * @return The {@link Item}s available in the {@link Inventory}.
	 */
	@Override public ImmutableMap<String, Item> getOptions()
	{
		ImmutableMap.Builder<String, Item> options = new ImmutableMap.Builder<>();
		for (Item item : items)
			if (item != null)
				options.put(item.getItemName(), item);

		return options.build();
	}
}
