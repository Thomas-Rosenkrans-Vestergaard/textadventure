package textadventure.items.weapons;

/**
 * An abstract implementation of the {@link Weapon} interface.
 */
public abstract class AbstractWeapon implements Weapon
{
	/**
	 * The amount of damage done by the {@link Weapon}.
	 */
	protected int damage;

	/**
	 * The number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 */
	protected int numberOfTargets;

	/**
	 * Creates a new {@link AbstractWeapon}.
	 *
	 * @param damage          The amount of damage done by the {@link Weapon}.
	 * @param numberOfTargets The number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 */
	public AbstractWeapon(int damage, int numberOfTargets)
	{
		if (numberOfTargets < 1)
			throw new IllegalArgumentException("numberOfTargets must be positive.");

		this.damage = damage;
		this.numberOfTargets = numberOfTargets;
	}

	/**
	 * Creates a new {@link AbstractWeapon}. The {@link AbstractWeapon} will be able to attack one {@link Character}
	 * at a time.
	 *
	 * @param damage The amount of damage done by the {@link Weapon}.
	 */
	public AbstractWeapon(int damage)
	{
		this(damage, 1);
	}

	/**
	 * Returns the amount of damage done by the {@link Weapon}.
	 *
	 * @return The amount of damage done by the {@link Weapon}.
	 */
	@Override public int getDamage()
	{
		return damage;
	}

	/**
	 * Returns the number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 *
	 * @return The number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 */
	@Override public int getNumberOfTargets()
	{
		return this.numberOfTargets;
	}
}
