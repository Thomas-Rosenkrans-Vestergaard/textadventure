package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Action;
import textadventure.actions.ActionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link Property} interface.
 */
public class BaseProperty implements Property
{

	/**
	 * The child instances of {@link Property} in the {@link Property}.
	 */
	private Map<Class<? extends Property>, Property> properties = new HashMap<>();

	/**
	 * The instances of {@link ActionFactory} mapped to the type of {@link Action} they create.
	 */
	private Map<Class<? extends Action>, ActionFactory> factories = new HashMap<>();

	/**
	 * Inserts a new {@link ActionFactory} for a provided {@link Action} type.
	 *
	 * @param action  The type of {@link Action} to insert the {@link ActionFactory} for.
	 * @param factory The {@link ActionFactory}.
	 */
	@Override public void putActionFactory(Class<? extends Action> action, ActionFactory factory)
	{
		factories.put(action, factory);
	}

	/**
	 * Checks whether or not the {@link Property} contains an {@link ActionFactory} that can produce instances of the
	 * provided type of {@link Action}.
	 *
	 * @param action The type of {@link Action} to check for.
	 * @return True if the {@link Property} has an {@link ActionFactory} that can produce instance of the provided
	 * type of {@link Action}. Returns false if no such {@link ActionFactory} exists.
	 */
	@Override public boolean hasActionFactory(Class<? extends Action> action)
	{
		return factories.containsKey(action);
	}

	/**
	 * Creates a new {@link Action} from the provided type.
	 *
	 * @param action The type of {@link Action} to create.
	 * @return The newly created {@link Action}.
	 * @throws UnknownActionException When no {@link ActionFactory} exists that can create the {@link Action} of the provided type.
	 */
	@Override public Action getAction(Class<? extends Action> action) throws UnknownActionException
	{
		ActionFactory factory = factories.get(action);

		if (factory == null)
			throw new UnknownActionException(this, action);

		return factory.create();
	}

	/**
	 * Returns the {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 *
	 * @return The {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 */
	@Override public ImmutableMap<Class<? extends Action>, ActionFactory> getActions()
	{
		return ImmutableMap.copyOf(factories);
	}

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	@Override public void putProperty(Property property)
	{
		properties.put(property.getClass(), property);
	}

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	@Override public <T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException
	{
		if (!properties.containsKey(type))
			throw new UnknownPropertyException(this, type);

		return type.cast(properties.get(type));
	}

	/**
	 * Returns an {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 *
	 * @return The {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 */
	@Override public ImmutableMap<Class<? extends Property>, Property> getProperties()
	{
		return ImmutableMap.copyOf(properties);
	}

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	@Override public boolean hasProperty(Class<? extends Property> type)
	{
		return properties.containsKey(type);
	}
}
