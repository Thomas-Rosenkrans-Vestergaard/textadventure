package textadventure.combat;

import textadventure.characters.Character;

/**
 * Represents a source of damage done to a {@link Character}.
 */
public interface DamageSource
{

	/**
	 * Returns the amount of damage done by the {@link DamageSource} to a {@link Character}.
	 *
	 * @return The amount of damage done by the {@link DamageSource} to a {@link Character}.
	 */
	int getDamage();
}
