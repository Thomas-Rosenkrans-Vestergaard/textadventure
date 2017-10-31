package textadventure.items.weapons;

import textadventure.items.Item;

public class PineappleGrenade extends AbstractSplashWeapon implements SplashWeapon , Item
{
	/**
	 * Creates a new {@link PineappleGrenade}.
	 */
	public PineappleGrenade(int damage)
	{
		super(45);
	}

	/**
	 * Returns the name of the {@link PineappleGrenade}.
	 *
	 * @return The name of the {@link PineappleGrenade}.
	 */
	@Override public String getItemTypeName()
	{
		return "Pineapple grenade.";
	}

	/**
	 * Returns the description of the {@link PineappleGrenade}.
	 *
	 * @return The description of the {@link PineappleGrenade}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Watch out for the explosion.";
	}
}
