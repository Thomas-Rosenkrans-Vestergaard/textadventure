package textadventure.items.weapons;

/**
 * An abstract base of the {@link StabWeapon} interface.
 */
public abstract class AbstractStabWeapon extends AbstractWeapon implements StabWeapon
{
	/**
	 * Integar representing the damage the {@link Weapon} does.
	 */
	public AbstractStabWeapon(int damage)
	{
		super(damage);
	}
}
