package textadventure.doors;

import textadventure.*;
import textadventure.lock.*;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;

/**
 * Base implementation of the {@link Door} interface.
 */
public class BaseDoor extends AbstractPropertyContainer implements Door
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
	 * Creates a new {@link BaseDoor} with the {@link OpenDoorAction}, {@link CloseDoorAction},
	 * {@link UseDoorAction}, {@link InspectDoorAction}, {@link LockLockAction} and {@link UnlockLockAction}.
	 *
	 * @param state The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock  The {@link Lock} placed on the {@link Door}.
	 * @param roomA The first room (<code>roomA</code>).
	 * @param roomB The second room (<code>roomB</code>).
	 * @param game  The {@link Game} instance.
	 * @return The newly created instance of {@link BaseDoor}.
	 */
	public static Door factory(State state, Lock lock, Room roomA, Room roomB, Game game)
	{

		BaseDoor      door          = new BaseDoor(state, lock, roomA, roomB);
		GameInterface gameInterface = game.getGameInterface();
		door.addAction("open", new OpenDoorAction(door, gameInterface::onDoorOpen));
		door.addAction("close", new CloseDoorAction(door, gameInterface::onDoorClose));
		door.addAction("use", new UseDoorAction(door, gameInterface::onDoorUse));
		door.addAction("inspect", new InspectDoorAction(door, gameInterface::onDoorInspect));
		door.addAction("lock", new LockLockAction(lock, gameInterface::onLockLock));
		door.addAction("unlock", new UnlockLockAction(lock, gameInterface::onLockUnlock));

		door.addProperty("lock", lock);

		return door;
	}

	/**
	 * Opens the {@link Door}.
	 *
	 * @throws DoorAlreadyOpenException When the {@link Door} is already open.
	 * @throws DoorLockedException      When the {@link Door} is locked.
	 */
	public void open() throws DoorAlreadyOpenException, DoorLockedException
	{
		if (state == State.OPEN) {
			throw new DoorAlreadyOpenException();
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new DoorLockedException();
		}

		this.state = State.OPEN;
	}

	/**
	 * Closes the {@link Door}.
	 *
	 * @throws DoorAlreadyClosedException When the {@link Door} is already closed.
	 * @throws DoorLockedException        When the {@link Door} is locked.
	 */
	public void close() throws DoorAlreadyClosedException, DoorLockedException
	{
		if (state == State.CLOSED) {
			throw new DoorAlreadyClosedException();
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new DoorLockedException();
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
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	@Override public Room getInverseRoom(Room room)
	{
		if (room == roomA) return roomB;
		if (room == roomB) return roomA;

		return null;
	}
}
