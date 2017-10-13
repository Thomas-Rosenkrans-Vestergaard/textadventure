package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.openable.doors.Door;

/**
 * Inspects the {@link Lock} on a {@link Door}
 * revealing its <code>code</code>.
 */
public class LockInspectAction implements Action
{

	/**
	 * The {@link Lock} to inspect.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockInspectAction}.
	 *
	 * @param lock The {@link Lock} to inspect.
	 */
	LockInspectAction(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		String message = String.format("You inspect the lock to gather information. You discover that the lock is %s. On the lock is written the code %s.",
				player.getCharacter().getName(), lock.getState().name(),
				lock.getCode()
		);

		game.getUserInterface().write(message);
	}
}
