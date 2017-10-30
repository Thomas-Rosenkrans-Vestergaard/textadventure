package textadventure.rooms;

import textadventure.GameException;

public class UnknownRoomException extends GameException
{

	private Coordinate coordinate;

	public UnknownRoomException(Coordinate coordinate)
	{
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate()
	{
		return this.coordinate;
	}
}
