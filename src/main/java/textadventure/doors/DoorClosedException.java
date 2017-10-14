package textadventure.doors;

import textadventure.*;

/**
 * Thrown when {@link Door#use(Game, Player)} is performed on a {@link textadventure.doors.Door.State#CLOSED}
 * {@link Door}.
 */
public class DoorClosedException extends ActionException
{

	/**
	 * Creates a new {@link DoorClosedException}.
	 *
	 * @param property The {@link Door} instance.
	 * @param action   The {@link Action} that was performed.
	 * @param player   The {@link Player} who performed the {@link Action}.
	 */
	public DoorClosedException(Door property, Action action, Player player)
	{
		super(property, action, player);
	}
}
