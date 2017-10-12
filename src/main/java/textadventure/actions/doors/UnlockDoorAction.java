package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;

public class UnlockDoorAction implements Action
{

	/**
	 * Returns the identifier of the {@link UnlockDoorAction}.
	 *
	 * @return The identifier of the {@link UnlockDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "unlock_door";
	}

	/**
	 * Returns the description of the {@link UnlockDoorAction}.
	 *
	 * @return The description of the {@link UnlockDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Unlock the door in front of you. To unlock a door you must first collect a matching key.";
	}

	/**
	 * Performs the {@link UnlockDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link UnlockDoorAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		throw new UnsupportedOperationException();
	}
}
