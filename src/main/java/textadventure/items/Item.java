package textadventure.items;

import textadventure.ui.Option;

public interface Item extends Option
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
	String getName();
}
