package textadventure.items.weapons;

public class Wrench implements BluntWeapon
{

	/**
	 * Returns The name of the {@link Wrench}.
	 *
	 * @return the name of the {@link Wrench}.
	 */
	@Override public String getItemName()
	{
		return "Wrench";
	}

	/**
	 * Returns The description of the {@link Wrench}.
	 *
	 * @return the description of the {@link Wrench}.
	 */
	@Override public String getItemDescription()
	{
		return "Seems to be made of aluminium.";
	}

	/**
	 * Returns the amount of damage done by the {@link Wrench} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link Wrench} to a {@link Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}

}
