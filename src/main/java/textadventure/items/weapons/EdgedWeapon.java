package textadventure.items.weapons;

/**
 * Represent a type of {@link Weapon}
 */
public interface EdgedWeapon extends Weapon
{
	/**
	 * Increases the damage done by the {@link EdgedWeapon}.
	 *
	 * @param whetstone The {@link Whetstone} used to sharpen the {@link EdgedWeapon}.
	 * @return The damage increase.
	 */
	int sharpen(Whetstone whetstone);
}
