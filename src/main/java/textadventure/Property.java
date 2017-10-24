package textadventure;

import textadventure.actions.Action;

import java.util.stream.Stream;

public interface Property
{

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name The name of the {@link Action}.
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	void addAction(String name, Action action);

	/**
	 * Returns the {@link Action} with the provided name.
	 *
	 * @param name The <code>name</code> of the {@link Action} to return.
	 * @return The {@link Action} with the provided <code>name</code>. Returns <code>null</code>
	 */
	Action getAction(String name);

	/**
	 * Returns a stream of the {@link Action}s available on the {@link Property}.
	 *
	 * @return Returns the stream of the {@link Action}s available on the {@link Property}.
	 */
	Stream<Action> getActions();
}
