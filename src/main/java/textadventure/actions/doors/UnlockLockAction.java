package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;

public class UnlockLockAction implements Action
{

	/**
	 * Returns the identifier of the {@link UnlockLockAction}.
	 *
	 * @return The identifier of the {@link UnlockLockAction}.
	 */
	@Override public String getIdentifier()
	{
		return "unlock_door";
	}

	/**
	 * Returns the description of the {@link UnlockLockAction}.
	 *
	 * @return The description of the {@link UnlockLockAction}.
	 */
	@Override public String getDescription()
	{
		return "Unlock the door in front of you. To unlock a door you must first collect a matching key.";
	}

	/**
	 * Performs the {@link UnlockLockAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link UnlockLockAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		throw new UnsupportedOperationException();
	}
}
