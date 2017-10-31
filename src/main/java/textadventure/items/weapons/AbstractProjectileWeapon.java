package textadventure.items.weapons;

/**
 * An abstract base of the {@link ProjectileWeapon} interface.
 */
public class AbstractProjectileWeapon extends AbstractWeapon implements ProjectileWeapon
{
	/**
	 * Integar representing the damage the {@link Weapon} does.
	 */
	public AbstractProjectileWeapon(int damage)
	{
		super(damage);
	}
}
