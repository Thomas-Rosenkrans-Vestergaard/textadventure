package textadventure.actions;

import textadventure.ui.Select;

@FunctionalInterface
public interface ActionResponse
{

	/**
	 * Responds to a {@link Select} request.
	 *
	 * @param action    The {@link Action} to respond with.
	 * @param arguments The arguments to pass to the {@link Action}.
	 */
	void respond(Action action, String[] arguments);
}
