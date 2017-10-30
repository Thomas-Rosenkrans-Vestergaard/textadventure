package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Action;
import textadventure.characters.Character;

/**
 * Object that {@link Character}s can perform {@link Action}s upon.
 */
public interface Property
{

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name   The name of the {@link Action}.
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
	 * Returns an {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 *
	 * @return The {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 */
	ImmutableMap<String, Action> getActions();
}
