package textadventure.actions;

import textadventure.Character;

public interface ActionPerformCallback<T>
{

	/**
	 * Used by {@link Action}s to report their outcome.
	 *
	 * @param character The  The {@link Character} performing the {@link Action}.
	 * @param action    The {@link Action} being performed.
	 */
	void send(Character character, T action);
}
