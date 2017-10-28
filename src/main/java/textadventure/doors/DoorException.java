package textadventure.doors;

import textadventure.GameException;

/**
 * Thrown when exception occurs related to {@link Door}s.
 */
public class DoorException extends GameException
{

	/**
	 * The {@link Door} that caused the {@link DoorException}.
	 */
	private Door door;

	/**
	 * Creates a new {@link DoorException}.
	 *
	 * @param door The {@link Door} that caused the {@link DoorException}.
	 */
	public DoorException(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the {@link Door} that caused the {@link DoorException}.
	 *
	 * @return The {@link Door} that caused the {@link DoorException}.
	 */
	public Door getDoor()
	{
		return this.door;
	}
}
