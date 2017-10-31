package textadventure.combat;

import textadventure.GameException;
import textadventure.characters.Character;

/**
 * Thrown when {@link AttackAction} is called on a member of ones own {@link Faction}.
 */
public class IncorrectTargetException extends GameException
{

	/**
	 * The instigator of the {@link AttackAction}.
	 */
	private Character instigator;

	/**
	 * The target of the {@link AttackAction}.
	 */
	private Character target;

	/**
	 * Creates a new {@link IncorrectTargetException}.
	 *
	 * @param instigator The instigator of the {@link AttackAction}.
	 * @param target     The target of the {@link AttackAction}.
	 */
	public IncorrectTargetException(Character instigator, Character target)
	{
		this.instigator = instigator;
		this.target = target;
	}

	/**
	 * Returns the instigator of the {@link AttackAction}.
	 *
	 * @return The instigator of the {@link AttackAction}.
	 */
	public Character getInstigator()
	{
		return this.instigator;
	}

	/**
	 * Returns the target of the {@link AttackAction}.
	 *
	 * @return The target of the {@link AttackAction}.
	 */
	public Character getTarget()
	{
		return this.target;
	}
}
