package textadventure.lock;

import textadventure.AbstractProperty;

public class Lock extends AbstractProperty
{

	/**
	 * Represents the {@link State} of a {@link Lock}.
	 */
	public enum State
	{
		LOCKED,
		UNLOCKED,
	}

	/**
	 * The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with matching
	 * codes.
	 */
	private final String code;

	/**
	 * The {@link State} of the {@link Lock}.
	 */
	private State state;

	/**
	 * Creates a new {@link Lock}.
	 *
	 * @param code  The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 *              matching codes.
	 * @param state The state of the {@link Lock}.
	 */
	public Lock(String code, State state)
	{
		this.code = code;
		this.state = state;

		addAction(new LockLockAction(this));
		addAction(new LockUnlockAction(this));
		addAction(new LockInspectAction(this));
	}

	/**
	 * Returns the name of the {@link textadventure.Property}. This name is used when accessing the
	 * {@link textadventure.Property}.
	 *
	 * @return The name of the {@link textadventure.Property}. This name is used when accessing the
	 * {@link textadventure.Property}.
	 */
	@Override public String getPropertyName()
	{
		return "lock";
	}

	/**
	 * Returns The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 * matching codes.
	 *
	 * @return The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 * matching codes.
	 */
	public String getCode()
	{
		return this.code;
	}

	/**
	 * Returns the {@link State} of the {@link Lock}.
	 *
	 * @return The {@link State} of the {@link Lock}.
	 */
	public State getState() throws IncorrectKeyException
	{
		return this.state;
	}

	/**
	 * Locks the {@link Lock} using the provided {@link Key}.
	 *
	 * @param key The {@link Key} to use to unlock the {@link Lock}.
	 *
	 * @throws LockAlreadyLockedException When the {@link Lock} is already locked.
	 * @throws IncorrectKeyException      When an incorrect {@link Key} is used to lock the {@link Lock}.
	 */
	public void lock(Key key) throws LockAlreadyLockedException, IncorrectKeyException
	{
		if (state == State.LOCKED) {
			throw new LockAlreadyLockedException(this);
		}

		if (!key.getCode().equals(this.code)) {
			throw new IncorrectKeyException(this, key);
		}

		this.state = State.LOCKED;
	}

	/**
	 * Unlocks the {@link Lock} using the provided {@link Key}.
	 *
	 * @param key The {@link Key} to use to unlock the {@link Lock}.
	 *
	 * @throws IncorrectKeyException When an incorrect {@link Key} is used to unlock the {@link Lock}.
	 */
	public void unlock(Key key) throws IncorrectKeyException, LockAlreadyUnlockedException
	{
		if (state == State.UNLOCKED) {
			throw new LockAlreadyUnlockedException(this);
		}

		if (!key.getCode().equals(this.code)) {
			throw new IncorrectKeyException(this, key);
		}

		this.state = State.UNLOCKED;
	}
}
