package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link Property} interface.
 */
public abstract class AbstractProperty implements Property
{

	/**
	 * The {@link Action}s available on the {@link Property}.
	 */
	private Map<Class<? extends Action>, Action> actions = new HashMap<>();

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	@Override public void addAction(Action action)
	{
		actions.put(action.getClass(), action);
	}

	/**
	 * Returns the {@link Action} with the provided name.
	 *
	 * @param type
	 * @return The {@link Action} with the provided name.
	 */
	@Override public <T extends Action> T getAction(Class<T> type) throws UnknownActionException
	{
		if (!actions.containsKey(type))
			throw new UnknownActionException();

		return type.cast(actions.get(type));
	}

	@Override public <T extends Action> boolean hasAction(Class<T> type)
	{
		return actions.containsKey(type);
	}

	/**
	 * Returns an {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 *
	 * @return The {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 */
	@Override public ImmutableMap<Class<? extends Action>, Action> getActions()
	{
		return ImmutableMap.copyOf(actions);
	}
}
