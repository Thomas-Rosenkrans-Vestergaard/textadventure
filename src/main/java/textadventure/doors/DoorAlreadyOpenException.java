package textadventure.doors;

import textadventure.*;

/**
 * Called when {@link Door#open(Game, Player)} is performed on an {@link textadventure.doors.Door.State#OPEN}
 * {@link Door}.
 */
public class DoorAlreadyOpenException extends ActionException
{

	/**
	 * Creates a new {@link DoorAlreadyOpenException}.
	 *
	 * @param property The {@link Door} instance.
	 * @param action   The {@link Action} that was performed.
	 * @param player   The {@link Player} who performed the {@link Action}.
	 */
	public DoorAlreadyOpenException(Door property, Action action, Player player)
	{
		super(property, action, player);
	}
}
