package textadventure.lock;

/**
 * Thrown when {@link Lock#lock(Key)} is called on a {@link Lock.State#LOCKED} {@link Lock}.
 */
public class LockAlreadyLockedException extends LockException
{

	/**
	 * Creates a new {@link LockAlreadyLockedException}.
	 *
	 * @param lock The {@link Lock.State#LOCKED} {@link Lock}.
	 */
	public LockAlreadyLockedException(Lock lock)
	{
		super(lock);
	}
}
