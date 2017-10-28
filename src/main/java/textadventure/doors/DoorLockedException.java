package textadventure.doors;

import textadventure.lock.Lock;

/**
 * Thrown by {@link Door}s when some {@link textadventure.actions.Action} attempted to {@link Door#open()} or
 * {@link Door#close()} a {@link Lock.State#LOCKED} {@link Door}.
 */
public class DoorLockedException extends DoorException
{

	/**
	 * The {@link Lock} that was {@link Lock.State#LOCKED}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link DoorLockedException}.
	 *
	 * @param door The {@link Door} that was {@link Lock.State#LOCKED}.
	 * @param lock The {@link Lock} that was {@link Lock.State#LOCKED}.
	 */
	public DoorLockedException(Door door, Lock lock)
	{
		super(door);
		this.lock = lock;
	}

	/**
	 * Returns the {@link Lock} that was {@link Lock.State#LOCKED}.
	 *
	 * @return The {@link Lock} that was {@link Lock.State#LOCKED}.
	 */
	public Lock getLock()
	{
		return this.lock;
	}
}
