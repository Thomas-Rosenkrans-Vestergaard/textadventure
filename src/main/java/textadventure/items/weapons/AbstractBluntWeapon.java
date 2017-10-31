package textadventure.items.weapons;

/**
 * An abstract base of the {@link BluntWeapon} interface.
 */
public abstract class AbstractBluntWeapon extends AbstractWeapon implements BluntWeapon
{
	/**
	 * Integar representing the damage the {@link Weapon} does.
	 */
	public AbstractBluntWeapon(int damage)
	{
		super(damage);
	}
}
