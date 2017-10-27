package textadventure.items.weapons;

public class Pistol implements ProjectileWeapon
{
	/**
	 * Returns the name of the {@link Pistol}.
	 *
	 * @return The name of the {@link Pistol}.
	 */
	@Override public String getItemName()
	{
		return "Pistol";
	}

	/**
	 * Returns the description of the {@link Pistol}.
	 *
	 * @return The description of the {@link Pistol}.
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
