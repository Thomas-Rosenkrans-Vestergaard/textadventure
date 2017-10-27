package textadventure.items.medical;

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
		return "band-aid";
	}

	/**
	 * Returns the description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemDescription()
	{
		return "Can be used to stop bleeding from small wounds.";
	}
}
