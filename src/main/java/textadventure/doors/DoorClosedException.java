package textadventure.doors;

/**
 * Thrown when a {@link Character} attempts to use a {@link Door.State#CLOSED} {@link Door}.
 */
public class DoorClosedException extends DoorException
{

	/**
	 * Creates a new {@link DoorClosedException}.
	 *
	 * @param door The {@link Door} that was {@link Door.State#CLOSED}.
	 */
	public DoorClosedException(Door door)
	{
		super(door);
	}
}
