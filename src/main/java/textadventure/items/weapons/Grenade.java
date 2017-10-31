package textadventure.items.weapons;

import textadventure.items.Item;

public class Grenade extends AbstractSplashWeapon implements SplashWeapon , Item
{
	/**
	 * Creates a new {@link Grenade}.
	 */
	public Grenade(int damage)
	{
		super(30);
	}

	/**
	 * Returns the name of the {@link Grenade}.
	 *
	 * @return The name of the {@link Grenade}.
	 */
	@Override public String getItemTypeName()
	{
		return "Grenade.";
	}

	/**
	 * Returns the description of the {@link Grenade}.
	 *
	 * @return The description of the {@link Grenade}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Watch out for the explosion.";
	}
}
