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
		CLOSED
	}

	/**
	 * Open the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws ActionException When the {@link Door} cannot be opened.
	 */
	void open(Game game, Player player) throws ActionException;

	/**
	 * Closes the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws ActionException When the {@link Door} cannot be closed.
	 */
	void close(Game game, Player player) throws ActionException;

	/**
	 * Use the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws ActionException When the {@link Door} cannot be used.
	 */
	void use(Game game, Player player) throws ActionException;

	/**
	 * Inspect the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws ActionException When the {@link Door} cannot be inspected.
	 */
	void inspect(Game game, Player player) throws ActionException;

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
