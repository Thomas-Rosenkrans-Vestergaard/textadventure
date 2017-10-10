package textadventure;

import textadventure.combat.DamageSource;
import textadventure.combat.HealthSource;

public interface Person
{

	/**
	 * The {@link State}s that a {@link Person} can be in.
	 */
	public enum State
	{
		ALIVE,
		DEAD,
	}

	/**
	 * Returns the current amount of HP the {@link Person} has.
	 *
	 * @return The current amount of HP the {@link Person} has.
	 */
	int getCurrentHP();

	/**
	 * Returns the largest amount of HP the {@link Person} can have.
	 *
	 * @return The largest amount of HP the {@link Person} can have.
	 */
	int getMaxHP();

	/**
	 * Damages the {@link Person} for the provided number of health points.
	 *
	 * @param source The source of the damage.
	 */
	void damage(DamageSource source);

	/**
	 * Heals the {@link Person} for the provided number of health points.
	 *
	 * @param source The source of the healing.
	 */
	void heal(HealthSource source);

	/**
	 * Returns the state of the {@link Person}.
	 *
	 * @return The state of the {@link Person}.
	 */
	State getState();
}
