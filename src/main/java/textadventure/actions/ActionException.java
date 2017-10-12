package textadventure.actions;

import textadventure.GameException;
import textadventure.Player;
import textadventure.rooms.Room;

/**
 * Thrown when an error occurs while performing an {@link Action}.
 */
public class ActionException extends GameException
{

	/**
	 * The {@link Focusable} object that the {@link Action} was performed on.
	 */
	private Focusable focusable;

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
	 * @param focusable The {@link Focusable} object that the {@link Action} was performed on.
	 * @param action    The {@link Action} that was attempted.
	 * @param player    The {@link Player} who attempted the {@link Action}.
	 * @param room      The {@link Room} the {@link Action} was performed in.
	 */
	public ActionException(Focusable focusable, Action action, Player player, Room room)
	{
		this.focusable = focusable;
		this.action = action;
		this.player = player;
		this.room = room;
	}

	/**
	 * Returns the {@link Focusable} object that the {@link Action} was performed on.
	 *
	 * @return The {@link Focusable} object that the {@link Action} was performed on.
	 */
	public Focusable getFocusable()
	{
		return this.focusable;
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
