package textadventure.actions;

import textadventure.Game;
import textadventure.Player;

public interface ActionPerformCallback<T>
{

	/**
	 * Used by {@link Action}s to report their outcome.
	 *
	 * @param game   The {@link Game} being played.
	 * @param player The  The {@link Player} performing the {@link Action}.
	 * @param action The {@link Action} being performed.
	 */
	void send(Game game, Player player, T action);
}
