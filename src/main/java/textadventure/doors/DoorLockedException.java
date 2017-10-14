package textadventure.doors;

import textadventure.*;

/**
 * Throws {@link Door#open(Game, Player)} or {@link Door#close(Game, Player)} is performed on a
 * {@link Door.State#CLOSED} {@link Door}.
 */
public class DoorLockedException extends ActionException
{

	/**
	 * Creates a new {@link DoorLockedException}.
	 *
	 * @param property The {@link Door} instance.
	 * @param action   The {@link Action} that was performed.
	 * @param player   The {@link Player} who performed the {@link Action}.
	 */
	public DoorLockedException(Door property, Action action, Player player)
	{
		super(property, action, player);
	}
}
