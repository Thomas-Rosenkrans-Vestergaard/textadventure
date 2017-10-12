package textadventure.rooms.features.doors;

import textadventure.Direction;
import textadventure.actions.Focusable;
import textadventure.rooms.Room;
import textadventure.rooms.features.RoomFeature;

/**
 * The {@link Door} represents an entrance to a new room.
 */
public interface Door extends RoomFeature, Focusable
{

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

	/**
	 * Sets the {@link State} of the {@link Door}.
	 *
	 * @param state The {@link State} to set.
	 */
	boolean setState(State state);

	/**
	 * Returns one of the {@link Room}s.
	 *
	 * @return One of the {@link Room}s.
	 */
	Room getSideA();

	/**
	 * Returns the other {@link Room}.
	 *
	 * @return The other {@link Room}.
	 */
	Room getSideB();

	/**
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 *
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	Room getOtherSide(Room room);

	/**
	 * Returns the {@link Direction} of the {@link Door} in the provided {@link Room}.
	 *
	 * @param room The {@link Room} to use as perspective.
	 *
	 * @return The {@link Direction} of the {@link Door} in the provided {@link Room}. Returns <code>null</code> when
	 * the {@link Direction} could not be found, or the provided {@link Room} is unknown to the {@link Door}.
	 */
	Direction getDirection(Room room);
}
