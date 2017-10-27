package textadventure.items.weapons;

public class Knife implements Weapon
{
	/**
	 * Returns The name of the {@link Knife}.
	 *
	 * @return the name of the {@link Knife}.
	 */
	@Override public String getItemName()
	{
		return "Knife";
	}

	/**
	 * Returns The description of the {@link Knife}.
	 *
	 * @return the description of the {@link Knife}.
	 */
	@Override public String getItemDescription()
	{
		return "Good for butter, if there was any.";
	}

	/**
	 * Returns the amount of damage done by the {@link Knife} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Knife} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
