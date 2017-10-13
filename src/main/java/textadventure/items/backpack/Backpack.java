package textadventure.items.backpack;

import textadventure.Property;
import textadventure.items.Item;
import textadventure.ui.Select;

public interface Backpack extends Select<Item>, Property
{

	/**
	 * Returns the {@link Item} in the provided slot.
	 *
	 * @param slot The identifier of the slot to insert.
	 *
	 * @return The {@link Item} in the provided slot. Returns <code>null</code> if the {@link Item} doesn't exist.
	 */
	Item getItem(int slot);

	/**
	 * Adds a new {@link Item} to the {@link Backpack}.
	 *
	 * @param slot The slot the add the {@link Item} to.
	 * @param item The {@link Item} to add to the {@link Backpack}.
	 */
	void addItem(int slot, Item item);

	/**
	 * Removes the {@link Item} in the provided backpack slot.
	 *
	 * @param slot The backpack slot identifier.
	 */
	void removeItem(int slot);

	/**
	 * Returns <code>true</code> when an {@link Item} exists in the provided backpack slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided backpack slot.
	 *
	 * @param slot The backpack slot to check for.
	 *
	 * @return <code>True</code> when an {@link Item} exists in the provided backpack slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided backpack slot.
	 */
	boolean hasItem(int slot);

	/**
	 * Returns the number of slots in the {@link Backpack}.
	 *
	 * @return The number of slots in the {@link Backpack}.
	 */
	int countSlots();

	/**
	 * Returns the number of non-empty slots.
	 *
	 * @return The number of non-empty slots.
	 */
	int countNonEmptySlots();

	/**
	 * Returns the number of empty slots.
	 *
	 * @return The number of empty slots.
	 */
	int countEmptySlots();
}
