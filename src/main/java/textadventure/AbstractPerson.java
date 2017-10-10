package textadventure;

import textadventure.combat.DamageSource;
import textadventure.combat.HealthSource;

public class AbstractPerson implements Person
{

	/**
	 * The largest amount of health the {@link Person} can posses.
	 */
	private final int maxHP;

	/**
	 * The current amount of health the {@link Person} possesses.
	 */
	private int currentHP;

	/**
	 * The {@link textadventure.Person.State} of the {@link Person}.
	 */
	private State state;

	/**
	 * Creates a new {@link AbstractPerson}.
	 *
	 * @param maxHP     The maximum amount of hit points of the {@link Person}.
	 * @param currentHP The amount of hit points that the {@link Person} starts out with.
	 */
	public AbstractPerson(final int maxHP, final int currentHP)
	{
		this.maxHP = maxHP;
		this.currentHP = currentHP;
		this.state = currentHP > 0 ? State.ALIVE : State.DEAD;
	}

	/**
	 * Creates a new {@link AbstractPerson}. Sets the <code>currentHP</code> of the {@link Person} to
	 * <code>maxHP</code>.
	 *
	 * @param maxHP The max health of the {@link AbstractPerson}.
	 */
	public AbstractPerson(final int maxHP)
	{
		this(maxHP, maxHP);
	}

	/**
	 * Returns the current amount of HP the {@link Person} has.
	 *
	 * @return The current amount of HP the {@link Person} has.
	 */
	@Override public int getCurrentHP()
	{
		return currentHP;
	}

	/**
	 * Returns the largest amount of HP the {@link Person} can have.
	 *
	 * @return The largest amount of HP the {@link Person} can have.
	 */
	@Override public int getMaxHP()
	{
		return maxHP;
	}

	/**
	 * Damages the {@link Person} for the provided number of health points.
	 *
	 * @param source The source of the damage.
	 */
	@Override public void damage(DamageSource source)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Heals the {@link Person} for the provided number of health points.
	 *
	 * @param source The source of the healing.
	 */
	@Override public void heal(HealthSource source)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the state of the {@link Person}.
	 *
	 * @return The state of the {@link Person}.
	 */
	@Override public State getState()
	{
		return state;
	}
}
