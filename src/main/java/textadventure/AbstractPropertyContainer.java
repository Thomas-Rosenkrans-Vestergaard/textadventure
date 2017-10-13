package textadventure;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

public abstract class AbstractPropertyContainer extends AbstractProperty implements PropertyContainer
{

	/**
	 * The instances of {@link Property} in the {@link PropertyContainer}.
	 */
	private HashMap<String, Property> properties = new HashMap<>();

	@Override public void addProperty(String propertyName, Property property)
	{
		properties.put(propertyName, property);
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
