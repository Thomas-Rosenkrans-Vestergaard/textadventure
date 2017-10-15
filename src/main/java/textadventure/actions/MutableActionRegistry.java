package textadventure.actions;

import com.google.common.collect.ImmutableMap;
import textadventure.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MutableActionRegistry
{

	/**
	 * The registered {@link Action}s mapped to their name.
	 */
	protected Map<String, List<NamedAction>> actions;

	/**
	 * Creates a new empty {@link MutableActionRegistry}.
	 */
	public MutableActionRegistry()
	{
		this(new HashMap<>());
	}

	/**
	 * Creates a new {@link MutableActionRegistry} using the provided {@link Map}.
	 *
	 * @param actions The {@link Action}s to add.
	 */
	public MutableActionRegistry(Map<String, List<NamedAction>> actions)
	{
		this.actions = actions;
	}

	/**
	 * Returns the registered {@link Action}s mapped to their name of their {@link Property}.
	 *
	 * @return The registered {@link Action}s mapped to their name of their {@link Property}.
	 */
	public ImmutableMap<String, List<NamedAction>> getActions()
	{
		return new ImmutableMap.Builder<String, List<NamedAction>>().putAll(actions).build();
	}

	/**
	 * Adds a new {@link Action} to the {@link Action}.
	 *
	 * @param property The name of the {@link Property} to add the {@link Action} under.
	 * @param action   The {@link Action} to add.
	 */
	public void addAction(String property, NamedAction action)
	{
		if (!actions.containsKey(property)) {
			actions.put(property, new ArrayList<>());
		}

		actions.get(property).add(action);
	}

	/**
	 * Creates a new {@link ImmutableActionRegistry} from the {@link MutableActionRegistry}.
	 *
	 * @return The resulting {@link ImmutableActionRegistry}.
	 */
	public ImmutableActionRegistry immutable()
	{
		return new ImmutableActionRegistry(this.actions);
	}
}
