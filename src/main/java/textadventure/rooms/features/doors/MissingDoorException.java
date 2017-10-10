package textadventure.rooms.features.doors;

import textadventure.Direction;
import textadventure.rooms.Room;

public class MissingDoorException
{
	private Room      room;
	private Direction direction;

	public MissingDoorException(Room room, Direction direction)
	{
		this.room = room;
		this.direction = direction;
	}

	public Room getRoom()
	{
		return this.room;
	}

	public Direction getDirection()
	{
		return this.direction;
	}
}
