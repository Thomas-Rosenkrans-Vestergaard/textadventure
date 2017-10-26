package textadventure.items;

import textadventure.ui.Option;

public interface ItemType extends Option
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
	 * Returns The name of the {@link ItemType}.
	 *
	 * @return the name of the {@link ItemType}.
	 */
	String getItemName();

	/**
	 * Returns The description of the {@link ItemType}.
	 *
	 * @return the description of the {@link ItemType}.
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
		if (this == item) return true;
		if (item == null) return false;

		return getIdentifier() == item.getIdentifier();
	}

	/**
	 * Returns the name of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 *
	 * @return The name of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 */
	@Override default String getOptionName()
	{
		return getItemName();
	}

	/**
	 * Returns the description of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 *
	 * @return The description of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 */
	@Override default String getOptionDescription()
	{
		return getItemDescription();
	}
}
