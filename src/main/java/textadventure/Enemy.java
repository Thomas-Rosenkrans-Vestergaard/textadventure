package textadventure;

import textadventure.items.weapons.Weapon;

/**
 * Represents an opponent in-game.
 */
public interface Enemy extends Character
{

	/**
	 * Attacks the {@link Enemy} using the provided {@link textadventure.items.weapons.Weapon}.
	 *
	 * @param weapon The {@link Weapon} used to attack the {@link Enemy}.
	 * @return The amount of damage taken by the {@link Enemy}.
	 */
	int attack(Weapon weapon);
}
