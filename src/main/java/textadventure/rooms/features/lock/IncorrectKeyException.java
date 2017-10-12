package textadventure.rooms.features.lock;

/**
 * Thrown
 */
public class IncorrectKeyException extends LockableException
{

	/**
	 * The incorrect {@link Key}.
	 */
	private Key key;

	/**
	 *
	 * @param lock
	 * @param key
	 */
	public IncorrectKeyException(Lock lock, Key key)
	{
		super(lock);
		this.key = key;
	}
}
