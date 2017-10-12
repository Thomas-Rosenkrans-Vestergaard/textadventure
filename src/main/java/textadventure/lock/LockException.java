package textadventure.lock;

public class LockException extends Exception
{

	/**
	 * The {@link Lock} the the {@link Exception} was thrown for.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockException}.
	 *
	 * @param lock The {@link Lock} the the {@link Exception} was thrown for.
	 */
	public LockException(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Returns the {@link Lock} the the {@link Exception} was thrown for.
	 *
	 * @return The {@link Lock} the the {@link Exception} was thrown for.
	 */
	public Lock getLock()
	{
		return this.lock;
	}
}
