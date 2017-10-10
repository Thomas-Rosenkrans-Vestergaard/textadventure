package textadventure.rooms;

import textadventure.Direction;
import textadventure.UnknownRoomException;
import textadventure.rooms.features.doors.Door;

import java.util.HashMap;
import java.util.Map;

/**
 * Object that tracks connections between {@link Room}s.
 */
public class RoomController
{

	/**
	 * The connections defined in the {@link RoomController}. The map contains a {@link Room} and its
	 * connections. The connections of the {@link Room} is represented as a {@link HashMap} where a {@link Room} is
	 * mapped to the {@link Direction} needed to arrive at the connection.
	 */
	private HashMap<Room, HashMap<Direction, Room>> connections = new HashMap<>();

	/**
	 * Adds a new {@link Room} to the {@link RoomController} collection.
	 *
	 * @param room The {@link Room} to add to the {@link RoomController}.
	 */
	public void addRoom(Room room)
	{
		connections.put(room, new HashMap<>());
	}

	/**
	 * Returns the number of {@link Room}s in the {@link RoomController}.
	 *
	 * @return The number of {@link Room}s in the {@link RoomController}.
	 */
	public int getRoomCount()
	{
		return connections.size();
	}

	/**
	 * Returns the {@link Room} next to the provided {@link Room} in the provided {@link Direction}.
	 *
	 * @param room      The starting {@link Room}.
	 * @param direction The {@link Direction} to look in,
	 *
	 * @return The room in the {@link Direction}. Returns <code>null</code> when the {@link Room} could not be found.
	 */
	public Room getRoom(Room room, Direction direction)
	{
		if (!connections.containsKey(room))
			return null;

		for (Map.Entry<Direction, Room> entry : connections.get(room).entrySet()) {
			if (entry.getKey() == direction) {
				return entry.getValue();
			}
		}

		return null;
	}

	/**
	 * Checks if the provided {@link Room} has a connection in the provided {@link Direction}.
	 *
	 * @param room      The {@link Room}.
	 * @param direction The {@link Direction}.
	 *
	 * @return True if the provided {@link Room} has a connection in the provided {@link Direction}.
	 */
	public boolean hasConnection(Room room, Direction direction)
	{
		if (!connections.containsKey(room)) {
			return false;
		}

		System.out.println(room.getName());
		System.out.println(direction);
		for (Map.Entry<Direction, Room> entry : connections.get(room).entrySet()) {
			if (entry.getKey().equals(direction)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the {@link Direction} needed to go from room a to room b.
	 *
	 * @param a The starting room.
	 * @param b The ending room.
	 *
	 * @return The direction between the rooms in the a to b. Returns null if room a doesn't connect to room b.
	 */
	public Direction getDirection(Room a, Room b)
	{
		if (!connections.containsKey(a))
			return null;

		for (HashMap.Entry<Direction, Room> entry : connections.get(a).entrySet())
			if (entry.getValue().equals(b))
				return entry.getKey();

		return null;
	}

	/**
	 * Adds a connection between room <code>a</> and <code>b</code>. The connection is added from <code>a</code> to
	 * <code>b</code> and from <code>b</code> to <code>a</code>.
	 *
	 * @param a         The first {@link Room}.
	 * @param b         The second {@link Room}.
	 * @param direction The direction of the connection from <code>a</code> to <code>b</code>. The direction from
	 *                  <code>b</code> to <code>a</code> is the opposite of the provided <code>direction</code>.
	 *
	 * @throws UnknownRoomException When {@link Room} <code>a</code> or <code>b</code> hasn't yet been added.
	 */
	public void addConnection(Room a, Room b, Direction direction) throws UnknownRoomException
	{
		if (!connections.containsKey(a))
			throw new UnknownRoomException(a);

		if (!connections.containsKey(b))
			throw new UnknownRoomException(b);

		connections.get(a).put(direction, b);
		connections.get(b).put(direction.getInverse(), a);
	}
}
