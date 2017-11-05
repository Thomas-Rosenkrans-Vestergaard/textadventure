package textadventure.items.backpack;

import com.google.common.collect.ImmutableMap;
import textadventure.BaseProperty;
import textadventure.Property;
import textadventure.UnknownActionException;
import textadventure.UnknownPropertyException;
import textadventure.actions.Action;
import textadventure.actions.ActionFactory;
import textadventure.characters.Character;
import textadventure.items.BaseInventory;
import textadventure.items.Inventory;

/**
 * Represents an {@link Inventory} a {@link Character} can carry.
 */
public class Backpack extends BaseInventory implements Property
{

	/**
	 * The {@link Property} the {@link Backpack} delegates methods to.
	 */
	private BaseProperty property = new BaseProperty();

	/**
	 * Creates a new {@link Backpack}.
	 *
	 * @param positions The number of positions in the {@link Backpack}.
	 */
	public Backpack(int positions)
	{
		super(positions);

		putActionFactory(ExpandBackpackAction.class, () -> new ExpandBackpackAction(this));
		putActionFactory(InspectBackpackAction.class, () -> new InspectBackpackAction(this));
	}

	/**
	 * Creates a new {@link Backpack} with an unlimited number of positions.
	 */
	public Backpack()
	{
		super();

		putActionFactory(ExpandBackpackAction.class, () -> new ExpandBackpackAction(this));
		putActionFactory(InspectBackpackAction.class, () -> new InspectBackpackAction(this));
	}

	/**
	 * Creates and returns a new {@link Backpack} with the {@link InspectBackpackAction}.
	 *
	 * @param positions The number of positions in the {@link Backpack}.
	 * @return The newly created {@link Backpack}.
	 */
	public static Backpack factory(int positions)
	{
		return new Backpack(positions);
	}

	/**
	 * Inserts a new {@link ActionFactory} for a provided {@link Action} type.
	 *
	 * @param action  The type of {@link Action} to insert the {@link ActionFactory} for.
	 * @param factory The {@link ActionFactory}.
	 */
	@Override public void putActionFactory(Class<? extends Action> action, ActionFactory factory)
	{
		property.putActionFactory(action, factory);
	}

	/**
	 * Checks whether or not the {@link Property} contains an {@link ActionFactory} that can produce instances of the
	 * provided type of {@link Action}.
	 *
	 * @param action The type of {@link Action} to check for.
	 * @return True if the {@link Property} has an {@link ActionFactory} that can produce instance of the provided
	 * type of {@link Action}. Returns false if no such {@link ActionFactory} exists.
	 */
	@Override public boolean hasActionFactory(Class<? extends Action> action)
	{
		return property.hasActionFactory(action);
	}

	/**
	 * Creates a new {@link Action} from the provided type.
	 *
	 * @param action The type of {@link Action} to create.
	 * @return The newly created {@link Action}.
	 * @throws UnknownActionException When no {@link ActionFactory} exists that can create the {@link Action} of the provided type.
	 */
	@Override public Action getAction(Class<? extends Action> action) throws UnknownActionException
	{
		return property.getAction(action);
	}

	/**
	 * Returns the {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 *
	 * @return The {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 */
	@Override public ImmutableMap<Class<? extends Action>, ActionFactory> getActions()
	{
		return property.getActions();
	}

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	@Override public void putProperty(Property property)
	{
		this.property.putProperty(property);
	}

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	@Override public <T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException
	{
		return property.getProperty(type);
	}

	/**
	 * Returns an {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 *
	 * @return The {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 */
	@Override public ImmutableMap<Class<? extends Property>, Property> getProperties()
	{
		return property.getProperties();
	}

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	@Override public boolean hasProperty(Class<? extends Property> type)
	{
		return property.hasProperty(type);
	}
}
