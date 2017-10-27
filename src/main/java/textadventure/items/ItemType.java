package textadventure.items;

import textadventure.ui.Option;

public interface ItemType
{

	/**
	 * Returns the integer identifier that identifies the {@link ItemType}.
	 *
	 * @return The identifier.
	 */
	default int getIdentifier()
	{
		return getClass().hashCode();
	}

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	String getItemName();

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	String getItemDescription();

	/**
	 * Checks if the provided {@link Item} is of the same {@link ItemType} as <code>this</code>.
	 *
	 * @param item The other {@link Item}.
	 * @return <code>True</code> when the other {@link Item} is of the same {@link ItemType} as <code>this</code>.
	 * Otherwise returns <code>false</code>.
	 */
	default boolean instanceOf(Item item)
	{
		return this == item || item != null && getIdentifier() == item.getIdentifier();
	}
}
