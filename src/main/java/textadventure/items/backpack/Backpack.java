package textadventure.items.backpack;

import com.google.common.collect.ImmutableMap;
import textadventure.Property;
import textadventure.UnknownActionException;
import textadventure.actions.Action;
import textadventure.characters.Character;
import textadventure.items.BaseInventory;
import textadventure.items.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an {@link Inventory} a {@link Character} can carry.
 */
public class Backpack extends BaseInventory implements Property
{

	/**
	 * Creates a new {@link Backpack}.
	 *
	 * @param positions The number of positions in the {@link Backpack}.
	 */
	public Backpack(int positions)
	{
		super(positions);
	}

	/**
	 * Creates a new {@link Backpack} with an unlimited number of positions.
	 */
	public Backpack()
	{
		super();
	}

	/**
	 * Creates and returns a new {@link Backpack} with the {@link InspectBackpackAction}.
	 *
	 * @param positions The number of positions in the {@link Backpack}.
	 * @return The newly created {@link Backpack}.
	 */
	public static Backpack factory(int positions)
	{
		Backpack backpack = new Backpack(positions);
		backpack.putAction(new InspectBackpackAction(backpack));
		backpack.putAction(new ExpandBackpackAction(backpack));

		return backpack;
	}


	/**
	 * The {@link Action}s available on the {@link Property}.
	 */
	private Map<Class<? extends Action>, Action> actions = new HashMap<>();

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
