package textadventure.items;

/**
 * An {@link ItemType} represents a type of {@link Item}. {@link ItemType}s are compared using the
 * {@link ItemType#instanceOf(ItemType)} method. Implementations of this interface must override the
 * {@link Object#hashCode()} method.
 */
public interface ItemType
{

	/**
	 * Returns the type of {@link Class} of the {@link ItemType}.
	 *
	 * @return The type of {@link Class} of the {@link ItemType}.
	 */
	default Class<?> getItemTypeClass()
	{
		return getClass();
	}

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	String getItemTypeName();

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	String getItemTypeDescription();

	/**
	 * Checks if the provided {@link ItemType} is of the same {@link ItemType} as <code>this</code>.
	 *
	 * @param item The other {@link ItemType}.
	 */
	default boolean instanceOf(ItemType item)
	{
		return this == item || item != null && hashCode() == item.hashCode();
	}
}
