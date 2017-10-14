package textadventure;

import textadventure.ui.Select;

@FunctionalInterface
public interface ActionResponse
{

	/**
	 * Used as a callback in the {@link Select}.
	 *
	 * @param action The {@link Action} to respond with.
	 * @throws ActionException When performing the {@link Action} goes wrong.
	 */
	void respond(Action action) throws ActionException;
}
