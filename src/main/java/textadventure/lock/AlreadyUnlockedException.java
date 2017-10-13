package textadventure.lock;

/**
 * Thrown when the {@link Lock} is already unlocked.
 */
public class AlreadyUnlockedException extends LockException
{

	/**
	 * Creates a new {@link AlreadyUnlockedException}.
	 *
	 * @param lock The {@link Lock} that was attempted unlocked.
	 */
	public AlreadyUnlockedException(Lock lock)
	{
		super(lock);
	}
}
