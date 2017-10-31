package textadventure.items.weapons;

/**
 * An abstract base of the {@link StabWeapon} interface.
 */
public abstract class AbstractStabWeapon extends AbstractWeapon implements StabWeapon
{
	/**
	 * The amount of damage done by the {@link Weapon}.
	 */
	public AbstractStabWeapon(int damage)
	{
		super(damage);
	}
}
