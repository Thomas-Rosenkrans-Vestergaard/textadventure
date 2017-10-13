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
	public LockInspectAction(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the lock to gather information.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		game.getUserInterface().onLockInspect(game, lock, player);
	}
}
