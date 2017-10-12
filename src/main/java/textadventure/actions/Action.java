package textadventure.actions;

import textadventure.Game;
import textadventure.Player;

public interface Action
{

	/**
	 * Returns the identifier of the {@link Action}.
	 *
	 * @return The identifier of the {@link Action}.
	 */
	String getIdentifier();

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	String getDescription();

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	void perform(Game game, Focusable focus, Player player) throws ActionException;
}
