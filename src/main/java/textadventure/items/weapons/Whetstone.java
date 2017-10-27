package textadventure.items.weapons;

import textadventure.items.Item;

public class Whetstone implements Item
{

	/**
	 * Returns The name of the {@link ItemType}.
	 *
	 * @return the name of the {@link ItemType}.
	 */
	@Override public String getItemName()
	{
		return "whetstone";
	}

	/**
	 * Returns The description of the {@link ItemType}.
	 *
	 * @return the description of the {@link ItemType}.
	 */
	@Override public String getItemDescription()
	{
		return "Sharpen your edgedweapon";
	}
}
