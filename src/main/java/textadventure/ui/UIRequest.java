package textadventure.ui;

import textadventure.actions.Action;
import textadventure.actions.ActionException;

public interface UIRequest
{

	/**
	 * Executes the {@link UIRequest}.
	 */
	void execute(UI ui);

	/**
	 * Responds to the {@link UIRequest} using the provided {@link Action}.
	 *
	 * @param result The {@link Action} to use as a response.
	 */
	void respond(Object result) throws ActionException;
}
