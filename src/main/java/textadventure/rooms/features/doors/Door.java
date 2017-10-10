package textadventure.rooms.features.doors;

import textadventure.rooms.features.RoomFeature;

/**
 * The {@link Door} represents an entrance to a new room.
 */
public interface Door extends RoomFeature
{

	/**
	 * The {@link Location} of the {@link Door} in the {@link textadventure.rooms.Room}.
	 */
	enum Location
	{
		NORTH,
		SOUTH,
		EAST,
		WEST,
	}

	/**
	 * The state of the {@link Door}. The {@link Door} can only have <code>4</code> states.
	 */
	enum State
	{
		/**
		 * The {@link Door} is open and can be used
		 */
		OPEN,

		/**
		 * The {@link Door} is closed and must first be opened before it can be used.
		 */
		CLOSED,

		/**
		 * The {@link Door} is locked and must first be unlocked and opened before it can be used.
		 */
		LOCKED,
	}

	/**
	 * Returns the description of the {@link Door}.
	 *
	 * @return The description of the {@link Door}.
	 */
	String getDescription();

	/**
	 * Returns the {@link Lock} located on the {@link Door}.
	 *
	 * @return The {@link Lock} located on the {@link Door}.
	 */
	Lock getLock();

	/**
	 * Returns the {@link State} of the {@link Door}.
	 *
	 * @return The {@link State} of the {@link Door}.
	 */
	State getState();
}
