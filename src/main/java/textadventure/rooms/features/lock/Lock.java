package textadventure.rooms.features.lock;

public class Lock
{

	/**
	 * The possible {@link State}s of the {@link Lock}.
	 */
	public enum State
	{
		LOCKED,
		UNLOCKED,
	}

	/**
	 * The <code>code</code> represents the relationship between {@link Key}s and {@link Lock}s. {@link Key}s
	 * can only open {@link Lock}s with the same <code>code</code>.
	 */
	private final int code;

	/**
	 * The {@link State} of the {@link Lock}.
	 */
	private State state;

	/**
	 * Creates a new {@link Lock}.
	 *
	 * @param code  The <code>code</code> represents the relationship between {@link Key}s and {@link Lock}s.
	 *              {@link Key}s can only open {@link Lock}s with the same <code>code</code>.
	 * @param state The state of the {@link Lock}.
	 */
	public Lock(int code, State state)
	{
		this.code = code;
		this.state = state;
	}

	/**
	 * Returns the code of the {@link Lock}.
	 *
	 * @return The code of the {@link Lock}.
	 */
	public int getCode()
	{
		return this.code;
	}

	/**
	 * Returns the {@link State} of the {@link Lock}.
	 *
	 * @return The {@link State} of the {@link Lock}.
	 */
	public State getState()
	{
		return this.state;
	}

	/**
	 * Attempts the unlock the {@link Lock} using the provided {@link Key}.
	 *
	 * @param key The {@link Key} used to unlock the {@link Lock}.
	 *
	 * @return The updated {@link State} of the {@link Lock}.
	 */
	public State unlock(Key key) throws IncorrectKeyException
	{
		if (code == key.getCode()) {
			this.state = State.UNLOCKED;
			return State.UNLOCKED;
		}

		return this.state;
	}
}
