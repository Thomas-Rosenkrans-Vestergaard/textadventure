package textadventure.items.weapons;

import textadventure.items.Item;

public class ScrewDriver extends AbstractStabWeapon implements StabWeapon, Item
{
	/**
	 * Creates a new {@link ScrewDriver}.
	 */
	public ScrewDriver()
	{
		super(10);
	}

	/**
	 * Returns the name of the {@link ScrewDriver}.
	 *
	 * @return The name of the {@link ScrewDriver}.
	 */
	@Override public String getItemTypeName()
	{
		return "Screwdriver";
	}

	/**
	 * Returns the description of the {@link ScrewDriver}.
	 *
	 * @return The description of the {@link ScrewDriver}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "It's pointy.";
	}
}

