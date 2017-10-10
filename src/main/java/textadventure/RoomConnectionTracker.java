package textadventure;

import textadventure.exception.UnknownRoomException;

import java.util.HashMap;
import java.util.Map;

/**
 * Object that tracks connections between {@link Room}s.
 */
public class RoomConnectionTracker
{

	/**
	 * The connections defined in the {@link RoomConnectionTracker}. The map contains a {@link Room} and its
	 * connections. The connections of the {@link Room} is represented as a {@link HashMap} where a {@link Room} is
	 * mapped to the {@link Direction} needed to arrive at the connection.
	 */
	private HashMap<Room, HashMap<Direction, Room>> connections = new HashMap<Room, HashMap<Direction, Room>>();

	/**
	 * Adds a new {@link Room} to the {@link RoomConnectionTracker} collection.
	 *
	 * @param room The {@link Room} to add to the {@link RoomConnectionTracker}.
	 */
	public void addRoom(Room room)
	{
		connections.put(room, new HashMap<Direction, Room>());
	}

	/**
	 * Returns the number of {@link Room}s in the {@link RoomConnectionTracker}.
	 *
	 * @return The number of {@link Room}s in the {@link RoomConnectionTracker}.
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
	 * @return The room in the {@link Direction}.
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
	 * <code>b</code>.
	 *
	 * @param a         The first room.
	 * @param b         The second room.
	 * @param direction The direction of the connection from <code>a</code> to <code>b</code>.
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
	}

	/**
	 * Adds a connection between room <code>a</> and <code>b</code>. The connection is added from <code>a</code> to
	 * <code>b</code> and from <code>b</code> to <code>a</code>.
	 *
	 * @param a         The first room.
	 * @param b         The second room.
	 * @param direction The direction of the connection from <code>a</code> to <code>b</code>. The direction from
	 *                  <code>b</code> to <code>a</code> is the opposite of the provided <code>direction</code>.
	 *
	 * @throws UnknownRoomException When {@link Room} <code>a</code> or <code>b</code> hasn't yet been added.
	 */
	public void addMutualConnection(Room a, Room b, Direction direction) throws UnknownRoomException
	{
		if (!connections.containsKey(a))
			throw new UnknownRoomException(a);

		if (!connections.containsKey(b))
			throw new UnknownRoomException(b);

		connections.get(a).put(direction, b);
		connections.get(b).put(direction.getInverse(), a);
	}
}
