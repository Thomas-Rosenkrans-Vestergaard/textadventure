package textadventure.items.inventory;

import textadventure.items.AbstractItem;

public class MockItem extends AbstractItem
{

	public MockItem()
	{
		this(null, null);
	}

	public MockItem(String name, String description)
	{
		super(name, description);
	}

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	@Override public String getItemName()
	{
		return null;
	}

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	@Override public String getItemDescription()
	{
		return null;
	}
}
