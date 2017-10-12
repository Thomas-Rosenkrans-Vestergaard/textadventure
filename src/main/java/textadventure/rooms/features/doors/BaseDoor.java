package textadventure.rooms.features.doors;

import textadventure.Direction;
import textadventure.actions.*;
import textadventure.actions.doors.*;
import textadventure.rooms.Room;

/**
 * The default implementation of the {@link Door} interface.
 */
public class BaseDoor extends AbstractFocusable implements Door
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
		this.description = description;
		this.lock = lock;
		this.state = state;
		this.a = a;
		this.b = b;
		this.direction = direction;

		addAction(new OpenDoorAction());
		addAction(new CloseDoorAction());
		addAction(new UnlockDoorAction());
		addAction(new LockDoorAction());
		addAction(new MoveThroughDoorAction());
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
		return state;
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
	 * Returns one of the {@link Room}s.
	 *
	 * @return One of the {@link Room}s.
	 */
	@Override public Room getSideA()
	{
		return a;
	}

	/**
	 * Returns the other {@link Room}.
	 *
	 * @return The other {@link Room}.
	 */
	@Override public Room getSideB()
	{
		return b;
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

	/**
	 * Returns the identifier use the identify the {@link Focusable} object. This identifier used to focus on the
	 * {@link Focusable} object using the <code>focus</code> command.
	 *
	 * @return The identifier use the identify the {@link Focusable} object. This identifier used to focus on the
	 * {@link Focusable} object using the <code>focus</code> command.
	 */
	@Override public String getIdentifier()
	{
		return direction.name().toLowerCase() + "_door";
	}
}
