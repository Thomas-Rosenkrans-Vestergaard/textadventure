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
	 * Adds the provided {@link Item} to the first available {@link Inventory} position.
	 *
	 * @param item The {@link Item} to put to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Item} could not be added.
	 */
	void addItem(Item item) throws InventoryFullException;

	/**
	 * Adds the provided {@link Stack} of {@link Item}s to the {@link Inventory}.
	 *
	 * @throws InventoryFullException When the {@link Stack} cannot be contained in the {@link Inventory}.
	 */
	void addItems(Stack<Item> items) throws InventoryFullException;

	/**
	 * Adds the provided {@link Item} to the position at the provided position.
	 *
	 * @param item     The {@link Item} to add to the {@link Inventory}.
	 * @param position The position of the position where the {@link Item} should be added.
	 * @throws PositionRangeException When the provided position is outside the permitted range.
	 * @throws TypeMismatchException  When the types of {@link Item} doesn't match.
	 */
	void addItem(Item item, int position) throws PositionRangeException, TypeMismatchException;

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position. The {@link Item} is removed from
	 * the position afterwards.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position is outside the permitted range.
	 * @throws EmptyPositionException When the provided position is empty.
	 */
	Item takeItem(int position) throws PositionRangeException, EmptyPositionException;

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position. The {@link Item} is removed from
	 * the position afterwards. The {@link Item} at the position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position is outside the permitted range.
	 * @throws EmptyPositionException When the provided position is empty.
	 * @throws TypeMismatchException  When the types of {@link Item} doesn't match.
	 */
	<T extends Item> T takeItem(int position, Class<T> type) throws PositionRangeException, EmptyPositionException, TypeMismatchException;

	/**
	 * Returns one or more {@link Item}s from the position at the provided position. The {@link Item}s are removed from the position
	 * afterwards.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param amount   The amount of {@link Item}s to take from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws PositionRangeException  When the provided position is outside the permitted range.
	 * @throws EmptyPositionException  When the provided position is empty.
	 * @throws NotEnoughItemsException When the position doesn't contain enough {@link Item}s to serve the request.
	 */
	Stack<Item> takeItems(int position, int amount) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException;

	/**
	 * Returns one or more {@link Item}s from the position at the provided position. The {@link Item}s are removed from the position
	 * afterwards. The {@link Item}s at the position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param amount   The amount of {@link Item}s to take from the {@link Inventory}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws PositionRangeException  When the provided position is outside the permitted range.
	 * @throws EmptyPositionException  When the provided position is empty.
	 * @throws NotEnoughItemsException When the position doesn't contain enough {@link Item}s to serve the request.
	 * @throws TypeMismatchException   When the types of {@link Item} doesn't match.
	 */
	<T extends Item> Stack<T> takeItems(int position, int amount, Class<T> type) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException, TypeMismatchException;

	/**
	 * Takes all the {@link Item}s from the provided position.
	 *
	 * @param position The position from which to take all items.
	 * @return The {@link Stack} of the {@link Item}s from the provided position.
	 * @throws PositionRangeException When the provided position is outside the permitted range.
	 */
	Stack<Item> takeItems(int position) throws PositionRangeException;

	/**
	 * Takes all the {@link Item} from the {@link Inventory}.
	 *
	 * @return The {@link Stack} of all the {@link Item}s.
	 */
	Stack<Item> takeItems();

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position is outside the permitted range.
	 * @throws EmptyPositionException When the provided position is empty.
	 */
	Item getItem(int position) throws PositionRangeException, EmptyPositionException;

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position. The {@link Item} at the
	 * position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position is outside the permitted range.
	 * @throws EmptyPositionException When the provided position is empty.
	 * @throws TypeMismatchException  When the types of {@link Item} doesn't match.
	 */
	<T extends Item> T getItem(int position, Class<T> type) throws PositionRangeException, EmptyPositionException, TypeMismatchException;

	/**
	 * Returns one or more {@link Item}s from the provided position position.
	 *
	 * @param position The position of the position from where to get the last {@link Item}.
	 * @param amount   The amount of {@link Item}s to get from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws PositionRangeException  When the provided position is outside the legal range.
	 * @throws EmptyPositionException  When the provided position is empty.
	 * @throws NotEnoughItemsException When the position doesn't contain enough {@link Item}s to serve a request.
	 */
	Stack<Item> getItems(int position, int amount) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException;

	/**
	 * Returns one or more {@link Item}s from the provided position position. The {@link Item}s at the
	 * position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to get the last {@link Item}.
	 * @param amount   The amount of {@link Item}s to get from the {@link Inventory}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws PositionRangeException  When the provided position is outside the legal range.
	 * @throws EmptyPositionException  When the provided position is empty.
	 * @throws NotEnoughItemsException When the position doesn't contain enough {@link Item}s to serve a request.
	 * @throws TypeMismatchException   When the types of {@link Item} doesn't match.
	 */
	<T extends Item> Stack<T> getItems(int position, int amount, Class<T> type) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException, TypeMismatchException;

	/**
	 * Returns the number of {@link Item}s contained in the {@link Inventory}.
	 *
	 * @return The number of {@link Item}s contained in the {@link Inventory}.
	 */
	int getNumberOfItems();

	/**
	 * Returns the number of {@link Item}s contained in the position at the provided position.
	 *
	 * @param position The position of the position from where to return the number of {@link Item}s.
	 * @return The number of {@link Item}s contained in the position with the provided position.
	 * @throws PositionRangeException When the provided position position is out of the legal range.
	 */
	int getNumberOfItems(int position) throws PositionRangeException;

	/**
	 * Returns an {@link ImmutableMap} of the positions in the {@link Inventory}. The map entries is the amount of
	 * {@link Item}s in the position mapped to the position of the position in the {@link Inventory}. Only non-empty
	 * positions are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	ImmutableMap<Integer, Integer> getPositionAmounts();

	/**
	 * Returns the maximum number of positions in the {@link Inventory}.
	 *
	 * @return The maximum number of positions in the {@link Inventory}.
	 */
	int getNumberOfPositions();

	/**
	 * Returns the number of empty positions in the {@link Inventory}.
	 *
	 * @return The number of empty positions in the {@link Inventory}.
	 */
	int getNumberOfEmptyPositions();

	/**
	 * Returns the number of non-empty positions in the {@link Inventory}.
	 *
	 * @return The number of non-empty positions in the {@link Inventory}.
	 */
	int getNumberOfNonEmptyPositions();

	/**
	 * Returns an {@link ImmutableMap} of the positions in the {@link Inventory}. The map entries is information about
	 * the {@link Item} in the position mapped to the position of the position in the {@link Inventory}. Only non-empty positions are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	ImmutableMap<Integer, ItemType> getPositions();

	/**
	 * Expands the number of positions in the {@link Inventory} with the provided number of positions.
	 *
	 * @param positions The number of positions to expand the {@link Inventory} with.
	 * @return Whether or not the number of positions in the {@link Inventory} could be expanded.
	 */
	boolean expand(int positions);

	/**
	 * Returns the positions in the {@link Inventory} as {@link Option}s.
	 *
	 * @return The {@link ImmutableSet} of {@link Option}s.
	 */
	ImmutableSet<Option<Item>> asOptions();

	/**
	 * Returns the positions in the {@link Inventory} with the provided {@link Class} type.
	 *
	 * @param type The type of the {@link Item} to return.
	 * @param <T>  The type.
	 * @return The positions in the {@link Inventory} with the provided {@link Class} type.
	 */
	<T extends Item> ImmutableSet<Option<T>> asOptions(Class<T> type);
}
