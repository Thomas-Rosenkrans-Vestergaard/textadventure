package textadventure.rooms.features.doors;

/**
 * The default implementation of the {@link Door} interface.
 */
public class BaseDoor implements Door
{

	/**
	 * The description of the {@link Door}.
	 */
	private String description;

	/**
	 * The {@link Lock} on the {@link Door}.
	 */
	private Lock lock;

	/**
	 * The {@link textadventure.rooms.features.doors.Door.State} of the {@link Door}.
	 */
	private State state;

	/**
	 * Creates a new {@link BaseDoor}.
	 *
	 * @param description The description of the {@link Door}.
	 * @param lock        The {@link Lock} on the {@link Door}.
	 * @param state       The {@link textadventure.rooms.features.doors.Door.State} of the {@link Door}.
	 */
	public BaseDoor(String description, Lock lock, State state)
	{
		if (state == State.LOCKED) {
			throw new IllegalArgumentException("The lock must decide whether or not the door is locked.");
		}

		this.description = description;
		this.lock = lock;
		this.state = state;
	}

	/**
	 * Returns the description of the {@link Door}.
	 *
	 * @return The description of the {@link Door}.
	 */
	@Override public String getDescription()
	{
		return description;
	}

	/**
	 * Returns the {@link Lock} located on the {@link Door}.
	 *
	 * @return The {@link Lock} located on the {@link Door}.
	 */
	@Override public Lock getLock()
	{
		return lock;
	}

	/**
	 * Returns the {@link State} of the {@link Door}.
	 *
	 * @return The {@link State} of the {@link Door}.
	 */
	@Override public State getState()
	{
		if (state == State.OPEN) {
			return state;
		}

		Lock.State lockState = lock.getState();

		if (lockState == Lock.State.LOCKED) {
			return State.LOCKED;
		}

		if (lockState == Lock.State.UNLOCKED) {
			return State.CLOSED;
		}

		throw new IllegalStateException();
	}
}
