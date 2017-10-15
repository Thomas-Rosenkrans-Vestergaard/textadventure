package textadventure;

import textadventure.ui.Select;

@FunctionalInterface
public interface ActionResponse
{

	/**
	 * Responds to a {@link Select} request.
	 *
	 * @param action The {@link Action} to respond with.
	 */
	void respond(Action action);
}
