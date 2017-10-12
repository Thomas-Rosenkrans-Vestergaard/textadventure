package textadventure.rooms.features.doors;

import textadventure.Direction;
import textadventure.rooms.Room;
import textadventure.rooms.features.lock.Lock;

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
	 * @param a           The {@link Room} on one side of the {@link Door}.
	 * @param b           The {@link Room} on the other side of the {@link Door}.
	 */
	public BlockedDoor(
			String description, Lock lock, State state, Obstruction obstruction, Room a, Room b, Direction
			direction)
	{
		super(description, lock, state, a, b, direction);
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
