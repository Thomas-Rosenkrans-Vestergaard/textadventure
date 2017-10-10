package textadventure;

public interface LivingBeing
{

	/**
	 * Returns the current health of the {@link LivingBeing}.
	 *
	 * @return The current health of the {@link LivingBeing}.
	 */
	int getCurrentHealth();

	/**
	 * Returns the max health of the {@link LivingBeing}.
	 *
	 * @return The max health of the {@link LivingBeing}.
	 */
	int getMaxHealth();

	/**
	 * Damages the {@link LivingBeing} for the provided number of health points.
	 *
	 * @param points The number of points to apply as damage.
	 */
	void damage(int points);

	/**
	 * Heals the {@link LivingBeing} for the provided number of health points.
	 *
	 * @param points The number of points to heal the {@link LivingBeing} for.
	 */
	void heal(int points);

	/**
	 * Returns the status of the {@link LivingBeing}.
	 *
	 * @return The status of the {@link LivingBeing}.
	 */
	LivingBeingStatus status();
}
