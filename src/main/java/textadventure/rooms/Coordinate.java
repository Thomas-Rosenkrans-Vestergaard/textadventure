package textadventure.rooms;

/**
 * Represents a {@link Coordinate} of a {@link Room}.
 */
public class Coordinate
{

	/**
	 * The position on the x-axis of the {@link Coordinate}
	 */
	private int x;

	/**
	 * The position on the y-axis of the {@link Coordinate}
	 */
	private int y;

	/**
	 * Creates a new {@link Coordinate}.
	 *
	 * @param x The x-value of the {@link Coordinate}.
	 * @param y The y-value of the {@link Coordinate}.
	 */
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns a new {@link Coordinate} with x and y as parameters.
	 *
	 * @param x The x-value of the {@link Coordinate}.
	 * @param y The y-value of the {@link Coordinate}.
	 * @return A new {@link Coordinate} with x and y as parameters.
	 */
	public static Coordinate of(int x, int y)
	{
		return new Coordinate(x, y);
	}

	/**
	 * Returns the x-value of the {@link Coordinate};
	 *
	 * @return the x-value of the {@link Coordinate};
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Returns the y-value of the {@link Coordinate};
	 *
	 * @return the y-value of the {@link Coordinate};
	 */
	public int getY()
	{
		return this.y;
	}

	/**
	 * Returns a string representation of the {@link Coordinate}.
	 *
	 * @return The string representation of the {@link Coordinate}
	 */
	@Override public String toString()
	{
		return String.format("(%d,%d)", x, y);
	}

	@Override public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Coordinate that = (Coordinate) o;

		if (x != that.x) return false;
		return y == that.y;
	}

	@Override public int hashCode()
	{
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
