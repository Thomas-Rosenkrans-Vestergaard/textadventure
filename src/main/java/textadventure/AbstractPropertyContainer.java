package textadventure;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link PropertyContainer} interface.
 */
public class AbstractPropertyContainer extends AbstractProperty implements PropertyContainer
{

	/**
	 * The instances of {@link Property} in the {@link PropertyContainer}.
	 */
	private Map<Class<? extends Property>, Property> properties = new HashMap<>();

	/**
	 * Adds the {@link Property} to the {@link PropertyContainer}.
	 *
	 * @param property The {@link Property} to add to the {@link PropertyContainer}.
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
			throw new UnknownPropertyException();

		return type.cast(properties.get(type));
	}

	@Override public <T extends Property> boolean hasProperty(Class<T> type)
	{
		return properties.containsKey(type);
	}

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	@Override public ImmutableMap<Class<? extends Property>, Property> getProperties()
	{
		return ImmutableMap.copyOf(properties);
	}
}
