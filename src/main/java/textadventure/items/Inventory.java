package textadventure.items;

import com.google.common.collect.ImmutableMap;
import textadventure.items.exception.*;
import textadventure.ui.Select;

import java.util.stream.Stream;

public interface Inventory extends Select<Item>
{

	/**
	 * Returns the {@link Item} in the provided <code>slot</code>. The {@link Item} is then deleted from the
	 * {@link Inventory}.
	 *
	 * @param slot The <code>slot</code> to get the {@link Item} from.
	 * @return The {@link Item} in the provided <code>slot</code>.
	 * @throws UnknownSlotException When the provided <code>slot</code> doesn't exist in the {@link Inventory}.
	 */
	Slot getSlot(int slot) throws UnknownSlotException;

	/**
	 * Returns a stream of {@link Slot}s that can contain the provided {@link Item} type.
	 *
	 * @param type The type of {@link Item} the slot can contain.
	 * @return The stream of {@link Slot}s that fit the criteria.
	 * @throws UnknownSlotException When no {@link Slot} exists that fit the criteria.
	 */
	Stream<Slot> getSlot(Class<? extends Item> type) throws UnknownSlotException;

	/**
	 * Adds a new {@link Item} to the {@link Inventory}. The {@link Item} is added to the first place it fits.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Inventory} cannot add the provided {@link Item}.
	 */
	void addItem(Item item) throws InventoryFullException;

	/**
	 * Removes the {@link Slot} at the provided <code>slot</code> number.
	 *
	 * @param slot The <code>slot</code> number.
	 * @throws UnknownSlotException When there exists no {@link Slot}.
	 */
	void removeSlot(int slot) throws UnknownSlotException;

	/**
	 * Returns an {@link ImmutableMap} of the items in the {@link Inventory}. The {@link ImmutableMap} does not contain
	 * <code>null</code> values.
	 *
	 * @return The {@link ImmutableMap} of the items in the {@link Inventory}. The {@link ImmutableMap} does not contain
	 * <code>null</code> values.
	 */
	ImmutableMap<Integer, Slot> getSlots();

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

	/**
	 * Expands the number of slots in the {@link Inventory}.
	 *
	 * @param slots The number of slots to expand the {@link Inventory} with.
	 */
	void expand(int slots);
}
