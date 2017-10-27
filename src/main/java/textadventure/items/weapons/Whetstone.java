package textadventure.items.weapons;

import textadventure.items.Item;

public class Whetstone implements Item
{

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	@Override public String getItemName()
	{
		return "whetstone";
	}

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	@Override public String getItemDescription()
	{
		return "Sharpen your edgedweapon";
	}
}
