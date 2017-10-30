package textadventure.rooms;

public class Coordinate
{
	private int x;
	private int y;

	public static Coordinate of(int x, int y)
	{
		return new Coordinate(x, y);
	}

	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
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
