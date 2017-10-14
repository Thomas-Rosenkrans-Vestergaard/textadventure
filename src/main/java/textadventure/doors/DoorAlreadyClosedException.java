package textadventure.doors;

import textadventure.Action;
import textadventure.ActionException;
import textadventure.Game;
import textadventure.Player;

/**
 * Thrown when {@link Door#close(Game, Player)} is performed on a {@link textadventure.doors.Door.State#CLOSED}
 * {@link Door}.
 */
public class DoorAlreadyClosedException extends ActionException
{

	/**
	 * Creates a new {@link DoorAlreadyClosedException}.
	 *
	 * @param property The {@link Door} instance.
	 * @param action   The {@link Action} that was performed.
	 * @param player   The {@link Player} who performed the {@link Action}.
	 */
	public DoorAlreadyClosedException(Door property, Action action, Player player)
	{
		super(property, action, player);
	}
}
