package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Relations
{

	/**
	 * The relations between instances of {@link Property} and instances of {@link Action}.
	 */
	private Map<Class<? extends Property>, List<Class<? extends Action>>> actions = new HashMap<>();

	/**
	 * The relations between instances of {@link PropertyContainer} and instances of {@link Property}.
	 */
	private Map<Class<? extends Property>, List<Class<? extends Property>>> properties = new HashMap<>();

	/**
	 * Adds a new relation between a {@link Property} and an {@link Action}.
	 *
	 * @param property The {@link Property}.
	 * @param action   The {@link Action}.
	 */
	public void addActionRelation(Class<? extends Property> property, Class<? extends Action> action)
	{
		if (!actions.containsKey(property))
			actions.put(property, new ArrayList<>());

		actions.get(property).add(action);
	}

	/**
	 * Adds a new relation between a {@link PropertyContainer} and a {@link Property}.
	 *
	 * @param propertyContainer The {@link PropertyContainer}.
	 * @param property          The {@link Property}.
	 */
	public void addPropertyRelation(Class<? extends Property> propertyContainer, Class<? extends Property> property)
	{
		if (!properties.containsKey(propertyContainer))
			properties.put(propertyContainer, new ArrayList<>());

		properties.get(propertyContainer).add(property);
	}

	public boolean hasActionRelation(Class<? extends Property> property, Class<? extends Action> action)
	{
		if (!actions.containsKey(property))
			return false;

		return actions.get(property).contains(action);
	}

	public boolean hasPropertyRelation(Class<? extends Property> propertyContainer, Class<? extends
			Property> property)
	{
		if (!properties.containsKey(propertyContainer))
			return false;

		return properties.get(propertyContainer).contains(property);
	}

	public ImmutableMap<Class<? extends Property>, List<Class<? extends Action>>> getActions()
	{
		return ImmutableMap.copyOf(this.actions);
	}

	public ImmutableMap<Class<? extends Property>, List<Class<? extends Property>>> getProperties()
	{
		return ImmutableMap.copyOf(this.properties);
	}
}
