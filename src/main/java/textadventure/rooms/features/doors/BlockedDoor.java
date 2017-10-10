package textadventure.rooms.features.doors;

/**
 * Represents a {@link Door} that is blocked by some {@link Obstruction}. Before being able
 * to pass the {@link BlockedDoor}, the {@link Obstruction} blocking the door must first be removed.
 */
public class BlockedDoor extends BaseDoor
{

	/**
	 * The {@link Obstruction} blocking the {@link BlockedDoor}.
	 */
	private Obstruction obstruction;

	/**
	 * Creates a new {@link BaseDoor}.
	 *
	 * @param description The description of the {@link BlockedDoor}.
	 * @param lock        The {@link Lock} on the {@link BlockedDoor}.
	 * @param state       The {@link textadventure.rooms.features.doors.Door.State} of the {@link BlockedDoor}.
	 * @param obstruction The {@link Obstruction} blocking the {@link BlockedDoor}.
	 */
	public BlockedDoor(String description, Lock lock, State state, Obstruction obstruction)
	{
		super(description, lock, state);
		this.obstruction = obstruction;
	}

	/**
	 * Returns the {@link Obstruction} blocking the {@link BlockedDoor}.
	 *
	 * @return The {@link Obstruction} blocking the {@link BlockedDoor}.
	 */
	public Obstruction getObstruction()
	{
		return this.obstruction;
	}
}
