package textadventure.items.weapons;


import textadventure.items.Item;


public class AA12Shotgun extends AbstractProjectileWeapon implements ProjectileWeapon, Item
{

	/**
	 * Creates a new {@link AA12Shotgun}.
	 */
	public AA12Shotgun()
	{
		super(75);
	}

	/**
	 * Returns the name of the {@link AA12Shotgun}.
	 *
	 * @return The name of the {@link AA12Shotgun}.
	 */
	@Override public String getItemTypeName()
	{
		return "AA12 shotgun";
	}

	/**
	 * Returns the description of the {@link AA12Shotgun}.
	 *
	 * @return The description of the {@link AA12Shotgun}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Apparently used the military.";
	}
}

