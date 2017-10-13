package textadventure.lock;

/**
 * Thrown when a {@link Key} was used on an incompatible {@link Lock}.
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
	 * @param lock The {@link Lock}.
	 * @param key  The {@link Key}.
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
