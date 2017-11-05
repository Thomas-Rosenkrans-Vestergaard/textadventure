package textadventure.doors;

import textadventure.BaseProperty;
import textadventure.lock.Lock;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;
import textadventure.rooms.Room;

/**
 * Represents an intersection between a {@link textadventure.Property} and a {@link Door}.
 */
public abstract class PropertyDoor extends BaseProperty implements Door
{

	/**
	 * The internal {@link Door} instance.
	 */
	private Door door;

	/**
	 * Creates a new {@link PropertyDoor}.
	 *
	 * @param door The internal {@link Door} instance.
	 */
	public PropertyDoor(Door door)
	{
		this.door = door;

		Lock lock = getLock();

		putActionFactory(CloseDoorAction.class, () -> new CloseDoorAction(this));
		putActionFactory(OpenDoorAction.class, () -> new OpenDoorAction(this));
		putActionFactory(InspectDoorAction.class, () -> new InspectDoorAction(this));
		putActionFactory(UseDoorAction.class, () -> new UseDoorAction(this));

		putActionFactory(LockLockAction.class, () -> new LockLockAction(lock));
		putActionFactory(UnlockLockAction.class, () -> new UnlockLockAction(lock));
	}

	/**
	 * Opens the {@link Door}.
	 *
	 * @throws DoorAlreadyOpenException When the {@link Door} is already {@link Door.State#OPEN}.
	 * @throws DoorLockedException      When the {@link Door} is {@link Lock.State#LOCKED}.
	 */
	public void open() throws DoorAlreadyOpenException, DoorLockedException
	{
		door.open();
	}

	/**
	 * Closes the {@link Door}.
	 *
	 * @throws DoorAlreadyClosedException When the {@link Door} is already {@link Door.State#CLOSED}.
	 * @throws DoorLockedException        When the {@link Door} is {@link Lock.State#LOCKED}.
	 */
	public void close() throws DoorAlreadyClosedException, DoorLockedException
	{
		door.close();
	}

	/**
	 * Returns the {@link textadventure.doors.Door.State} of the {@link Door}.
	 *
	 * @return The {@link textadventure.doors.Door.State} of the {@link Door}.
	 */
	public Door.State getState()
	{
		return door.getState();
	}

	/**
	 * Returns the {@link Lock} on the {@link Door}.
	 *
	 * @return The {@link Lock} on the {@link Door}.
	 */
	public Lock getLock()
	{
		return door.getLock();
	}

	/**
	 * Returns one of the {@link Room}s the {@link Door} connects.
	 *
	 * @return One of the {@link Room}s the {@link Door} connects.
	 */
	@Override public Room getRoomA()
	{
		return door.getRoomA();
	}

	/**
	 * Returns the other {@link Room}s the {@link Door} connects.
	 *
	 * @return The other {@link Room}s the {@link Door} connects.
	 */
	@Override public Room getRoomB()
	{
		return door.getRoomB();
	}

	/**
	 * Sets one of the {@link Room}s the {@link Door} connects.
	 *
	 * @param a One of the {@link Room}s the {@link Door} connects.
	 */
	@Override public void setRoomA(Room a)
	{
		door.setRoomA(a);
	}

	/**
	 * Sets the other {@link Room}s the {@link Door} connects.
	 *
	 * @param b The other {@link Room}s the {@link Door} connects.
	 */
	@Override public void setRoomB(Room b)
	{
		door.setRoomB(b);
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
		return door.getInverseRoom(room);
	}
}
