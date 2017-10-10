package textadventure;

public class AbstractLivingBeing implements LivingBeing
{

	/**
	 * The max health of the {@link LivingBeing}.
	 */
	private final int maxHealth;

	/**
	 * The current health of the {@link LivingBeing}.
	 */
	private int currentHealth;

	/**
	 * Creates a new {@link AbstractLivingBeing}.
	 *
	 * @param maxHealth The max health of the {@link AbstractLivingBeing}.
	 */
	public AbstractLivingBeing(final int maxHealth)
	{
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}

	@Override public int getCurrentHealth()
	{
		return currentHealth;
	}

	@Override public int getMaxHealth()
	{
		return maxHealth;
	}

	@Override public void damage(int points)
	{
		currentHealth -= points;
	}

	@Override public void heal(int points)
	{
		currentHealth += points;
	}

	@Override public LivingBeingStatus status()
	{
		return currentHealth <= 0 ? LivingBeingStatus.DEAD : LivingBeingStatus.ALIVE;
	}
}
