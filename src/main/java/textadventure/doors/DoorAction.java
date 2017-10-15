package textadventure.doors;

import textadventure.Action;

public abstract class DoorAction implements Action
{

	/**
	 * The {@link Door} the {@link DoorAction} is performed upon.
	 */
	protected final Door door;

	/**
	 * Creates a new {@link DoorAction}.
	 *
	 * @param door The {@link Door} the {@link DoorAction} is performed upon.
	 */
	public DoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the {@link Door} the {@link DoorAction} is performed upon.
	 *
	 * @return The {@link Door} the {@link DoorAction} is performed upon.
	 */
	public Door getDoor()
	{
		return this.door;
	}
}
