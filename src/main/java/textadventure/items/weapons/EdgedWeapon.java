package textadventure.items.weapons;

/**
 * Represents a type of {@link Weapon} damage
 */
public interface EdgedWeapon extends Weapon
{
	/**
	 * Increases the damage done by the {@link EdgedWeapon}.
	 *
	 * @param whetstone The {@link Whetstone} used to sharpen the {@link EdgedWeapon}.
	 * @return The damage increase.
	 * @throws {@link CantSharpenException} when you can sharpen your weapon any more
	 *
	 */
	void sharpen(Whetstone whetstone) throws CantSharpenException;
}
