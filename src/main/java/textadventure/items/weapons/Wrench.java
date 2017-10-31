package textadventure.items.weapons;

import textadventure.items.Item;

public class Wrench extends  AbstractBluntWeapon implements BluntWeapon, Item
{
	/**
	 * Creates a new {@link Wrench}.
	 */
	public Wrench()
	{
		super(9);
	}

	/**
	 * Returns the name of the {@link Wrench}.
	 *
	 * @return The name of the {@link Wrench}.
	 */
	@Override public String getItemTypeName()
	{
		return "Wrench";
	}

	/**
	 * Returns the description of the {@link Wrench}.
	 *
	 * @return The description of the {@link Wrench}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Seems to be made of aluminium.";
	}

}
