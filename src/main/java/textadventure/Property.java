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
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	void putAction(Action action);

	/**
	 * Returns the {@link Action} with the provided name.
	 *
	 * @return The {@link Action} with the provided name.
	 */
	<T extends Action> T getAction(Class<T> type) throws UnknownActionException;

	<T extends Action> boolean hasAction(Class<T> type);

	/**
	 * Returns an {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 *
	 * @return The {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 */
	ImmutableMap<Class<? extends Action>, Action> getActions();
}
