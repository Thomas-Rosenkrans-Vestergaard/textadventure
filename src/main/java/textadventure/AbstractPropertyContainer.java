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
	 * Adds the {@link Property} to the {@link PropertyContainer}.
	 *
	 * @param property The {@link Property} to add to the {@link PropertyContainer}.
	 */
	@Override public void addProperty(Property property)
	{
		properties.put(property.getPropertyName(), property);
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
