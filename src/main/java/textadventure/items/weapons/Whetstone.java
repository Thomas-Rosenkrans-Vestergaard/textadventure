package textadventure.items.weapons;

import textadventure.items.Item;

public class Whetstone implements Item
{

	/**
	 * Returns the name of the {@link Whetstone}.
	 *
	 * @return The name of the {@link Whetstone}.
	 */
	@Override public String getItemTypeName()
	{
		return "whetstone";
	}

	/**
	 * Returns the description of the {@link Whetstone}.
	 *
	 * @return The description of the {@link Whetstone}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Sharpen your edged weapons.";
	}
}
