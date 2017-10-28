package textadventure.lock;

/**
 * Thrown when {@link Lock#unlock(Key)} (Key)} is called on a {@link Lock.State#UNLOCKED} {@link Lock}.
 */
public class LockAlreadyUnlockedException extends LockException
{

	/**
	 * Creates a new {@link LockAlreadyUnlockedException}.
	 *
	 * @param lock The {@link Lock.State#UNLOCKED} {@link Lock}.
	 */
	public LockAlreadyUnlockedException(Lock lock)
	{
		super(lock);
	}
}

