package textadventure.doors;

import textadventure.*;
import textadventure.lock.Lock;
import textadventure.rooms.Room;

/**
 * The {@link Door} represents a portal from one {@link Room} to another.
 */
public interface Door extends PropertyContainer, Property
{

	/**
	 * The {@link State} of the {@link Door}.
	 */
	enum State
	{
		OPEN,
		CLOSED,
	}

	/**
	 * Opens the {@link Door}.
	 *
	 * @throws DoorAlreadyOpenException When the {@link Door} is already open.
	 * @throws DoorLockedException      When the {@link Door} is locked.
	 */
	void open() throws DoorAlreadyOpenException, DoorLockedException;

	/**
	 * Closes the {@link Door}.
	 *
	 * @throws DoorAlreadyClosedException When the {@link Door} is already closed.
	 * @throws DoorLockedException        When the {@link Door} is locked.
	 */
	void close() throws DoorAlreadyClosedException, DoorLockedException;

	/**
	 * Returns the {@link State} of the {@link Door}.
	 *
	 * @return The {@link State} of the {@link Door}.
	 */
	State getState();

	/**
	 * Returns the {@link Lock} on the {@link Door}.
	 *
	 * @return The {@link Lock} on the {@link Door}.
	 */
	Lock getLock();

	/**
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	Room getInverseRoom(Room room);
}
