package textadventure.items.weapons;

/**
 * Represents a class of {@link Weapon}.
 */
public interface EdgedWeapon extends Weapon
{
	/**
	 * Increases the damage done by the {@link EdgedWeapon}.
	 *
	 * @param whetstone The {@link Whetstone} used to sharpen the {@link EdgedWeapon}.
	 * @throws {@link CantSharpenException} when you can't sharpen your weapon.
	 */
	void sharpen(Whetstone whetstone) throws CantSharpenException;
}
