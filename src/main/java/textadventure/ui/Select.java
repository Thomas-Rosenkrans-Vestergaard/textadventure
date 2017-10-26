package textadventure.ui;

import com.google.common.collect.ImmutableMap;

/**
 * Defines a selectable object. Using the {@link GameInterface}, the player can be prompted to select between the
 * provided options in the {@link Select}.
 */
public interface Select
{

	/**
	 * Returns the available in the {@link Select}.
	 *
	 * @return The available in the {@link Select}.
	 */
	ImmutableMap<Integer, Option> getOptions();
}
