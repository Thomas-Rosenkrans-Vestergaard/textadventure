package textadventure.items.weapons;

import textadventure.items.Item;

public class Baton extends AbstractBluntWeapon implements BluntWeapon, Item
{

	/**
	 * Creates a new {@link Baton}.
	 */
	public Baton()
	{
		super(7);
	}

	/**
	 * Returns the name of the {@link Baton}.
	 *
	 * @return The name of the {@link Baton}.
	 */
	@Override public String getItemTypeName()
	{
		return "Baton";
	}

	/**
	 * Returns the description of the {@link Baton}.
	 *
	 * @return The description of the {@link Baton}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Efficient for handling riots.";
	}
}
