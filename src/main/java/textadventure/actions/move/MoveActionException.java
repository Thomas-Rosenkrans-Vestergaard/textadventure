package textadventure.actions.move;

import textadventure.Direction;
import textadventure.rooms.Room;
import textadventure.actions.ActionException;

public class MoveActionException extends ActionException
{

	/**
	 * The starting {@link Room}.
	 */
	private Room from;

	/**
	 * The {@link Direction} the direction that was attempted to move in.
	 */
	private Direction direction;

	/**
	 * Creates a new {@link MoveActionException}.
	 *
	 * @param from      The starting {@link Room}.
	 * @param direction The {@link Direction} the direction that was attempted to move in.
	 */
	public MoveActionException(Room from, Direction direction)
	{
		this.from = from;
		this.direction = direction;
	}

	/**
	 * Returns the starting {@link Room}.
	 *
	 * @return The starting {@link Room}.
	 */
	public Room getFrom()
	{
		return this.from;
	}

	/**
	 * Returns the {@link Direction} the direction that was attempted to move in.
	 *
	 * @return The {@link Direction} the direction that was attempted to move in.
	 */
	public Direction getDirection()
	{
		return this.direction;
	}
}
