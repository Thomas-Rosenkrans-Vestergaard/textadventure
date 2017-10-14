package textadventure;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

public abstract class AbstractPropertyContainer extends AbstractProperty implements PropertyContainer
{

	/**
	 * The instances of {@link Property} in the {@link PropertyContainer}.
	 */
	private HashMap<String, Property> properties = new HashMap<>();

	/**
	 * Adds a new property to the {@link PropertyContainer}.
	 *
	 * @param name        The name of the {@link Property}.
	 * @param description The description of the {@link Property}.
	 * @param property    The {@link Property} to add to the {@link PropertyContainer}.
	 */
	@Override public void addProperty(String name, String description, Property property)
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
		return properties.get(name);
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
