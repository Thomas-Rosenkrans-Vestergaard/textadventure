package textadventure.items;

import com.google.common.collect.ImmutableMap;
import textadventure.ui.Select;

import java.util.Stack;

public interface Inventory extends Select<Item>
{

	ImmutableMap<Integer, Item> getSlots();

	/**
	 * Adds a new {@link Item} to the first available {@link Inventory} slot.
	 *
	 * @param item The {@link Item} to add to the {@link Inventory}.
	 * @throws InventoryFullException When the {@link Item} could not be added.
	 */
	void addItem(Item item) throws InventoryFullException;

	void addItem(Item item, int slot) throws UnknownItemSlotException, InventoryTypeMismatchException,
			ItemSlotFullException;

	Item takeItem(int slot) throws UnknownItemSlotException, NotEnoughItemsException;

	Stack<Item> takeItem(int slot, int amount) throws UnknownItemSlotException, NotEnoughItemsException;

	int getNumberOfItems();

	int getNumberOfItems(int slot) throws UnknownItemSlotException;

	int getNumberOfSlots();

	void expand(int slots);
}
