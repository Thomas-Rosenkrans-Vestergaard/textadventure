package textadventure.lock;

import textadventure.actions.AbstractAction;
import textadventure.actions.Action;

public abstract class LockAction extends AbstractAction
{

	/**
	 * The {@link Lock} to execute {@link Action}s upon.
	 */
	protected Lock lock;

	/**
	 * Creates a new {@link LockAction}.
	 *
	 * @param lock The {@link Lock} to execute {@link Action}s upon.
	 */
	LockAction(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Returns the {@link Lock} to execute {@link Action}s upon.
	 *
	 * @return The {@link Lock} to execute {@link Action}s upon.
	 */
	public Lock getLock()
	{
		return this.lock;
	}
}
