package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.ActionFocusMismatchException;
import textadventure.actions.Focusable;
import textadventure.rooms.features.lock.Lock;
import textadventure.rooms.features.lock.Lockable;

public class LockLockAction implements Action
{

	/**
	 * Returns the identifier of the {@link LockLockAction}.
	 *
	 * @return The identifier of the {@link LockLockAction}.
	 */
	@Override public String getIdentifier()
	{
		return "lock_door";
	}

	/**
	 * Returns the description of the {@link LockLockAction}.
	 *
	 * @return The description of the {@link LockLockAction}.
	 */
	@Override public String getDescription()
	{
		return "Locks the focused door. This action requires the use of a matching key.";
	}

	/**
	 * Performs the {@link InspectLockAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link InspectLockAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		if (!(focus instanceof Lockable)) {
			throw new ActionFocusMismatchException(focus, this, player);
		}

		//Lockable lockable = (Lockable) focus;
		//Lock     lock     = lockable.getLock();

		throw new UnsupportedOperationException();

		//game.getUI().onLockableLock(game, lockable, player);
	}
}
