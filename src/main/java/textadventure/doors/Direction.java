package textadventure.doors;

public enum Direction
{
	NORTH,
	SOUTH,
	EAST,
	WEST;

	public Direction getInverse()
	{
		if (this == NORTH)
			return SOUTH;

		if (this == SOUTH)
			return NORTH;

		if (this == EAST)
			return WEST;

		if (this == WEST)
			return EAST;

		throw new UnsupportedOperationException();
	}
}
