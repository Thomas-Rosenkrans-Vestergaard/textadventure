package textadventure.items.medical;

import textadventure.items.Item;

public class Gauze implements Item
{
	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemTypeName()
	{
		return "gauze";
	}

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Can be used to stop bleeding from bigger wounds.";
	}
}
