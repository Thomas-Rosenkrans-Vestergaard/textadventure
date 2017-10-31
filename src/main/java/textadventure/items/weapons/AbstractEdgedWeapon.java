package textadventure.items.weapons;

/**
 * An abstract base of the {@link EdgedWeapon} interface.
 */
public abstract class AbstractEdgedWeapon extends AbstractWeapon implements EdgedWeapon
{

	/**
	 * Storage how  many {@link Whetstone} used on a {@link EdgedWeapon}
	 */
	private int numberOfTimesSharpened;

	/**
	 * Integar representing the damage the {@link Weapon} does.
	 */
	public AbstractEdgedWeapon(int damage)
	{
		super(damage);
	}

	/**
	 * Increases the damage done by the {@link EdgedWeapon}.
	 *
	 * @param whetstone The {@link Whetstone} used to sharpen the {@link EdgedWeapon}.
	 * @return The damage increase.
	 * @throws {@link CantSharpenException} when you can sharpen your weapon any more
	 */
	@Override public final void sharpen(Whetstone whetstone) throws CantSharpenException
	{
		if (numberOfTimesSharpened > 4) {
			throw new CantSharpenException(this, whetstone);
		}

		damage += 5;
	}
}
