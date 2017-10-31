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
	 * @param propertyName The name of the {@link Property}.
	 * @param property     The {@link Property} to add to the {@link PropertyContainer}.
	 */
	void addProperty(String propertyName, Property property);

	/**
	 * Returns the {@link Property} with the provided name.
	 *
	 * @param name The name of the {@link Property} to return.
	 */
	Property getProperty(String name);

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	ImmutableMap<String, Property> getProperties();
}
