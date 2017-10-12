package textadventure.rooms.features.lock;

public class LockableException extends Exception
{

	/**
	 * The {@link Lock} the the {@link Exception} was thrown for.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockableException}.
	 *
	 * @param lock The {@link Lock} the the {@link Exception} was thrown for.
	 */
	public LockableException(Lock lock)
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
