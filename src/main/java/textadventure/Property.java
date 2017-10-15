package textadventure;

import java.util.stream.Stream;

public interface Property
{

	/**
	 * Returns the name of the {@link Property}.
	 *
	 * @return The name of the {@link Property}.
	 */
	/*String getPropertyName();*/

	/**
	 * Returns the description of the {@link Property}.
	 *
	 * @return The description of the {@link Property}.
	 */
	/*String getPropertyDescription();*/

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name        The name of the {@link Action}. Used when invoking the {@link Action}.
	 * @param description The description of the {@link Action}.
	 * @param action      The {@link Action} to add to the {@link Property}.
	 */
	void addAction(String name, String description, Action action);

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
