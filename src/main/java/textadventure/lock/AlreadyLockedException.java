package textadventure.lock;

/**
 * Thrown when the {@link Lock} is already locked.
 */
public class AlreadyLockedException extends LockException
{

	/**
	 * Creates a new {@link AlreadyLockedException}.
	 *
	 * @param lock The {@link Lock} that was attempted locked.
	 */
	public AlreadyLockedException(Lock lock)
	{
		super(lock);
	}
}
