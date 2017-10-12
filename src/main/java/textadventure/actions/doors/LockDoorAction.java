package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;

public class LockDoorAction implements Action
{

	/**
	 * Returns the identifier of the {@link LockDoorAction}.
	 *
	 * @return The identifier of the {@link LockDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "lock_door";
	}

	/**
	 * Returns the description of the {@link LockDoorAction}.
	 *
	 * @return The description of the {@link LockDoorAction}.
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
		throw new UnsupportedOperationException();
	}
}
