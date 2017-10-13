package textadventure.doors;

import textadventure.Direction;
import textadventure.rooms.Room;
import textadventure.lock.Lock;

/**
 * Represents a {@link Door} that is blocked by some {@link Obstruction}. Before being able
 * to pass the {@link BlockedDoor}, the {@link Obstruction} blocking the door must first be removed.
 */
public class BlockedDoor extends DefaultDoor
{

	/**
	 * The {@link Obstruction} blocking the {@link BlockedDoor}.
	 */
	private Obstruction obstruction;

	/**
	 * Creates a new {@link DefaultDoor}.
	 *
	 * @param state       The {@link Door.State} of the {@link BlockedDoor}.
	 * @param lock        The {@link Lock} on the {@link BlockedDoor}.
	 * @param roomA       The {@link Room} on one side of the {@link Door}.
	 * @param roomB       The {@link Room} on the other side of the {@link Door}.
	 * @param obstruction The {@link Obstruction} blocking the {@link BlockedDoor}.
	 */
	public BlockedDoor(State state, Lock lock, Direction direction, Room roomA, Room roomB, Obstruction obstruction)
	{
		super(state, lock, direction, roomA, roomB);
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
