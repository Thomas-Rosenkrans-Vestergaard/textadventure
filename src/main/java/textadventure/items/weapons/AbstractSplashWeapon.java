package textadventure.items.weapons;

import textadventure.characters.Character;

/**
 * An abstract base of the {@link SplashWeapon} interface.
 */
public abstract class AbstractSplashWeapon extends AbstractWeapon implements SplashWeapon
{

	/**
	 * Creates a new {@link AbstractWeapon}.
	 *
	 * @param damage          The amount of damage done by the {@link Weapon}.
	 * @param numberOfTargets The number of {@link Character}s the {@link Weapon} can be used on at once.
	 */
	public AbstractSplashWeapon(int damage, int numberOfTargets)
	{
		super(damage, numberOfTargets);
	}
}
