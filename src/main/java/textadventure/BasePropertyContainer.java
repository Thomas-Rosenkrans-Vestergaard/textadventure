package textadventure;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

/**
 * Default implementation of the {@link PropertyContainer} interface.
 */
public class BasePropertyContainer extends BaseProperty implements PropertyContainer
{

	/**
	 * The instances of {@link Property} in the {@link PropertyContainer}.
	 */
	private HashMap<String, Property> properties = new HashMap<>();

	/**
	 * Adds a new property to the {@link PropertyContainer}.
	 *
	 * @param name     The name of the {@link Property}.
	 * @param property The {@link Property} to add to the {@link PropertyContainer}.
	 */
	@Override public void addProperty(String name, Property property)
	{
		properties.put(name, property);
	}

	/**
	 * Returns the {@link Property} with the provided <code>name</code>.
	 *
	 * @param name The <code>name</code> of the {@link Property} to get.
	 */
	@Override public Property getProperty(String name)
	{
		return getProperties().get(name);
	}

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	@Override public ImmutableMap<String, Property> getProperties()
	{
		return new ImmutableMap.Builder<String, Property>().putAll(properties).build();
	}
}
