package textadventure.items.weapons;

import textadventure.characters.Character;

public class Fist implements BluntWeapon
{

	/**
	 * Returns the name of the {@link Fist}.
	 *
	 * @return The name of the {@link Fist}.
	 */
	@Override public String getItemTypeName()
	{
		return null;
	}

	/**
	 * Returns the description of the {@link Fist}.
	 *
	 * @return The description of the {@link Fist}.
	 */
	@Override public String getItemTypeDescription()
	{
		return null;
	}

	/**
	 * Returns the amount of damage done by the {@link Fist} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Fist} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
