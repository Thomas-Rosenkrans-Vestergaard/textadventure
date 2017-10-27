package textadventure.items.weapons;

import textadventure.items.Item;

/**
 * Represent a type of {@link Weapon}
 */
public interface EdgedWeapon extends Weapon
{
	/**
	 * Increas the dmg of the {@link EdgedWeapon}
	 * @param whetstone
	 * @return Increas the dmg of the {@link EdgedWeapon}
	 */
	int sharpen(Item whetstone);
}
