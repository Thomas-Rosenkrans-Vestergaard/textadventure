package textadventure.items.weapons;

import textadventure.combat.DamageSource;

/**
 * Represents a type of {@link Weapon} damage
 */
public interface Weapon extends DamageSource
{

	/**
	 * Returns the number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 *
	 * @return The number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 */
	int getNumberOfTargets();

	/**
	 * Returns the name of the {@link Weapon}.
	 *
	 * @return The name of the {@link Weapon}.
	 */
	String getWeaponName();

	/**
	 * Returns the description of the {@link Weapon}.
	 *
	 * @return The description of the {@link Weapon}.
	 */
	String getWeaponDescription();
}
