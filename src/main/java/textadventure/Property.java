package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Action;
import textadventure.actions.ActionFactory;
import textadventure.characters.Character;

/**
 * Object that {@link Character}s can perform {@link Action}s upon.
 */
public interface Property
{

	/**
	 * Inserts a new {@link ActionFactory} for a provided {@link Action} type.
	 *
	 * @param action  The type of {@link Action} to insert the {@link ActionFactory} for.
	 * @param factory The {@link ActionFactory}.
	 */
	void putActionFactory(Class<? extends Action> action, ActionFactory factory);

	/**
	 * Checks whether or not the {@link Property} contains an {@link ActionFactory} that can produce instances of the
	 * provided type of {@link Action}.
	 *
	 * @param action The type of {@link Action} to check for.
	 * @return True if the {@link Property} has an {@link ActionFactory} that can produce instance of the provided
	 * type of {@link Action}. Returns false if no such {@link ActionFactory} exists.
	 */
	boolean hasActionFactory(Class<? extends Action> action);

	/**
	 * Creates a new {@link Action} from the provided type.
	 *
	 * @param action The type of {@link Action} to create.
	 * @return The newly created {@link Action}.
	 * @throws UnknownActionException When no {@link ActionFactory} exists that can create the {@link Action} of the provided type.
	 */
	Action getAction(Class<? extends Action> action) throws UnknownActionException;

	/**
	 * Returns the {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 *
	 * @return The {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 */
	ImmutableMap<Class<? extends Action>, ActionFactory> getActions();

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	void putProperty(Property property);

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	<T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException;

	/**
	 * Returns an {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 *
	 * @return The {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 */
	ImmutableMap<Class<? extends Property>, Property> getProperties();

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	boolean hasProperty(Class<? extends Property> type);
}
