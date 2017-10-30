package textadventure.items.backpack;

import textadventure.Property;
import textadventure.actions.Action;
import textadventure.characters.Character;
import textadventure.items.BaseInventory;
import textadventure.items.Inventory;
import textadventure.ui.GameInterface;

import java.util.HashMap;
import java.util.stream.Stream;

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
	 * @param gameInterface The {@link GameInterface} instance.
	 * @param positions     The number of positions in the {@link Backpack}.
	 * @return The newly created {@link Backpack}.
	 */
	public static Backpack factory(GameInterface gameInterface, int positions)
	{
		Backpack backpack = new Backpack(positions);
		backpack.addAction("inspect", new InspectBackpackAction(backpack, gameInterface::onBackpackInspect));
		backpack.addAction("expand", new ExpandBackpackAction(backpack, gameInterface::onBackpackExpand));

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
	 * Returns a stream of the {@link Action}s available on the {@link Property}.
	 *
	 * @return Returns the stream of the {@link Action}s available on the {@link Property}.
	 */
	@Override public Stream<Action> getActions()
	{
		return actions.values().stream();
	}
}
