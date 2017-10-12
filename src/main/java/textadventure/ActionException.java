package textadventure;

import textadventure.rooms.Room;

/**
 * Thrown when an error occurs while performing an {@link Action}.
 */
public class ActionException extends GameException
{

	private Property property;

	/**
	 * The {@link Action} that was attempted.
	 */
	private Action action;

	/**
	 * The {@link Player} who attempted the {@link Action}.
	 */
	private Player player;

	/**
	 * The {@link Room} the {@link Action} was performed in.
	 */
	private Room room;

	/**
	 * Creates a new {@link ActionException}.
	 *
	 * @param property The {@link textadventure.Property} object that the {@link Action} was performed on.
	 * @param action   The {@link Action} that was attempted.
	 * @param player   The {@link Player} who attempted the {@link Action}.
	 * @param room     The {@link Room} the {@link Action} was performed in.
	 */
	public ActionException(Property property, Action action, Player player, Room room)
	{
		this.property = property;
		this.action = action;
		this.player = player;
		this.room = room;
	}


	public Property getProperty()
	{
		return this.property;
	}

	/**
	 * Returns the {@link Action} that was attempted.
	 *
	 * @return The {@link Action} that was attempted.
	 */
	public Action getAction()
	{
		return this.action;
	}

	/**
	 * Returns the {@link Player} who attempted the {@link Action}.
	 *
	 * @return The {@link Player} who attempted the {@link Action}.
	 */
	public Player getPlayer()
	{
		return this.player;
	}

	/**
	 * Returns the {@link Room} the {@link Action} was performed in.
	 *
	 * @return The {@link Room} the {@link Action} was performed in.
	 */
	public Room getRoom()
	{
		return this.room;
	}
}
