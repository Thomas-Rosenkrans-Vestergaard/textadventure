package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class Pistol extends AbstractProjectileWeapon implements ProjectileWeapon, Item
{
	/**
	 * Creates a new {@link Pistol}.
	 */
	public Pistol()
	{
		super(15);
	}

	/**
	 * Returns the name of the {@link Pistol}.
	 *
	 * @return The name of the {@link Pistol}.
	 */
	@Override public String getItemTypeName()
	{
		return "Pistol";
	}

	/**
	 * Returns the description of the {@link Pistol}.
	 *
	 * @return The description of the {@link Pistol}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Not fully assembled, but it might work.";
	}

}
