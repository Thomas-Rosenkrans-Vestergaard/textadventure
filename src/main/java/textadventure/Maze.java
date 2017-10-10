package textadventure;

public class Maze
{
	/**
	 * The rooms in the {@link Maze}.
	 */
	private RoomTracker roomConnections;

	/**
	 * The {@link StartingRoom} of the {@link Maze}. This room is the {@link Room} where the {@link Player}(s) start
	 * their adventure.
	 */
	private StartingRoom startingRoom;

	/**
	 * The {@link EndingRoom} of the {@link Maze}. This room is the {@link Room} where the {@link Player}(s) end their
	 * adventure.
	 */
	private EndingRoom endingRoom;

	/**
	 * Creates a new {@link Maze}.
	 *
	 * @param roomConnections The connections between the {@link Room}s in the {@link Maze}.
	 * @param startingRoom    The {@link Room} that the {@link Player}(s) starts in.
	 * @param endingRoom      The {@link Room} that the {@link Player}(s) ends in.
	 */
	public Maze(RoomTracker roomConnections, StartingRoom startingRoom, EndingRoom endingRoom)
	{
		this.roomConnections = roomConnections;
		this.startingRoom = startingRoom;
		this.endingRoom = endingRoom;
	}

	/**
	 * Returns the {@link RoomTracker} tracking the connections between the {@link Room}s in the
	 * {@link Maze}.
	 *
	 * @return The {@link RoomTracker} tracking the connections between the {@link Room}s in the
	 * {@link Maze}.
	 */
	public RoomTracker getRoomConnections()
	{
		return this.roomConnections;
	}

	/**
	 * Returns the {@link StartingRoom} the {@link Player}(s) starts in.
	 *
	 * @return The {@link StartingRoom} the {@link Player}(s) starts in.
	 */
	public StartingRoom getStartingRoom()
	{
		return this.startingRoom;
	}

	/**
	 * Returns the {@link EndingRoom} the {@link Player}(s) ends in.
	 *
	 * @return The {@link EndingRoom} the {@link Player}(s) ends in.
	 */
	public EndingRoom getEndingRoom()
	{
		return this.endingRoom;
	}
}
