package textadventure.items.weapons;

public class SubmachineGun implements Weapon
{
	/**
	 * Returns The name of the {@link SubmachineGun}.
	 *
	 * @return the name of the {@link SubmachineGun}.
	 */
	@Override public String getItemName()
	{
		return "Submachine gun";
	}

	/**
	 * Returns The description of the {@link SubmachineGun}.
	 *
	 * @return the description of the {@link SubmachineGun}.
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
