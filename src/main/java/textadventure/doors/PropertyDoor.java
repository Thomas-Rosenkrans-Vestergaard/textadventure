package textadventure.doors;

import textadventure.AbstractPropertyContainer;
import textadventure.lock.Lock;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;

public abstract class PropertyDoor extends AbstractPropertyContainer
{

	private Door door;

	public PropertyDoor(Door door)
	{
		this.door = door;

		putAction(new OpenDoorAction(door));
		putAction(new CloseDoorAction(door));
		putAction(new UseDoorAction(door));
		putAction(new InspectDoorAction(door));
		putAction(new LockLockAction(door.getLock()));
		putAction(new UnlockLockAction(door.getLock()));

		putProperty(door.getLock());
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
}
