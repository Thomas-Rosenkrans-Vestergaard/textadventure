package textadventure.items.inventory.crates;

import textadventure.Action;
import textadventure.Game;
import textadventure.Player;
import textadventure.Property;
import textadventure.items.inventory.BaseInventory;
import textadventure.lock.Lock;
import textadventure.ui.UserInterface;

import java.util.HashMap;
import java.util.stream.Stream;

public class Crate extends BaseInventory implements Property
{

	/**
	 * The {@link Action}s available on the {@link Property}.
	 */
	private HashMap<String, Action> actions = new HashMap<>();

	/**
	 * The {@link Lock} on the {@link Crate}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link Crate}.
	 *
	 * @param countSlots
	 * @param lock
	 */
	public Crate(int countSlots, Lock lock)
	{
		super(countSlots);
		this.lock = lock;
	}

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name        The name of the {@link Action}. Used when invoking the {@link Action}.
	 * @param description The description of the {@link Action}.
	 * @param action      The {@link Action} to add to the {@link Property}.
	 */
	@Override public void addAction(String name, String description, Action action)
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
