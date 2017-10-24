package textadventure.items;

import com.google.common.collect.ImmutableMap;
import textadventure.items.exception.InventoryFullException;
import textadventure.items.exception.UnknownSlotException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class BaseInventory implements Inventory
{

	/**
	 * The {@link Slot}s in the {@link Inventory} mapped to the type of {@link Item} they contain.
	 */
	private HashMap<Class<? extends Item>, List<Slot>> types = new HashMap<>();

	/**
	 * The {@link Slot}s in the {@link Inventory}.
	 */
	private List<Slot> slots = new ArrayList<>();

	/**
	 * Returns the {@link Item} in the provided <code>slot</code>. The {@link Item} is then deleted from the
	 * {@link Inventory}.
	 *
	 * @param slot The <code>slot</code> to get the {@link Item} from.
	 * @return The {@link Item} in the provided <code>slot</code>.
	 * @throws UnknownSlotException When the provided <code>slot</code> doesn't exist in the {@link Inventory}.
	 */
	@Override public Slot getSlot(int slot) throws UnknownSlotException
	{
		Slot result = slots.get(slot);
		if (result == null) throw new UnknownSlotException(this);
		return result;
	}

	/**
	 * Returns a stream of {@link Slot}s that can contain the provided {@link Item} type.
	 *
	 * @param type The type of {@link Item} the slot can contain.
	 * @return The stream of {@link Slot}s that fit the criteria.
	 * @throws UnknownSlotException When no {@link Slot} exists that fit the criteria.
	 */
	@Override public Stream<Slot> getSlot(Class<? extends Item> type) throws UnknownSlotException
	{
		List<Slot> slots = types.get(type);
		if (slots == null) throw new UnknownSlotException(this);
		return slots.stream();
	}

	/**
	 * Adds a new {@link Item} to the {@link Inventory}. The {@link Item} is added to the first place it fits.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Inventory} cannot add the provided {@link Item}.
	 */
	@Override public void addItem(Item item) throws InventoryFullException
	{
		List<Slot> slots = types.get(item.getClass());
		if(slots == null) throw new UnknownSlotException(this);
	}

	/**
	 * Removes the {@link Slot} at the provided <code>slot</code> number.
	 *
	 * @param slot The <code>slot</code> number.
	 * @throws UnknownSlotException When there exists no {@link Slot}.
	 */
	@Override public void removeSlot(int slot) throws UnknownSlotException
	{

	}

	/**
	 * Returns an {@link ImmutableMap} of the items in the {@link Inventory}. The {@link ImmutableMap} does not contain
	 * <code>null</code> values.
	 *
	 * @return The {@link ImmutableMap} of the items in the {@link Inventory}. The {@link ImmutableMap} does not contain
	 * <code>null</code> values.
	 */
	@Override public ImmutableMap<Integer, Slot> getSlots()
	{
		return null;
	}

	/**
	 * Returns the number of slots in the {@link Inventory}.
	 *
	 * @return The number of slots in the {@link Inventory}.
	 */
	@Override public int countSlots()
	{
		return 0;
	}

	/**
	 * Returns the number of non-empty slots.
	 *
	 * @return The number of non-empty slots.
	 */
	@Override public int countNonEmptySlots()
	{
		return 0;
	}

	/**
	 * Returns the number of empty slots.
	 *
	 * @return The number of empty slots.
	 */
	@Override public int countEmptySlots()
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

	}
}
