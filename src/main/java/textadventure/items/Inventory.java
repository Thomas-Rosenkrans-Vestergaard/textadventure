package textadventure.items;

public interface Inventory
{
	void addItem(Item item) throws InventoryFullException;

	void addItem(Item item, int slot) throws UnknownItemSlotException, InventoryTypeMismatchException,
			ItemSlotFullException;

	void takeItem(Item item) throws NotEnoughItemsException;

	void takeItem(int slot, int amount) throws UnknownItemSlotException, NotEnoughItemsException;

	int getNumberOfItems();

	int getNumberOfItems(int slot) throws UnknownItemSlotException;

	int getNumberOfSlots();

	int getNumberOfEmptySlots();

	int getNumberOfNonEmptySlots();

	int isEmpty();

	int isEmpty(int slot) throws UnknownItemSlotException;

	int isFull();

	int isFull(int slot) throws UnknownItemSlotException;

	void expand(int slots);
}
