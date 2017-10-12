package textadventure.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefaultInventory implements Inventory {

    private ArrayList<Item>[] slots;

//    private Map<Integer, Item> items = new HashMap<>();

    private int numberOfSlots;

    public DefaultInventory(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        for (int i = 0; i < numberOfSlots; i++) {
            slots[i] = new ArrayList<Item>();
        }
    }
//
    @Override
    public Item getItem(int slotNumber) {
        return slots[slotNumber].get(0);
    }

    @Override
    public boolean addItem(Item item) {
        for (ArrayList<Item> slot : slots) {
            if (slot.size() > 0 && slot.get(0).getName() == item.getName()) {
                slot.add(item);
                return true;
            }
        }
        for (ArrayList<Item> slot : slots) {
            if (slot.size() == 0) {
                slot.add(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(int slotNumber) {
        if (slots[slotNumber].size() > 0) {
            slots[slotNumber].remove(0);
            return true;
        }
        return false;

    }

    @Override
    public boolean useItem(int slotNumber) throws UseItemException {
        return removeItem(slotNumber);

    }

    @Override
    public int countItem(int slotNumber) {
        return slots[slotNumber].size();
    }

    @Override
    public int countSlots() {
        return numberOfSlots;
    }
}
