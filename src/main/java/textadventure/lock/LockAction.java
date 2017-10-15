package textadventure.lock;

import textadventure.Action;

public abstract class LockAction implements Action
{
	protected Lock lock;

	public LockAction(Lock lock)
	{
		this.lock = lock;
	}

	public Lock getLock()
	{
		return this.lock;
	}
}
