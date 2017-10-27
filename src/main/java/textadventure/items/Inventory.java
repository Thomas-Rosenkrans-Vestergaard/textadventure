package textadventure.items;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import textadventure.ui.Option;

import java.util.Stack;

/**
 * Represents some type containing {@link Item}(s).
 */
public interface Inventory
{

	/**
	 * Returns an {@link ImmutableMap} of the slots in the {@link Inventory}. The map entries is information about
	 * the {@link Item} in the slot mapped to the position of the slot in the {@link Inventory}. Only non-empty slots
	 * are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	ImmutableMap<Integer, ItemType> getSlots();

	/**
	 * Returns an {@link ImmutableMap} of the slots in the {@link Inventory}. The map entries is the amount of
	 * {@link Item}s in the slot mapped to the position of the slot in the {@link Inventory}. Only non-empty slots
	 * are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	ImmutableMap<Integer, Integer> getSlotAmounts();

	/**
	 * Adds the provided {@link Item} to the first available {@link Inventory} slot.
	 *
	 * @param item The {@link Item} to put to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Item} could not be added.
	 */
	void addItem(Item item) throws InventoryFullException;

	/**
	 * Adds the provided {@link Item} to the slot at the provided position.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @param slot The position of the slot where the {@link Item} should be added.
	 * @throws SlotOutOfRangeException        When the provided slot doesn't exist.
	 * @throws InventoryTypeMismatchException When the type of the {@link Item} to insert doesn't match the type of
	 *                                        the slot where the {@link Item} should be added.
	 */
	void addItem(Item item, int slot) throws SlotOutOfRangeException, InventoryTypeMismatchException;

	/**
	 * Returns the last inserted {@link Item} from the slot at the provided position. The {@link Item} is removed from
	 * the slot afterwards.
	 *
	 * @param slot The position of the slot from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws SlotOutOfRangeException When the provided slot doesn't exist.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	Item takeItem(int slot) throws SlotOutOfRangeException, NotEnoughItemsException;

	/**
	 * Returns one or more {@link Item}s from the slot at the provided position. The {@link Item}s are removed from the slot
	 * afterwards.
	 *
	 * @param slot   The position of the slot from where to take the last {@link Item}.
	 * @param amount The amount of {@link Item}s to take from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws SlotOutOfRangeException When the provided slot doesn't exist.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	Stack<Item> takeItem(int slot, int amount) throws SlotOutOfRangeException, NotEnoughItemsException;

	/**
	 * Returns the last inserted {@link Item} from the slot at the provided position.
	 *
	 * @param slot The position of the slot from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws SlotOutOfRangeException When the provided slot doesn't exist.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	Item getItem(int slot) throws SlotOutOfRangeException, NotEnoughItemsException;

	/**
	 * Returns one or more {@link Item}s from the provided slot position.
	 *
	 * @param slot   The position of the slot from where to get the last {@link Item}.
	 * @param amount The amount of {@link Item}s to get from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws SlotOutOfRangeException When the provided slot doesn't exist.
	 * @throws NotEnoughItemsException When the slot doesn't contain enough {@link Item}s to serve the request.
	 */
	Stack<Item> getItem(int slot, int amount) throws SlotOutOfRangeException, NotEnoughItemsException;

	/**
	 * Returns the number of {@link Item}s contained in the {@link Inventory}.
	 *
	 * @return The number of {@link Item}s contained in the {@link Inventory}.
	 */
	int getNumberOfItems();

	/**
	 * Returns the number of {@link Item}s contained in the slot at the provided position.
	 *
	 * @param slot The position of the slot from where to return the number of {@link Item}s.
	 * @return The number of {@link Item}s contained in the slot with the provided position.
	 * @throws SlotOutOfRangeException When the provided slot position is out of the legal range.
	 */
	int getNumberOfItems(int slot) throws SlotOutOfRangeException;

	/**
	 * Returns the maximum number of slots in the {@link Inventory}.
	 *
	 * @return the maximum number of slots in the {@link Inventory}.
	 */
	int getNumberOfSlots();

	/**
	 * Returns the number of empty slots in the {@link Inventory}.
	 *
	 * @return The number of empty slots in the {@link Inventory}.
	 */
	int getNumberOfEmptySlots();

	/**
	 * Returns the number of non-empty slots in the {@link Inventory}.
	 *
	 * @return The number of non-empty slots in the {@link Inventory}.
	 */
	int getNumberOfNonEmptySlots();

	/**
	 * Expands the number of slots in the {@link Inventory} with the provided number of slots.
	 *
	 * @param slots The number of slots to expand the {@link Inventory} with.
	 * @return Whether or not the number of slots in the {@link Inventory} could be expanded.
	 */
	boolean expand(int slots);

	/**
	 * Returns the slots in the {@link Inventory} as {@link Option}s.
	 *
	 * @return The {@link ImmutableSet} of {@link Option}s.
	 */
	ImmutableSet<Option> asOptions();

	/**
	 * Returns the slots in the {@link Inventory} with the provided {@link Class} type.
	 *
	 * @param type The type of the {@link Item} to return.
	 * @param <T>  The type.
	 * @return The slots in the {@link Inventory} with the provided {@link Class} type.
	 */
	/*<T extends Option> ImmutableSet<T> asOptions(Class<T> type);*/
}
