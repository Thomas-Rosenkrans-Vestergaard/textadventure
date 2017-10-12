package textadventure.items;

public interface Inventory
{
    Item getItem(int slotNumber);
    boolean addItem(Item item);
    boolean removeItem(int slotNumber);
    boolean useItem(int slotNumber) throws UseItemException;
    // the amount of a singular item.
    int countItem(int slotNumber);
    int countSlots();
}
