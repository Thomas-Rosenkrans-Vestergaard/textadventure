package textadventure;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link Property} interface.
 */
public abstract class AbstractProperty implements Property
{

	/**
	 * The child instances of {@link Property} in the {@link Property}.
	 */
	private Map<Class<? extends Property>, Property> properties = new HashMap<>();

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
			throw new UnknownPropertyException(this.getClass(), type);

		return type.cast(properties.get(type));
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
