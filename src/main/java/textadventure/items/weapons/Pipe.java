package textadventure.items.weapons;

public class Pipe implements BluntWeapon
{
	/**
	 * Returns The name of the {@link Pipe}.
	 *
	 * @return the name of the {@link Pipe}.
	 */
	@Override public String getItemName()
	{
		return "Pipe";
	}

	/**
	 * Returns The description of the {@link Pipe}.
	 *
	 * @return the description of the {@link Pipe}.
	 */
	@Override public String getItemDescription()
	{
		return "Warning, not for tobacco, unless you're desperate.";
	}

	/**
	 * Returns the amount of damage done by the {@link Pipe} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Pipe} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}

}
