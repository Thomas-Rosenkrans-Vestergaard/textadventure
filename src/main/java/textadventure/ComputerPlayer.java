package textadventure;

import textadventure.actions.ActionRequestCallback;
import textadventure.ui.GameInterface;

public class ComputerPlayer implements Player
{

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	@Override public void onCharacter(Character character)
	{

	}

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param gameInterface The {@link GameInterface} instance.
	 * @param character     The {@link Character} that the {@link Player} should chose an {@link textadventure.actions.Action} for.
	 * @param callback      The callback to use for returning an {@link textadventure.actions.Action}.
	 */
	@Override
	public void playCharacter(GameInterface gameInterface, Character character, ActionRequestCallback callback)
	{

	}
}
