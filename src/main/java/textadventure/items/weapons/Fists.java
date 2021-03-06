package textadventure.items.weapons;

import textadventure.characters.Character;

public class Fists extends AbstractBluntWeapon implements BluntWeapon
{

	/**
	 * Returns the amount of damage done by the {@link Fists} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Fists} to a {@link Character}.
	 */
	public Fists()
	{
		super(5);
	}

	/**
	 * Returns the name of the {@link Weapon}.
	 *
	 * @return The name of the {@link Weapon}.
	 */
	@Override public String getWeaponName()
	{
		return "Fists";
	}

	/**
	 * Returns the description of the {@link Weapon}.
	 *
	 * @return The description of the {@link Weapon}.
	 */
	@Override public String getWeaponDescription()
	{
		return "The first weapon.";
	}
}
