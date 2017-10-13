package textadventure.openable.doors;

import textadventure.AbstractPropertyContainer;
import textadventure.Direction;
import textadventure.lock.*;
import textadventure.openable.*;
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
	 * @param state     The {@link textadventure.openable.doors.Door.State} that the {@link Door} is in.
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

		addAction("open", "Opens the door, provided that the door is unlocked.", new OpenableOpenAction(this));
		addAction("close", "Closes the door, provided that the door is unlocked.", new OpenableCloseAction(this));
		addAction("enter", "Use the door to enter the next room, provided that the door is open.", new DoorEnterAction(this));
		addAction("inspect", "Inspects the door to learn new information.", new DoorInspectAction(this));

		addProperty("lock", lock);
	}

	/**
	 * Returns the {@link Lock} on the {@link Door}.
	 *
	 * @return The {@link Lock} on the {@link Door}.
	 */
	@Override
	public Lock getLock()
	{
		return lock;
	}

	/**
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	@Override
	public Room getInverseRoom(Room room)
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
	@Override
	public Room getRoomA()
	{
		return roomA;
	}

	/**
	 * Returns the other {@link Room}.
	 *
	 * @return The other {@link Room}.
	 */
	@Override
	public Room getRoomB()
	{
		return roomB;
	}

	/**
	 * Returns the {@link Direction} of the {@link Door} in the provided {@link Room}.
	 *
	 * @param room The {@link Room} to use as perspective.
	 * @return The {@link Direction} of the {@link Door} in the provided {@link Room}. Returns <code>null</code> when
	 * the {@link Direction} could not be found, or the provided {@link Room} is unknown to the {@link Door}.
	 */
	@Override
	public Direction getDirection(Room room)
	{
		if (room == roomA) {
			return direction;
		}

		if (room == roomB) {
			return direction.getInverse();
		}

		return null;
	}

	/**
	 * Returns the {@link State} of the {@link Openable} object.
	 *
	 * @return The {@link State} of the {@link Openable} object.
	 */
	@Override
	public State getOpenableState()
	{
		return state;
	}

	/**
	 * Opens the {@link Openable} object.
	 *
	 * @throws OpenableAlreadyOpenException When the {@link Openable} object is already open.
	 * @throws CannotOpenException          When the {@link Openable} object cannot open.
	 */
	@Override
	public void open() throws OpenableAlreadyOpenException, CannotOpenException
	{
		if (state == State.OPEN) {
			throw new OpenableAlreadyOpenException();
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new CannotOpenException();
		}

		this.state = State.OPEN;
	}

	/**
	 * Closes the {@link Openable} object.
	 *
	 * @throws OpenableAlreadyClosedException When the {@link Openable} object is already closed.
	 * @throws CannotCloseException           When the {@link Openable} object cannot close.
	 */
	@Override
	public void close() throws OpenableAlreadyClosedException, CannotCloseException
	{
		if (state == State.CLOSED) {
			throw new OpenableAlreadyClosedException();
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new CannotCloseException();
		}

		this.state = State.CLOSED;
	}

	/**
	 * Returns a description of the {@link textadventure.rooms.RoomFeature}.
	 *
	 * @return The description of the {@link textadventure.rooms.RoomFeature}.
	 */
	@Override
	public String getRoomFeatureDescription()
	{
		return "There is a door in the room";
	}
}
