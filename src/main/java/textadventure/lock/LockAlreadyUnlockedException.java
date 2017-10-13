package textadventure.lock;

public class LockAlreadyUnlockedException extends LockException
{
	public LockAlreadyUnlockedException(Lock lock)
	{
		super(lock);
	}
}
