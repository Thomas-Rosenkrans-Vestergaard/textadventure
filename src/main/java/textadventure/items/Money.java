package textadventure.items;

public class Money implements Item
{

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	@Override public String getItemTypeName()
	{
		return "Money";
	}

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Money is a currency";
	}
}
