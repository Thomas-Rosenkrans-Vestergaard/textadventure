package textadventure.lock;

import textadventure.BaseProperty;
import textadventure.Game;
import textadventure.ui.GameInterface;

import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class Lock extends BaseProperty
{

	/**
	 * Represents the {@link State} of a {@link Lock}.
	 */
	public enum State
	{

		/**
		 * The {@link Lock} is locked.
		 */
		LOCKED,

		/**
		 * The {@link Lock} is unlocked.
		 */
		UNLOCKED,
	}

	/**
	 * The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with a matching
	 * code.
	 */
	private final String code;

	/**
	 * The {@link State} of the {@link Lock}.
	 */
	private State state;

	/**
	 * Creates a new {@link Lock}.
	 *
	 * @param code  The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with a
	 *              matching code.
	 * @param state The state of the {@link Lock}.
	 */
	public Lock(String code, State state)
	{
		this.code = code;
		this.state = state;
	}

	/**
	 * Creates and returns a {@link Lock} with the {@link LockLockAction}, {@link UnlockLockAction} and
	 * {@link InspectLockAction}.
	 *
	 * @param code  The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 *              matching codes.
	 * @param state The state of the {@link Lock}.
	 * @param game  The {@link Game} instance.
	 * @return The newly created {@link Lock}.
	 */
	public static Lock factory(String code, State state, Game game)
	{
		Lock          lock          = new Lock(code, state);
		GameInterface gameInterface = game.getGameInterface();

		lock.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		lock.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));
		lock.addAction("inspect", new InspectLockAction(lock, gameInterface::onLockInspect));

		return lock;
	}

	/**
	 * Lock the {@link Lock} using the provided {@link Key}.
	 *
	 * @param key The {@link Key} to use when locking the {@link Lock}.
	 * @throws LockAlreadyLockedException When the {@link Lock} is already {@link Lock.State#LOCKED}.
	 * @throws IncorrectKeyException  When the incorrect {@link Key} is used on the {@link Lock}.
	 */
	public void lock(Key key) throws LockAlreadyLockedException, IncorrectKeyException
	{

		if (state == LOCKED) {
			throw new LockAlreadyLockedException(this);
		}

		if (!code.equals(key.getCode())) {
			throw new IncorrectKeyException(this, key);
		}

		state = LOCKED;
	}

	/**
	 * Unlocks the {@link Lock} using the provided {@link Key}.
	 *
	 * @param key The {@link Key} to use when unlocking the {@link Lock}.
	 * @throws LockAlreadyUnlockedException When the {@link Lock} is already {@link Lock.State#LOCKED}.
	 * @throws IncorrectKeyException    When the incorrect {@link Key} is used on the {@link Lock}.
	 */
	void unlock(Key key) throws LockAlreadyUnlockedException, IncorrectKeyException
	{

		if (state == UNLOCKED) {
			throw new LockAlreadyUnlockedException(this);
		}

		if (!code.equals(key.getCode())) {
			throw new IncorrectKeyException(this, key);
		}

		state = UNLOCKED;
	}

	/**
	 * Returns the code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with a
	 * matching code.
	 *
	 * @return The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with a
	 * matching code.
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
