package textadventure.actions;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.HashMap;

public abstract class AbstractFocusable implements Focusable
{

	/**
	 * The {@link Action} available on the {@link Focusable} object.
	 */
	private Map<String, Action> actions = new HashMap<>();

	/**
	 * Adds some {@link Action} to the {@link Focusable} object.
	 *
	 * @param action The {@link Action} to add to the {@link Focusable} object.
	 */
	@Override public void addAction(Action action)
	{
		this.actions.put(action.getIdentifier(), action);
	}

	/**
	 * Returns a complete {@link ImmutableMap} of the {@link Action}s that can be performed on the
	 * {@link Focusable} object.
	 *
	 * @return The complete {@link ImmutableMap} of the {@link Action}s that can be performed on the
	 * {@link Focusable} object.
	 */
	@Override public ImmutableMap<String, Action> getActions()
	{
		return new ImmutableMap.Builder<String, Action>().putAll(actions).build();
	}
}
