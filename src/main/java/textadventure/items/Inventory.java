package textadventure.items;

import com.google.common.collect.ImmutableMap;

public interface Inventory
{

	/**
	 * Adds the provided {@link Item} to the {@link Inventory}. The {@link Item} is added to the first available stack.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @throws InventoryFullException When the number of slots in the {@link Inventory} prevents the {@link Item}
	 *                                from being added to the {@link Inventory}.
	 */
	void addItem(Item item) throws InventoryFullException;

	/**
	 * Returns the {@link InventorySlot} at the given position. The positions range from <code>0</code> to the number of
	 * slots - 1.
	 *
	 * @param position The position of the {@link InventorySlot} to retrieve.
	 * @return The {@link InventorySlot} at the provided position.
	 * @throws UnknownItemSlotException When the given position is invalid.
	 */
	InventorySlot getSlot(int position) throws UnknownItemSlotException;

	/**
	 * Returns a collection of the {@link InventorySlot}s in the {@link Inventory}.
	 *
	 * @return The collections.
	 */
	ImmutableMap<Integer, InventorySlot> getSlots();

	boolean isEmpty();

	/**
	 * Returns the number of {@link Item}s in the {@link Inventory}.
	 *
	 * @return The number of {@link Item}s in the {@link Inventory}.
	 */
	int getNumberOfItems();

	/**
	 * Counts the number of {@link Item}s of the provided <code>type</code>.
	 *
	 * @param type The <code>type</code> of {@link Item} to count.
	 * @return The number of {@link Item}s of the provided <code>type</code>.
	 */
	int getNumberOfItems(Class<? extends Item> type);

	/**
	 * Returns the number of slots in the {@link Inventory}.
	 *
	 * @return The number of slots in the {@link Inventory}.
	 */
	int getNumberOfSlots();

	/**
	 * Expands the number of slots in the {@link Inventory}.
	 *
	 * @param slots The number of slots to expand the {@link Inventory} with.
	 */
	void expand(int slots);
}
