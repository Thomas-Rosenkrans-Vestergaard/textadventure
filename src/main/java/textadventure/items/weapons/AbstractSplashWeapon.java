package textadventure.items.weapons;

/**
 * An abstract base of the {@link SplashWeapon} interface.
 */
public abstract class AbstractSplashWeapon extends AbstractWeapon implements SplashWeapon
{
	/**
	 * Integar representing the damage the {@link Weapon} does.
	 */
	public AbstractSplashWeapon(int damage)
	{
		super(damage);
	}
}
