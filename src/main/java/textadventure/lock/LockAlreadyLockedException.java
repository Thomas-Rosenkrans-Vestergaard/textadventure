package textadventure.lock;

public class LockAlreadyLockedException extends LockException
{

	public LockAlreadyLockedException(Lock lock)
	{
		super(lock);
	}
}
