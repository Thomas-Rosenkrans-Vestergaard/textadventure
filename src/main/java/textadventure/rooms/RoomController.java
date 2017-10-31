package textadventure.rooms;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

/**
 * Stores {@link Room}s using {@link Coordinate}s.
 */
public class RoomController
{

	/**
	 * Creates a new {@link HashMap} containing {@link Coordinate}s and {@link Room}s in the {@link RoomController}.
	 */
	private HashMap<Coordinate, Room> rooms = new HashMap<>();

	/**
	 * Adds a {@link Room} with a {@link Coordinate} to the {@link HashMap} of the {@link RoomController}.
	 *
	 * @param coordinate The {@link Coordinate} where the {@link Room} is located.
	 * @param room       The {@link Room} with the given {@link Coordinate}
	 */
	public void add(Coordinate coordinate, Room room)
	{
		this.rooms.put(coordinate, room);
	}

	/**
	 * Returns the {@link Room} at the provided {@link Coordinate}.
	 *
	 * @param coordinate The {@link Coordinate} of the {@link Room} to return.
	 * @return The {@link Room} at the provided {@link Coordinate}.
	 * @throws UnknownRoomException When no {@link Room} at the provided {@link Coordinate}s exists.
	 */
	public Room get(Coordinate coordinate) throws UnknownRoomException
	{
		if (!rooms.containsKey(coordinate))
			throw new UnknownRoomException(coordinate);

		return this.rooms.get(coordinate);
	}

	/**
	 * Returns a copy of the {@link RoomController}s {@link HashMap}.
	 *
	 * @return A copy of the {@link RoomController}s {@link HashMap}.
	 */
	public ImmutableMap<Coordinate, Room> getRooms()
	{
		return ImmutableMap.copyOf(rooms);
	}
}
