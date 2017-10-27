package textadventure.items.weapons;

public class Fist implements BluntWeapon
{
	/**
	 * Returns the name of the {@link Fist}.
	 *
	 * @return The name of the {@link Fist}.
	 */
	@Override public String getItemName()
	{
		return "Fist";
	}

	/**
	 * Returns the description of the {@link Fist}.
	 *
	 * @return The description of the {@link Fist}.
	 */
	@Override public String getItemDescription()
	{
		return "The standard choice.";
	}

	/**
	 * Returns the amount of damage done by the {@link Fist} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Fist} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
