package textadventure.doors;

import textadventure.actions.AbstractAction;
import textadventure.actions.Action;

/**
 * Abstract class for {@link Action}s performed upon a {@link Door}.
 */
public abstract class DoorAction extends AbstractAction
{

	/**
	 * The {@link Door} the {@link DoorAction} is performed upon.
	 */
	private final Door door;

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
