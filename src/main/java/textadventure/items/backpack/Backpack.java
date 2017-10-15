package textadventure.items.backpack;

import textadventure.Property;
import textadventure.actions.Action;
import textadventure.actions.NamedAction;
import textadventure.items.BaseInventory;
import textadventure.items.Inventory;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Represents a {@link Inventory} the {@link textadventure.Player} can carry.
 */
public class Backpack extends BaseInventory implements Property, Inventory
{

	/**
	 * The {@link Action}s available on the {@link Backpack}.
	 */
	private HashMap<String, Action> actions = new HashMap<>();

	/**
	 * Creates a new {@link Backpack}.
	 *
	 * @param countSlots The number of slots in the {@link Backpack}.
	 */
	public Backpack(int countSlots)
	{
		super(countSlots);

		addAction(new InspectBackpackAction(this));
	}

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	@Override public void addAction(NamedAction action)
	{
		actions.put(action.getActionName(), action);
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
