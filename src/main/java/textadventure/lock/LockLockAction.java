package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.ui.UI;

public class LockLockAction implements Action
{

	/**
	 * The {@link Lock} to lock.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockLockAction}.
	 *
	 * @param lock The {@link Lock} to lock.
	 */
	public LockLockAction(Lock lock)
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
		return "lock";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Locks the door.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		Lock.State state         = lock.getState();
		UI         userInterface = game.getUI();

		if (state == Lock.State.LOCKED) {
			userInterface.onLockAlreadyLocked(game, lock, player);
			return;
		}

		if (state == Lock.State.UNLOCKED) {
			throw new UnsupportedOperationException();
		}

		throw new IllegalStateException();
	}
}
