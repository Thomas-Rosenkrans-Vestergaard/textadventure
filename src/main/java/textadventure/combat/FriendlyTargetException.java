package textadventure.combat;

import textadventure.GameException;
import textadventure.characters.Character;

/**
 * Thrown when {@link AttackAction} is called on a member of ones own {@link Faction}.
 */
public class FriendlyTargetException extends GameException
{

	/**
	 * The {@link Character} who performed the {@link AttackAction}.
	 */
	private Character instigator;

	/**
	 * The {@link Character} who was targeted.
	 */
	private Character target;

	/**
	 * Creates a new {@link FriendlyTargetException}.
	 *
	 * @param instigator The {@link Character} who performed the {@link AttackAction}.
	 * @param target     The {@link Character} who was targeted.
	 */
	public FriendlyTargetException(Character instigator, Character target)
	{
		this.instigator = instigator;
		this.target = target;
	}

	/**
	 * Returns the {@link Character} who performed the {@link AttackAction}.
	 *
	 * @return The {@link Character} who performed the {@link AttackAction}.
	 */
	public Character getInstigator()
	{
		return this.instigator;
	}

	/**
	 * Returns The {@link Character} who was targeted.
	 *
	 * @return the {@link Character} who was targeted.
	 */
	public Character getTarget()
	{
		return this.target;
	}
}
