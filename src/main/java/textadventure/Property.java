package textadventure;

import textadventure.actions.Action;
import textadventure.characters.Character;

/**
 * Object that {@link Character}s can perform {@link Action}s upon.
 */
public interface Property
{

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	void putProperty(Property property);

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	<T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException;

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	boolean hasProperty(Class<? extends Property> type);
}
