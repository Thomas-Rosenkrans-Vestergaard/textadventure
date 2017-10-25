package textadventure.items.medkit;

import textadventure.items.Item;

public class BandAid implements Item
{

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemName()
	{
		return "band_aid";
	}

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemDescription()
	{
		return "Can be used to stop bleeding from small wounds.";
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
