package textadventure.doors;

/**
 * Thrown by {@link Door}s when a {@link Character} attempted to {@link Door#open()} a {@link Door.State#OPEN} {@link Door}.
 */
public class DoorAlreadyOpenException extends DoorException
{

	/**
	 * Creates a new {@link DoorAlreadyOpenException}.
	 *
	 * @param door The {@link Door} that was already {@link Door.State#OPEN}.
	 */
	public DoorAlreadyOpenException(Door door)
	{
		super(door);
	}
}
