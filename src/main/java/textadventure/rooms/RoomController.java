package textadventure.rooms;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

public class RoomController
{

	private HashMap<Coordinate, Room> rooms = new HashMap<>();

	public void add(Coordinate coordinate, Room room)
	{
		this.rooms.put(coordinate, room);
	}

	public Room get(Coordinate coordinate) throws UnknownRoomException
	{
		if (!rooms.containsKey(coordinate))
			throw new UnknownRoomException(coordinate);

		return this.rooms.get(coordinate);
	}

	public ImmutableMap<Coordinate, Room> getRooms()
	{
		return ImmutableMap.copyOf(rooms);
	}
}
