package textadventure.items.weapons;

public class Baton implements Weapon
{
	/**
	 * Returns The name of the {@link Baton}.
	 *
	 * @return the name of the {@link Baton}.
	 */
	@Override public String getItemName()
	{
		return "Baton";
	}

	/**
	 * Returns The description of the {@link Baton}.
	 *
	 * @return the description of the {@link Baton}.
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
