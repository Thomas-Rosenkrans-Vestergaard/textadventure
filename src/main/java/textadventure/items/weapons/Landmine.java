package textadventure.items.weapons;

import textadventure.items.Item;

public class Landmine extends AbstractSplashWeapon implements SplashWeapon, Item
{
	/**
	 * Creates a new {@link Landmine}.
	 */
	public Landmine(int damage)
	{
		super(75);
	}

	/**
	 * Returns the name of the {@link Landmine}.
	 *
	 * @return The name of the {@link Landmine}.
	 */
	@Override public String getItemTypeName()
	{
		return "Landmine.";
	}

	/**
	 * Returns the description of the {@link Landmine}.
	 *
	 * @return The description of the {@link Landmine}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "If you find one of these watch out for you step. ";
	}


}
