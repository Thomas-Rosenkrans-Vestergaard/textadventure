package textadventure.items.inventory;

import textadventure.items.Item;

public class MockItem implements Item {

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getOptionName() {
        return null;
    }

    @Override
    public String getItemDescription() {
        return null;
    }
}
