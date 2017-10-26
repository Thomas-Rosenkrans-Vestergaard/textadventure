package textadventure;

import textadventure.actions.Action;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Abstract implementation of the {@link Property} interface.
 */
public abstract class AbstractProperty implements Property
{

	/**
	 * The {@link Action}s available on the {@link Property}.
	 */
	private HashMap<String, Action> actions = new HashMap<>();

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name   The name of the {@link Action}.
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	@Override public void addAction(String name, Action action)
	{
		actions.put(name, action);
	}

	/**
	 * Returns the {@link Action} with the provided name.
	 *
	 * @param name The <code>name</code> of the {@link Action} to return.
	 * @return The {@link Action} with the provided <code>name</code>. Returns <code>null</code>
	 */
	@Override public Action getAction(String name)
	{
		return actions.get(name);
	}

	/**
	 * Returns a stream of the {@link Action}s available on the {@link Property}.
	 *
	 * @return Returns the stream of the {@link Action}s available on the {@link Property}.
	 */
	@Override public Stream<Action> getActions()
	{
		return actions.values().stream();
	}
}
