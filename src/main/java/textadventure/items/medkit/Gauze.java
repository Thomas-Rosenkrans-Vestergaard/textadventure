package textadventure.items.medkit;

import textadventure.items.Item;

public class Gauze implements Item
{
	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemName()
	{
		return "gauze";
	}

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemDescription()
	{
		return "Can be used to stop bleeding from bigger wounds.";
	}

	/**
	 * Returns an integer representing the type of the {@link Item}.
	 *
	 * @return The integer representing the type of the {@link Item}.
	 */
	@Override public int getType()
	{
		return getClass().hashCode();
	}
}