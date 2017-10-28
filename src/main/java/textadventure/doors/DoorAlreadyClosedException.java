package textadventure.doors;

/**
 * Thrown by {@link Door}s when a {@link Character} attempted to {@link Door#close()} a {@link Door.State#CLOSED}
 * {@link Door}.
 */
public class DoorAlreadyClosedException extends DoorException
{

	/**
	 * Creates a new {@link DoorAlreadyClosedException}.
	 *
	 * @param door The {@link Door} that was already {@link Door.State#CLOSED}.
	 */
	public DoorAlreadyClosedException(Door door)
	{
		super(door);
	}
}
