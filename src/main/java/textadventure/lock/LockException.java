package textadventure.lock;

import textadventure.GameException;

/**
 * Thrown when exception occurs related to {@link Lock}s.
 */
public class LockException extends GameException
{

	/**
	 * The {@link Lock} that caused the {@link LockException}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockException}.
	 *
	 * @param lock The {@link Lock} that caused the {@link LockException}.
	 */
	public LockException(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Returns the {@link Lock} that caused the {@link LockException}.
	 *
	 * @return The {@link Lock} that caused the {@link LockException}.
	 */
	public Lock getLock()
	{
		return this.lock;
	}
}
