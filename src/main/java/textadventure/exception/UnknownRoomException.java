package textadventure.exception;

import textadventure.Room;

public class UnknownRoomException extends GameException
{

	/**
	 * The unknown {@link Room}.
	 */
	private Room unknownRoom;

	/**
	 * Creates a new {@link UnknownRoomException}.
	 *
	 * @param unknownRoom The unknown {@link Room}.
	 */
	public UnknownRoomException(Room unknownRoom)
	{
		this.unknownRoom = unknownRoom;
	}

	/**
	 * Returns the unknown {@link Room} that caused the exception.
	 *
	 * @return The unknown {@link Room} that caused the exception.
	 */
	public Room getUnknownRoom()
	{
		return this.unknownRoom;
	}
}
