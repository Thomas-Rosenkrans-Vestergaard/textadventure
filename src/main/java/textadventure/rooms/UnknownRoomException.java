package textadventure.rooms;

import textadventure.GameException;

/**
 * Exception thrown when attempting to access a missing {@link Room}.
 */
public class UnknownRoomException extends GameException
{

	/**
	 * The {@link Coordinate} of the missing {@link Room}.
	 */
	private Coordinate coordinate;

	/**
	 * Creates a new {@link UnknownRoomException}.
	 *
	 * @param coordinate The {@link Coordinate} of the missing {@link Room}.
	 */
	public UnknownRoomException(Coordinate coordinate)
	{
		this.coordinate = coordinate;
	}

	/**
	 * Returns the {@link Coordinate} of the missing {@link Room}.
	 *
	 * @return The {@link Coordinate} of the missing {@link Room}.
	 */
	public Coordinate getCoordinate()
	{
		return this.coordinate;
	}
}
