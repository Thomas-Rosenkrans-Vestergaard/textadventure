package textadventure.actions;

import textadventure.characters.Character;

/**
 * Used to request the next {@link Action} for some {@link Character} by their {@link textadventure.Player} leader.
 * The {@link Action} chosen is returned asynchronous using the {@link ActionRequestCallback#respond(Action)}
 * method.
 */
@FunctionalInterface
public interface ActionRequestCallback
{

	/**
	 * Callback for the {@link textadventure.Player#onActionRequest(Character, ActionRequestCallback)}.
	 *
	 * @param action The {@link Action} the {@link textadventure.Player} wants to perform for the {@link Character}.
	 */
	void respond(Action action);
}
