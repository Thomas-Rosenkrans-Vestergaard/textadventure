package textadventure.rooms;

import com.google.common.collect.ImmutableMap;
import textadventure.Property;
import textadventure.UnknownActionException;
import textadventure.actions.Action;
import textadventure.items.BaseInventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an {@link textadventure.items.Inventory} containing {@link textadventure.items.Item}s on the ground.
 */
public class Floor extends BaseInventory implements Property
{

	/**
	 * The {@link Action}s available on the {@link Property}.
	 */
	private Map<Class<? extends Action>, Action> actions = new HashMap<>();

	/**
	 * Creates a new {@link Floor}.
	 */
	public Floor()
	{
		super();
	}

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	@Override public void putAction(Action action)
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
			throw new UnknownActionException(this, type);

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
