package textadventure.actions;

import textadventure.Character;

@FunctionalInterface
public interface ActionRequestCallback
{

	/**
	 * Callback for the {@link textadventure.ui.GameInterface#onActionRequest(Character, ActionRequestCallback)}.
	 * method
	 *
	 * @param action    The {@link Action} to send with.
	 * @param arguments The arguments to pass to the {@link Action}.
	 */
	void respond(Action action, String[] arguments);
}
