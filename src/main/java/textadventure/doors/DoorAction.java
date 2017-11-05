package textadventure.doors;

import textadventure.actions.AbstractAction;
import textadventure.actions.Action;

/**
 * Abstract class for {@link Action}s performed upon a {@link Door}.
 */
public abstract class DoorAction extends AbstractAction
{

	/**
	 * The {@link PropertyDoor} the {@link DoorAction} is performed upon.
	 */
	private final PropertyDoor door;

	/**
	 * Creates a new {@link DoorAction}.
	 *
	 * @param door The {@link PropertyDoor} the {@link DoorAction} is performed upon.
	 */
	public DoorAction(PropertyDoor door)
	{
		this.door = door;
	}

	/**
	 * Returns the {@link PropertyDoor} the {@link DoorAction} is performed upon.
	 *
	 * @return The {@link PropertyDoor} the {@link DoorAction} is performed upon.
	 */
	public PropertyDoor getDoor()
	{
		return this.door;
	}
}
