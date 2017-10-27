package textadventure.items.weapons;

public class Pistol implements ProjectileWeapon
{
	/**
	 * Returns The name of the {@link Pistol}.
	 *
	 * @return the name of the {@link Pistol}.
	 */
	@Override public String getItemName()
	{
		return "Pistol";
	}

	/**
	 * Returns The description of the {@link Pistol}.
	 *
	 * @return the description of the {@link Pistol}.
	 */
	@Override public String getItemDescription()
	{
		return "Not fully assembled, but it might work.";
	}

	/**
	 * Returns the amount of damage done by the {@link Pistol} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Pistol} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
