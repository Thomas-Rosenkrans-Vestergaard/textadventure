package textadventure;

import textadventure.rooms.Room;

/**
 * Represents the directions a player can travel between {@link Room}s.
 */
public enum Direction
{
	NORTH,
	SOUTH,
	EAST,
	WEST;

	/**
	 * Returns the inverse of the {@link Direction}.
	 *
	 * @return The inverse of the {@link Direction}.
	 */
	public Direction getInverse()
	{
		if (this.equals(Direction.NORTH)) {
			return Direction.SOUTH;
		}

		if (this.equals(Direction.SOUTH)) {
			return Direction.NORTH;
		}

		if (this.equals(Direction.EAST)) {
			return Direction.WEST;
		}

		if (this.equals(Direction.WEST)) {
			return Direction.EAST;
		}

		throw new IllegalStateException();
	}
}
