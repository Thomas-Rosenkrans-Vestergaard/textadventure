package textadventure.actions;

import textadventure.Character;
import textadventure.Game;

public interface ActionPerformCallback<T>
{

	/**
	 * Used by {@link Action}s to report their outcome.
	 *
	 * @param game      The {@link Game} being played.
	 * @param character The  The {@link Character} performing the {@link Action}.
	 * @param action    The {@link Action} being performed.
	 */
	void send(Game game, Character character, T action);
}
