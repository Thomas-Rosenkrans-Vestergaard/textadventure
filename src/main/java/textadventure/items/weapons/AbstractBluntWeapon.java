package textadventure.items.weapons;

/**
 * An abstract base of the {@link BluntWeapon} interface.
 */
public abstract class AbstractBluntWeapon extends AbstractWeapon implements BluntWeapon
{
	/**
	 * The amount of damage done by the {@link Weapon}.
	 */
	public AbstractBluntWeapon(int damage)
	{
		super(damage);
	}
}
