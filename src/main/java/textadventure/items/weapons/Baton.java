package textadventure.items.weapons;

public class Baton implements BluntWeapon
{
	/**
	 * Returns the name of the {@link Baton}.
	 *
	 * @return The name of the {@link Baton}.
	 */
	@Override public String getItemName()
	{
		return "Baton";
	}

	/**
	 * Returns the description of the {@link Baton}.
	 *
	 * @return The description of the {@link Baton}.
	 */
	@Override public String getItemDescription()
	{
		return "Efficient for handling riots.";
	}

	/**
	 * Returns the amount of damage done by the {@link Baton} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Baton} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
