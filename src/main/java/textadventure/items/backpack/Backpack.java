package textadventure.items.backpack;

import textadventure.Property;
import textadventure.UnknownPropertyException;
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

	private Map<Class<? extends Property>, Property> properties = new HashMap<>();

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
		return new Backpack(positions);
	}

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	@Override public void putProperty(Property property)
	{
		properties.put(property.getClass(), property);
	}

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	@Override public <T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException
	{
		if (!properties.containsKey(type))
			throw new UnknownPropertyException(this.getClass(), type);

		return type.cast(properties.get(type));
	}

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	@Override public boolean hasProperty(Class<? extends Property> type)
	{
		return properties.containsKey(type);
	}
}
