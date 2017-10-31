package textadventure.items.weapons;

import textadventure.characters.Character;

public class Fist extends AbstractBluntWeapon implements BluntWeapon
{
	/**
<<<<<<< HEAD
	 * Creates a new {@link Fist}.
=======
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
>>>>>>> 31fd471485b287efec109951474242969ea04857
	 */
	public Fist()
	{
		super(5);
	}
}
