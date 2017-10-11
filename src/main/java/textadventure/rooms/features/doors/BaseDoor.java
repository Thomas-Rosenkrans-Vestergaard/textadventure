package textadventure.rooms.features.doors;

import textadventure.Direction;
import textadventure.rooms.Room;

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
	 * The {@link Room} on one side of the {@link Door}.
	 */
	private Room a;

	/**
	 * The {@link Room} on the other side of the {@link Door}.
	 */
	private Room b;

	/**
	 * The {@link Direction} of the {@link Door} in the path <code>a</code> -> <code>b</code>.
	 */
	private Direction direction;

	/**
	 * Creates a new {@link BaseDoor}.
	 *
	 * @param description The description of the {@link Door}.
	 * @param lock        The {@link Lock} on the {@link Door}.
	 * @param state       The {@link textadventure.rooms.features.doors.Door.State} of the {@link Door}.
	 * @param a           The {@link Room} on one side of the {@link Door}.
	 * @param b           The {@link Room} on the other side of the {@link Door}.
	 */
	public BaseDoor(String description, Lock lock, State state, Room a, Room b, Direction direction)
	{
		if (state == State.LOCKED) {
			throw new IllegalArgumentException("Cannot initiate door in the LOCKED state. Use a locked Lock.");
		}

		this.description = description;
		this.lock = lock;
		this.state = state;
		this.a = a;
		this.b = b;
		this.direction = direction;
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

	/**
	 * Sets the {@link State} of the {@link Door}.
	 *
	 * @param state The {@link State} to set.
	 */
	@Override public boolean setState(State state)
	{
		if (lock.getState() == Lock.State.LOCKED) {
			return false;
		}

		this.state = state;
		return true;
	}

	/**
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 *
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	@Override public Room getOtherSide(Room room)
	{
		if (room == a)
			return b;

		if (room == b)
			return a;

		return null;
	}

	/**
	 * Returns the {@link Direction} of the {@link Door} in the provided {@link Room}.
	 *
	 * @param room The {@link Room} to use as perspective.
	 *
	 * @return The {@link Direction} of the {@link Door} in the provided {@link Room}. Returns <code>null</code> when
	 * the {@link Direction} could not be found, or the provided {@link Room} is unknown to the {@link Door}.
	 */
	@Override public Direction getDirection(Room room)
	{
		if (room == a)
			return direction;

		if (room == b)
			return direction.getInverse();

		return null;
	}
}
