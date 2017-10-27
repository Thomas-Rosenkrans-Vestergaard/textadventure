package textadventure.items.weapons;

public class SubmachineGun implements ProjectileWeapon
{
	/**
	 * Returns the name of the {@link SubmachineGun}.
	 *
	 * @return The name of the {@link SubmachineGun}.
	 */
	@Override public String getItemName()
	{
		return "Submachine gun";
	}

	/**
	 * Returns the description of the {@link SubmachineGun}.
	 *
	 * @return The description of the {@link SubmachineGun}.
	 */
	@Override public String getItemDescription()
	{
		return "High fire rate, may get too hot to the touch.";
	}

	/**
	 * Returns the amount of damage done by the {@link SubmachineGun} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link SubmachineGun} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
