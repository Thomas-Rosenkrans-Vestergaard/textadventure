package textadventure.doors;

import textadventure.lock.Lock;
import textadventure.rooms.Room;

/**
 * Base implementation of the {@link Door} interface.
 */
public class BaseDoor implements Door
{

	/**
	 * The {@link Door.State} of the {@link Door}.
	 */
	private State state;

	/**
	 * The {@link Lock} on the {@link Door}.
	 */
	private Lock lock;

	/**
	 * The {@link Room} on one side of the {@link Door}.
	 */
	private Room roomA;

	/**
	 * The {@link Room} on the other side of the {@link Door}.
	 */
	private Room roomB;

	/**
	 * Creates a new {@link BaseDoor}.
	 *
	 * @param state The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock  The {@link Lock} placed on the {@link Door}.
	 * @param roomA The first room (<code>roomA</code>).
	 * @param roomB The second room (<code>roomB</code>).
	 */
	public BaseDoor(State state, Lock lock, Room roomA, Room roomB)
	{
		this.lock = lock;
		this.state = state;
		this.roomA = roomA;
		this.roomB = roomB;
	}

	/**
	 * Opens the {@link Door}.
	 *
	 * @throws DoorAlreadyOpenException When the {@link Door} is already {@link State#OPEN}.
	 * @throws DoorLockedException      When the {@link Door} is {@link Lock.State#LOCKED}.
	 */
	@Override public void open() throws DoorAlreadyOpenException, DoorLockedException
	{
		if (state == State.OPEN) {
			throw new DoorAlreadyOpenException(this);
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new DoorLockedException(this, lock);
		}

		this.state = State.OPEN;
	}

	/**
	 * Closes the {@link Door}.
	 *
	 * @throws DoorAlreadyClosedException When the {@link Door} is already {@link State#CLOSED}.
	 * @throws DoorLockedException        When the {@link Door} is {@link Lock.State#LOCKED}.
	 */
	@Override public void close() throws DoorAlreadyClosedException, DoorLockedException
	{
		if (state == State.CLOSED) {
			throw new DoorAlreadyClosedException(this);
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new DoorLockedException(this, lock);
		}

		this.state = State.CLOSED;
	}

	/**
	 * Returns the {@link State} of the {@link Door}.
	 *
	 * @return The {@link State} of the {@link Door}.
	 */
	@Override public State getState()
	{
		return state;
	}

	/**
	 * Returns the {@link Lock} on the {@link Door}.
	 *
	 * @return The {@link Lock} on the {@link Door}.
	 */
	@Override public Lock getLock()
	{
		return lock;
	}

	/**
	 * Returns one of the {@link Room}s the {@link Door} connects.
	 *
	 * @return One of the {@link Room}s the {@link Door} connects.
	 */
	@Override public Room getRoomA()
	{
		return roomA;
	}

	/**
	 * Returns the other {@link Room}s the {@link Door} connects.
	 *
	 * @return The other {@link Room}s the {@link Door} connects.
	 */
	@Override public Room getRoomB()
	{
		return roomB;
	}

	/**
	 * Sets one of the {@link Room}s the {@link Door} connects.
	 *
	 * @param a One of the {@link Room}s the {@link Door} connects.
	 */
	@Override public void setRoomA(Room a)
	{
		this.roomA = a;
	}

	/**
	 * Sets the other {@link Room}s the {@link Door} connects.
	 *
	 * @param b The other {@link Room}s the {@link Door} connects.
	 */
	@Override public void setRoomB(Room b)
	{
		this.roomB = b;
	}

	/**
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	@Override public Room getInverseRoom(Room room)
	{
		if (roomA == null || roomB == null)
			throw new IllegalStateException("Cannot call getInverseRoom before both roomA and roomB are declared.");

		if (room == roomA) return roomB;
		if (room == roomB) return roomA;

		return null;
	}
}
