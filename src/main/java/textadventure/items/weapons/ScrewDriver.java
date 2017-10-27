package textadventure.items.weapons;

public class ScrewDriver implements Weapon
{
	/**
	 * Returns The name of the {@link ScrewDriver}.
	 *
	 * @return the name of the {@link ScrewDriver}.
	 */
	@Override public String getItemName()
	{
		return "Screwdriver";
	}

	/**
	 * Returns The description of the {@link ScrewDriver}.
	 *
	 * @return the description of the {@link ScrewDriver}.
	 */
	@Override public String getItemDescription()
	{
		return "It's pointy.";
	}

	/**
	 * Returns the amount of damage done by the {@link ScrewDriver} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link ScrewDriver} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
