package textadventure.items.weapons;

import textadventure.GameException;

public class CantSharpenException extends GameException
{
	/**
	 * The {@link EdgedWeapon} that couldn't be sharped
	 */
	private EdgedWeapon edgedWeapon;
	/**
	 * The {@link Whetstone} which couldn't be used.
	 */
	private Whetstone   whetstone;

	/**
	 * Creates a new {@link CantSharpenException}.
	 *
	 * @param edgedWeapon {@link EdgedWeapon}that couldn't be sharped.
	 * @param whetstone   {@link Whetstone}  where the exception occurred
	 */
	public CantSharpenException(EdgedWeapon edgedWeapon, Whetstone whetstone)
	{
		this.edgedWeapon = edgedWeapon;
		this.whetstone = whetstone;
	}

	/**
	 * Returns the {@link Weapon} that couldn't be sharped.
	 *
	 * @return The {@link Weapon} that couldn't be sharped.
	 */
	public EdgedWeapon getEdgedWeapon()
	{
		return edgedWeapon;
	}

	/**
	 * Returns the {@link Whetstone that couldn't be used.
	 *
	 * @return The {@link Whetstone} that couldn't be used.
	 */
	public Whetstone getWhetstone()
	{
		return whetstone;
	}
}
