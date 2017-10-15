package textadventure.actions;

import textadventure.Game;
import textadventure.Player;

public interface Action
{

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	void perform(Game game, Player player);
}
