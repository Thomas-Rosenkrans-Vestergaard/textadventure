package textadventure.items.weapons;

/**
 * An abstract base of the {@link ProjectileWeapon} interface.
 */
public abstract class AbstractProjectileWeapon extends AbstractWeapon implements ProjectileWeapon
{
	/**
	 * The amount of damage done by the {@link Weapon}.
	 */
	public AbstractProjectileWeapon(int damage)
	{
		super(damage);
	}
}
