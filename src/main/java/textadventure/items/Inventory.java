package textadventure.items;

import textadventure.Property;
import textadventure.ui.Select;

public interface Inventory extends Select, Property
{

	/**
	 * Returns the {@link Item} in the provided slot.
	 *
	 * @param slot The identifier of the slot to insert.
	 *
	 * @return The {@link Item} in the provided slot.
	 */
	Item getItem(int slot);

	/**
	 * Adds a new {@link Item} to the {@link Inventory}.
	 *
	 * @param slot The slot the add the {@link Item} to.
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 */
	void addItem(int slot, Item item);

	/**
	 * Removes the {@link Item} in the provided inventory slot.
	 *
	 * @param slot The inventory slot identifier.
	 *
	 * @return The {@link Item} in the provided inventory slot.
	 */
	void removeItem(int slot);

	/**
	 * Returns <code>true</code> when an {@link Item} exists in the provided inventory slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided inventory slot.
	 *
	 * @param slot The inventory slot to check for.
	 *
	 * @return <code>True</code> when an {@link Item} exists in the provided inventory slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided inventory slot.
	 */
	boolean hasItem(int slot);

	/**
	 * Returns the number of slots in the {@link Inventory}.
	 *
	 * @return The number of slots in the {@link Inventory}.
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
