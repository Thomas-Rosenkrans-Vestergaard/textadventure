package textadventure.doors;

import textadventure.AbstractPropertyContainer;
import textadventure.Direction;
import textadventure.lock.*;
import textadventure.rooms.Room;

/**
 * The default implementation of the {@link Door} interface.
 */
public class DefaultDoor extends AbstractPropertyContainer implements Door
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
	 * The {@link Direction} of the {@link Door} in the path <code>a</code> -> <code>b</code>.
	 */
	private Direction direction;

	/**
	 * The {@link Room} on one side of the {@link Door}.
	 */
	private Room roomA;

	/**
	 * The {@link Room} on the other side of the {@link Door}.
	 */
	private Room roomB;

	/**
	 * Creates a new {@link DefaultDoor}.
	 *
	 * @param state     The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock      The {@link Lock} placed on the {@link Door}.
	 * @param direction The {@link Direction} of the {@link Door} from <code>roomA</code> to <code>roomB</code>.
	 * @param roomA     <code>roomA</code>.
	 * @param roomB     <code>roomB</code>.
	 */
	public DefaultDoor(State state, Lock lock, Direction direction, Room roomA, Room roomB)
	{
		this.lock = lock;
		this.state = state;
		this.roomA = roomA;
		this.roomB = roomB;
		this.direction = direction;

		addAction(new OpenDoorAction(this));
		addAction(new CloseDoorAction(this));
		addAction(new EnterDoorAction(this));
		addAction(new InspectDoorAction(this));

		addProperty(lock);
	}

	/**
	 * Returns the name of the property.
	 *
	 * @return The name of the property.
	 */
	@Override public String getPropertyName()
	{
		return String.format("%sern_door", direction.name().toLowerCase());
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
		if (lock.getState() == Lock.State.LOCKED) return false;
		this.state = state;
		return true;
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
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 *
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	@Override public Room getInverseRoom(Room room)
	{
		if (room == roomA) {
			return roomB;
		}

		if (room == roomB) {
			return roomA;
		}

		return null;
	}

	/**
	 * Returns one of the {@link Room}s.
	 *
	 * @return One of the {@link Room}s.
	 */
	@Override public Room getRoomA()
	{
		return roomA;
	}

	/**
	 * Returns the other {@link Room}.
	 *
	 * @return The other {@link Room}.
	 */
	@Override public Room getRoomB()
	{
		return roomB;
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
		if (room == roomA) {
			return direction;
		}

		if (room == roomB) {
			return direction.getInverse();
		}

		return null;
	}
}
