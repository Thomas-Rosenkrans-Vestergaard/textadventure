package textadventure;

import com.google.common.collect.ImmutableMap;

public interface PropertyContainer extends Property
{

	/**
	 * Adds the {@link Property} to the {@link PropertyContainer}.
	 *
	 * @param property The {@link Property} to add to the {@link PropertyContainer}.
	 */
	void addProperty(Property property);

	/**
	 * Returns the {@link Property} with the provided <code>name</code>.
	 *
	 * @param name The <code>name</code> of the {@link Property} to get.
	 */
	Property getProperty(String name);

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	ImmutableMap<String, Property> getProperties();
}
