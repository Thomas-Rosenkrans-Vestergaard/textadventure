package textadventure.items.weapons;

/**
 * An abstract base of the {@link Weapon} interface.
 */
public abstract class AbstractWeapon implements Weapon
{
	/**
	 * Store The true damage of the {@link Weapon}
	 */
	protected int damage;

	/**
	 * Integar representing the damage the {@link Weapon} does.
	 */
	public AbstractWeapon(int damage)
	{
		this.damage = damage;
	}

	@Override public int getDamage()
	{
		return damage;
	}
}
