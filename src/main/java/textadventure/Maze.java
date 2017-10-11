package textadventure;

import textadventure.rooms.EndingRoom;
import textadventure.rooms.Room;
import textadventure.rooms.RoomController;
import textadventure.rooms.StartingRoom;

public class Maze
{
	/**
	 * The rooms in the {@link Maze}.
	 */
	private RoomController roomConnections;

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
	 * @param rooms        The {@link Room}s in the {@link Maze}.
	 * @param startingRoom The {@link Room} that the {@link Player}(s) starts in.
	 * @param endingRoom   The {@link Room} that the {@link Player}(s) ends in.
	 */
	public Maze(StartingRoom startingRoom, EndingRoom endingRoom, Room... rooms)
	{
		this.startingRoom = startingRoom;
		this.endingRoom = endingRoom;
	}

	/**
	 * Returns the {@link RoomController} tracking the connections between the {@link Room}s in the
	 * {@link Maze}.
	 *
	 * @return The {@link RoomController} tracking the connections between the {@link Room}s in the
	 * {@link Maze}.
	 */
	public RoomController getRoomConnections()
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
