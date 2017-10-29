package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.ui.GameInterface;

/**
 * Object that can control {@link Character}s and perform {@link Action}s.
 */
public interface Player
{

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	void onCharacter(Character character);

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param gameInterface The {@link GameInterface} instance.
	 * @param character     The {@link Character} that the {@link Player} should chose an {@link Action} for.
	 * @param callback      The callback to use for returning an {@link Action}.
	 */
	void playCharacter(GameInterface gameInterface, Character character, ActionRequestCallback callback);
}
