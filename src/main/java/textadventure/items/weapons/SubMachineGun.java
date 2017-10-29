package textadventure.items.weapons;

import textadventure.items.Item;

public class SubMachineGun implements ProjectileWeapon, Item
{

	/**
	 * Returns the name of the {@link SubMachineGun}.
	 *
	 * @return The name of the {@link SubMachineGun}.
	 */
	@Override public String getItemTypeName()
	{
		return "Sub machine gun";
	}

	/**
	 * Returns the description of the {@link SubMachineGun}.
	 *
	 * @return The description of the {@link SubMachineGun}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "High fire rate, may get too hot to the touch.";
	}

	/**
	 * Returns the amount of damage done by the {@link SubMachineGun} to a {@link textadventure.Character}.
	 *
	 * @return The amount of damage done by the {@link SubMachineGun} to a {@link textadventure.Character}.
	 */
	@Override public int getDamage()
	{
		return 0;
	}
}
