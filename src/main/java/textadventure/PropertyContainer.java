package textadventure;

import com.google.common.collect.ImmutableMap;

/**
 * Object that contains instances of {@link Property}.
 */
public interface PropertyContainer extends Property
{

	/**
	 * Adds the {@link Property} to the {@link PropertyContainer}.
	 *
	 * @param property The {@link Property} to add to the {@link PropertyContainer}.
	 */
	void putProperty(Property property);

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	<T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException;

	<T extends Property> boolean hasProperty(Class<T> type);

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	ImmutableMap<Class<? extends Property>, Property> getProperties();
}
