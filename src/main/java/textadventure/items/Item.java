package textadventure.items;

import textadventure.ui.Option;

public interface Item extends Option
{

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	String getItemName();

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	String getItemDescription();

	/**
	 * Returns an integer representing the type of the {@link Item}.
	 *
	 * @return The integer representing the type of the {@link Item}.
	 */
	int getType();

	/**
	 * Returns the amount of {@link Item}s that can fit in a stack of {@link Item}s.
	 *
	 * @return The amount of {@link Item}s that can fit in a stack of {@link Item}s.
	 */
	int getStackSize();

	/**
	 * Returns the name that should be displayed in the {@link textadventure.ui.Select} or {@link textadventure.ui.MultiSelect}.
	 *
	 * @return The name that should be displayed in the {@link textadventure.ui.Select} or {@link textadventure.ui.MultiSelect}.
	 */
	@Override default String getOptionName()
	{
		return getItemName();
	}

	/**
	 * Returns the description of the {@link Option}. Informs the user what selecting the {@link Option} will do.
	 *
	 * @return The description of the {@link Option}. Informs the user what selecting the {@link Option} will do.
	 */
	@Override default String getOptionDescription()
	{
		return getItemDescription();
	}
}
