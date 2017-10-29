package textadventure.combat;

import textadventure.Character;
import textadventure.GameException;

/**
 * Thrown when {@link AttackAction} is called on a member of ones own {@link Faction}.
 */
public class IncorrectTargetException extends GameException
{

	private Character instigator;
	private Character target;

	public IncorrectTargetException(Character instigator, Character target)
	{
		this.instigator = instigator;
		this.target = target;
	}

	public Character getInstigator()
	{
		return this.instigator;
	}

	public Character getTarget()
	{
		return this.target;
	}
}
