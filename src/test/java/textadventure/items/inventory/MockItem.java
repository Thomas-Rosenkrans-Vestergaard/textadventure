package textadventure.items.inventory;

import textadventure.items.Item;

public class MockItem implements Item
{

	@Override public String getItemName()
	{
		return null;
	}

	@Override public int getType()
	{
		return 0;
	}

	@Override public String getOptionDescription()
	{
		return null;
	}

	@Override public String getOptionName()
	{
		return null;
	}

	@Override
	public String getItemDescription()
	{
		return null;
	}
}
