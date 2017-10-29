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
	 * The {@link ItemType} of a position mapped to its position in the {@link Inventory}.
	 */
	private HashMap<Integer, ItemType> types;

	/**
	 * The number of positions the {@link Inventory} can contain.
	 */
	private int numberOfPositions;

	/**
	 * The number of empty positions the {@link Inventory} has.
	 */
	private int numberOfEmptyPositions;

	/**
	 * The number of {@link Item}s contained in the {@link Inventory}.
	 */
	private int numberOfItems;

	/**
	 * Creates a new {@link BaseInventory} using <code>Integer.MAX_VALUE</code> as the number of positions the
	 * {@link Inventory} can contain.
	 */
	public BaseInventory()
	{
		this(Integer.MAX_VALUE);
	}

	/**
	 * Creates a new {@link BaseInventory}.
	 *
	 * @param numberOfPositions The number of positions the {@link Inventory} can contain.
	 */
	public BaseInventory(int numberOfPositions)
	{
		this.items = new HashMap<>();
		this.types = new HashMap<>();
		this.numberOfPositions = numberOfPositions;
		this.numberOfEmptyPositions = numberOfPositions;
		this.numberOfEmptyPositions = numberOfPositions;
		this.numberOfItems = 0;
	}

	/**
	 * Returns an {@link ImmutableMap} of the positions in the {@link Inventory}. The map entries is information about
	 * the {@link Item} in the position mapped to the position of the position in the {@link Inventory}. Only non-empty positions
	 * are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	@Override public ImmutableMap<Integer, ItemType> getPositions()
	{
		ImmutableMap.Builder<Integer, ItemType> builder = new ImmutableMap.Builder<>();
		for (Map.Entry<Integer, Stack<Item>> entry : items.entrySet()) {
			builder.put(entry.getKey(), entry.getValue().peek());
		}

		return builder.build();
	}

	/**
	 * Returns an {@link ImmutableMap} of the positions in the {@link Inventory}. The map entries is the amount of
	 * {@link Item}s in the position mapped to the position of the position in the {@link Inventory}. Only non-empty positions
	 * are included.
	 *
	 * @return The {@link ImmutableMap}.
	 */
	@Override public ImmutableMap<Integer, Integer> getPositionAmounts()
	{
		ImmutableMap.Builder<Integer, Integer> builder = new ImmutableMap.Builder<>();
		for (Map.Entry<Integer, Stack<Item>> entry : items.entrySet()) {
			builder.put(entry.getKey(), entry.getValue().size());
		}

		return builder.build();
	}

	/**
	 * Adds the provided {@link Item} to the first available {@link Inventory} position.
	 *
	 * @param item The {@link Item} to put to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Item} could not be added.
	 */
	@Override public void addItem(Item item) throws InventoryFullException
	{
		for (Map.Entry<Integer, ItemType> entry : types.entrySet()) {
			int      index        = entry.getKey();
			ItemType positionType = entry.getValue();
			if (positionType.instanceOf(item)) {
				items.get(index).push(item);
				numberOfItems++;
				return;
			}
		}

		for (int index = 0; index < numberOfPositions; index++) {
			if (!types.containsKey(index)) {
				types.put(index, item);
				Stack<Item> stack = new Stack<>();
				stack.push(item);
				items.put(index, stack);
				numberOfItems++;
				numberOfEmptyPositions--;
				return;
			}
		}

		throw new InventoryFullException(this, item);
	}

	/**
	 * Adds the provided {@link Item} to the position at the provided position.
	 *
	 * @param item     The {@link Item} to add to the {@link Inventory}.
	 * @param position The position of the position where the {@link Item} should be added.
	 * @throws PositionRangeException When the provided position doesn't exist.
	 * @throws TypeMismatchException  When the types of {@link Item} doesn't match.
	 */
	@Override public void addItem(Item item, int position) throws PositionRangeException, TypeMismatchException
	{
		validatePosition(position);
		ItemType positionType = types.get(position);

		if (positionType == null) {
			types.put(position, item);
			Stack<Item> stack = new Stack<>();
			stack.push(item);
			items.put(position, stack);
			numberOfItems++;
			numberOfEmptyPositions--;
			return;
		}

		if (!positionType.instanceOf(item))
			throw new TypeMismatchException(this, position, null, null);

		Stack<Item> stack = items.get(position);
		stack.push(item);
		this.numberOfItems++;
	}

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position. The {@link Item} is removed from
	 * the position afterwards.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position doesn't exist.
	 * @throws EmptyPositionException When the provided position is empty.
	 */
	@Override
	public Item takeItem(int position) throws PositionRangeException, EmptyPositionException
	{
		try {
			return takeItems(position, 1).pop();
		} catch (NotEnoughItemsException e) {
			throw new EmptyPositionException(this, position);
		}
	}

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position. The {@link Item} is removed from
	 * the position afterwards. The {@link Item} at the position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position doesn't exist.
	 * @throws EmptyPositionException When the provided position is empty.
	 * @throws TypeMismatchException  When the types of {@link Item} doesn't match.
	 */
	@Override
	public <T extends Item> T takeItem(int position, Class<T> type) throws PositionRangeException, EmptyPositionException, TypeMismatchException
	{
		try {
			return takeItems(position, 1, type).pop();
		} catch (NotEnoughItemsException e) {
			throw new EmptyPositionException(this, position);
		}
	}

	/**
	 * Returns one or more {@link Item}s from the position at the provided position. The {@link Item}s are removed from the position
	 * afterwards.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param amount   The amount of {@link Item}s to take from the {@link Inventory}.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws PositionRangeException  When the provided position doesn't exist.
	 * @throws EmptyPositionException  When the provided position is empty.
	 * @throws NotEnoughItemsException When the position doesn't contain enough {@link Item}s to serve the request.
	 */
	@Override
	public Stack<Item> takeItems(int position, int amount) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException
	{
		validatePosition(position);
		Stack<Item> result = new Stack<>();
		Stack<Item> stack  = items.get(position);

		if (stack == null)
			throw new EmptyPositionException(this, position);

		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, types.get(position), amount, stack.size());

		int moved = 0;
		while (moved < amount) {
			Item item = stack.pop();
			result.push(item);
			moved++;
			if (stack.size() == 0) {
				items.remove(position);
				types.remove(position);
				numberOfEmptyPositions++;
			}
		}

		numberOfItems -= moved;

		return result;
	}

	/**
	 * Returns one or more {@link Item}s from the position at the provided position. The {@link Item}s are removed from the position
	 * afterwards. The {@link Item}s at the position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param amount   The amount of {@link Item}s to take from the {@link Inventory}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Stack} containing the taken {@link Item}s.
	 * @throws PositionRangeException  When the provided position doesn't exist.
	 * @throws EmptyPositionException  When the provided position is empty.
	 * @throws NotEnoughItemsException When the position doesn't contain enough {@link Item}s to serve the request.
	 * @throws TypeMismatchException   When the types of {@link Item} doesn't match.
	 */
	@Override
	public <T extends Item> Stack<T> takeItems(int position, int amount, Class<T> type) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException, TypeMismatchException
	{
		validatePosition(position);
		Stack<T>    result = new Stack<>();
		Stack<Item> stack  = items.get(position);

		if (stack == null)
			throw new EmptyPositionException(this, position);

		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, types.get(position), amount, stack.size());

		int moved = 0;
		while (moved < amount) {
			Item item = stack.pop();

			if (!type.isInstance(item))
				throw new TypeMismatchException(this, position, ClassTypeItem.of(type), item);

			result.push(type.cast(item));
			moved++;
			if (stack.size() == 0) {
				items.remove(position);
				types.remove(position);
				numberOfEmptyPositions++;
			}
		}

		numberOfItems -= moved;

		return result;
	}

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position doesn't exist.
	 * @throws EmptyPositionException When the provided position is empty.
	 */
	@Override
	public Item getItem(int position) throws PositionRangeException, EmptyPositionException
	{
		try {
			return getItems(position, 1).pop();
		} catch (NotEnoughItemsException e) {
			throw new EmptyPositionException(this, position);
		}
	}

	/**
	 * Returns the last inserted {@link Item} from the position at the provided position. The {@link Item} at the
	 * position must be of the provided {@link Class} type.
	 *
	 * @param position The position of the position from where to take the last {@link Item}.
	 * @param type     The type of {@link Item} to take from the {@link Inventory} position.
	 * @return The {@link Item}.
	 * @throws PositionRangeException When the provided position doesn't exist.
	 * @throws EmptyPositionException When the provided position is empty.
	 * @throws TypeMismatchException  When the types of {@link Item} doesn't match.
	 */
	@Override
	public <T extends Item> T getItem(int position, Class<T> type) throws PositionRangeException, EmptyPositionException, TypeMismatchException
	{
		try {
			return getItems(position, 1, type).pop();
		} catch (NotEnoughItemsException e) {
			throw new EmptyPositionException(this, position);
		}
	}

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
	@Override
	public Stack<Item> getItems(int position, int amount) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException
	{
		validatePosition(position);
		ItemType    positionType = types.get(position);
		Stack<Item> result       = new Stack<>();
		Stack<Item> stack        = items.get(position);

		if (stack == null)
			throw new EmptyPositionException(this, position);

		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, positionType, amount, stack.size());

		int moved = 0;
		for (int x = stack.size() - 1; x >= 0; x--) {
			if (moved == amount)
				return result;
			result.push(stack.get(x));
			moved++;
		}

		return result;
	}

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
	@Override
	public <T extends Item> Stack<T> getItems(int position, int amount, Class<T> type) throws PositionRangeException, EmptyPositionException, NotEnoughItemsException, TypeMismatchException
	{
		validatePosition(position);
		ItemType    positionType = types.get(position);
		Stack<T>    result       = new Stack<>();
		Stack<Item> stack        = items.get(position);

		if (stack == null)
			throw new EmptyPositionException(this, position);


		if (stack.size() < amount)
			throw new NotEnoughItemsException(this, positionType, amount, stack.size());

		int moved = 0;
		for (int x = stack.size() - 1; x >= 0; x--) {
			if (moved == amount)
				return result;

			Item item = stack.get(x);
			if (!type.isInstance(item))
				throw new TypeMismatchException(this, position, ClassTypeItem.of(type), item);

			result.push(type.cast(item));
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
	 * Returns the number of {@link Item}s contained in the position at the provided position.
	 *
	 * @param position The position of the position from where to return the number of {@link Item}s.
	 * @return The number of {@link Item}s contained in the position with the provided position.
	 * @throws PositionRangeException When the provided position position is out of the legal range.
	 */
	@Override public int getNumberOfItems(int position) throws PositionRangeException
	{
		validatePosition(position);

		Stack<Item> stack = items.get(position);

		return stack == null ? 0 : stack.size();
	}

	/**
	 * Returns the maximum number of positions in the {@link Inventory}.
	 *
	 * @return The maximum number of positions in the {@link Inventory}.
	 */
	@Override public int getNumberOfPositions()
	{
		return numberOfPositions;
	}

	/**
	 * Returns the number of empty positions in the {@link Inventory}.
	 *
	 * @return The number of empty positions in the {@link Inventory}.
	 */
	@Override public int getNumberOfEmptyPositions()
	{
		return numberOfEmptyPositions;
	}

	/**
	 * Returns the number of non-empty positions in the {@link Inventory}.
	 *
	 * @return The number of non-empty positions in the {@link Inventory}.
	 */
	@Override public int getNumberOfNonEmptyPositions()
	{
		return numberOfPositions - numberOfEmptyPositions;
	}

	/**
	 * Expands the number of positions in the {@link Inventory} with the provided number of positions.
	 *
	 * @param positions The number of positions to expand the {@link Inventory} with.
	 * @return Whether or not the number of positions in the {@link Inventory} could be expanded.
	 */
	@Override public boolean expand(int positions)
	{
		if (numberOfPositions + positions < 0)
			return false;

		numberOfPositions += positions;
		return true;
	}

	/**
	 * Returns the positions in the {@link Inventory} as {@link Option}s.
	 *
	 * @return The {@link ImmutableSet} of {@link Option}s.
	 */
	@Override public ImmutableSet<Option<Item>> asOptions()
	{
		ImmutableSet.Builder<Option<Item>> builder = new ImmutableSet.Builder<>();
		items.forEach((position, stack) -> {
			Item item = stack.peek();
			builder.add(new BaseOption<>(position, item.getItemTypeName(), item.getItemTypeDescription(), item));
		});

		return builder.build();
	}

	/**
	 * Returns the positions in the {@link Inventory} with the provided {@link Class} type.
	 *
	 * @param type The type of the {@link Item} to return.
	 * @return The positions in the {@link Inventory} with the provided {@link Class} type.
	 */
	@Override public <T extends Item> ImmutableSet<Option<T>> asOptions(Class<T> type)
	{
		ImmutableSet.Builder<Option<T>> builder = new ImmutableSet.Builder<>();
		items.forEach((position, stack) -> {
			Item item = stack.peek();
			if (type.isInstance(item))
				builder.add(new BaseOption<>(position, item.getItemTypeName(), item.getItemTypeDescription(), type.cast(item)));
		});

		return builder.build();
	}

	/**
	 * Validates that the provided position position is within a legal range. The valid position position range is from
	 * <code>0</code> to <code>numberOfPositions-1</code>.
	 *
	 * @param position The position position to validate.
	 * @throws PositionRangeException When the position position is out of range.
	 */
	private void validatePosition(int position) throws PositionRangeException
	{
		if (position < 0)
			throw new PositionRangeException(this, position);

		if (position >= numberOfPositions)
			throw new PositionRangeException(this, position);
	}
}
