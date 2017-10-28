package textadventure.lock;

/**
 * Thrown when an incorrect {@link Key} is used on a {@link Lock}.
 */
public class IncorrectKeyException extends LockException
{

	/**
	 * The {@link Key} that was used.
	 */
	private Key key;

	/**
	 * Creates a new {@link IncorrectKeyException}.
	 *
	 * @param lock The {@link Lock} that the incorrect {@link Key} was used upon.
	 * @param key  The {@link Key} that was used.
	 */
	public IncorrectKeyException(Lock lock, Key key)
	{
		super(lock);

		this.key = key;
	}

	/**
	 * Returns the {@link Key} that was used.
	 *
	 * @return The {@link Key} that was used.
	 */
	public Key getKey()
	{
		return this.key;
	}
}
