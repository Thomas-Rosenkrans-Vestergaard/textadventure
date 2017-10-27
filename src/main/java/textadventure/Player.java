package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;

/**
 * Object that can control {@link Character}s and perform {@link Action}s.
 */
public interface Player
{

	/**
	 * Returns the {@link Character} being played by the {@link Player}.
	 *
	 * @return The {@link Character} being played by the {@link Player}.
	 */
	Character getCharacter();

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param response The selectIndices to use for returning an appropriate {@link Action}.
	 */
	void takeTurn(Game game, ActionRequestCallback response);
}
