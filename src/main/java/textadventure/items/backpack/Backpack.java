package textadventure.items.backpack;

import com.google.common.collect.ImmutableMap;
import textadventure.Property;
import textadventure.actions.Action;
import textadventure.characters.Character;
import textadventure.items.BaseInventory;
import textadventure.items.Inventory;

import java.util.HashMap;

/**
 * Represents an {@link Inventory} a {@link Character} can carry.
 */
public class Backpack extends BaseInventory implements Property
{

	/**
	 * The {@link Action}s available on the {@link Backpack}.
	 */
	private HashMap<String, Action> actions = new HashMap<>();

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
		backpack.addAction("inspect", new InspectBackpackAction(backpack));
		backpack.addAction("expand", new ExpandBackpackAction(backpack));

		return backpack;
	}

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name   The name of the {@link Action}.
	 * @param action The {@link Action} to put to the {@link Property}.
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
	 * Returns an {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 *
	 * @return The {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 */
	@Override public ImmutableMap<String, Action> getActions()
	{
		return ImmutableMap.copyOf(actions);
	}
}
