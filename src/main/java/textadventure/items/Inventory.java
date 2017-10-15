package textadventure.items;

import com.google.common.collect.ImmutableMap;
import textadventure.ui.Select;

public interface Inventory extends Select<Item>
{

	/**
	 * Returns the {@link Item} in the provided slot.
	 *
	 * @param slot The identifier of the slot to insert.
	 * @return The {@link Item} in the provided slot. Returns <code>null</code> if the {@link Item} doesn't exist.
	 */
	Item getItem(int slot);

	/**
	 * Adds a new {@link Item} to the {@link Inventory}.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 */
	void addItem(Item item);

	/**
	 * Adds a new {@link Item} to the {@link Inventory}.
	 *
	 * @param slot The slot the add the {@link Item} to.
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 */
	void addItem(int slot, Item item);

	/**
	 * Returns <code>true</code> when an {@link Item} exists in the provided inventory slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided inventory slot.
	 *
	 * @param slot The inventory slot to check for.
	 * @return <code>True</code> when an {@link Item} exists in the provided inventory slot. Returns
	 * <code>false</code> when no {@link Item} was found in the provided inventory slot.
	 */
	boolean hasItem(int slot);

	/**
	 * Removes the {@link Item} in the provided inventory slot.
	 *
	 * @param slot The inventory slot identifier.
	 */
	void removeItem(int slot);

	/**
	 * Removes the {@link Item} from the {@link Inventory}.
	 *
	 * @param item The {@link Item} to remove.
	 */
	void removeItem(Item item);

	/**
	 * Returns the slot of the provided {@link Item}.
	 *
	 * @param item The {@link Item} to return the slot of.
	 * @return The slot number of the provided {@link Item}. Returns <code>-1</code> if the {@link Item} doesn't exist.
	 */
	int getSlot(Item item);

	/**
	 * Returns an {@link ImmutableMap} of the items in the {@link Inventory}. The {@link ImmutableMap} does not contain
	 * <code>null</code> values.
	 *
	 * @return The {@link ImmutableMap} of the items in the {@link Inventory}. The {@link ImmutableMap} does not contain
	 * <code>null</code> values.
	 */
	ImmutableMap<Integer, Item> getItems();

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
