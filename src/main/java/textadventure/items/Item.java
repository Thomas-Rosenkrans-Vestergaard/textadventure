package textadventure.items;

public interface Item
{

	/**
	 * Returns the unique identifier of the {@link Item}.
	 *
	 * @return The unique identifier of the {@link Item}.
	 */
	int getID();

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	int getName();

	/**
	 * Attempts to use the {@link Item} on some {@link Object}.
	 *
	 * @param object The object to use the {@link Item} on.
	 *
	 * @throws ItemException When something goes wrong while using the {@link Item}.
	 */
	void use(Object object) throws UseItemException;
}
