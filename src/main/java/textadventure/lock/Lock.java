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
	public State getState()
	{
		return this.state;
	}
}
