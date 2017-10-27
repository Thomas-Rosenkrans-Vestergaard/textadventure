package textadventure.items.weapons;

public class Shotgun implements ProjectileWeapon
{
	/**
	 * Returns The name of the {@link Shotgun}.
	 *
	 * @return the name of the {@link Shotgun}.
	 */
	@Override public String getItemName()
	{
		return "Shotgun";
	}

	/**
	 * Returns The description of the {@link Shotgun}.
	 *
	 * @return the description of the {@link Shotgun}.
	 */
	@Override public String getItemDescription()
	{
		return "Apparently not used for hunting.";
	}

	/**
	 * Returns the amount of damage done by the {@link Shotgun} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link Shotgun} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
