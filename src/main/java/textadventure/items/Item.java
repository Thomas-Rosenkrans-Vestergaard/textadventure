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
}
