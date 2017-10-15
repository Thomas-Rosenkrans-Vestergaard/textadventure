package textadventure.lock;

import textadventure.Action;
import textadventure.ActionException;
import textadventure.Player;

/**
 * Thrown when the {@link Lock} is already locked.
 */
public class AlreadyLockedException extends ActionException
{

	/**
	 * Creates a new {@link AlreadyUnlockedException}.
	 * @param property The {@link Lock}
	 * @param action
	 * @param player
	 */
	public AlreadyLockedException(Lock property, Action action, Player player)
	{
		super(property, action, player);
	}
}
