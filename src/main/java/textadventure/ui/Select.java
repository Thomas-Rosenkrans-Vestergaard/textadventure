package textadventure.ui;

import com.google.common.collect.ImmutableMap;

/**
 * Defines a selectable class. Using the {@link GameInterface}, the player can be prompted to select between the
 * provided options in the {@link Select}.
 *
 * @param <O> The type of the option.
 */
public interface Select<O extends Option>
{

	/**
	 * Returns the available in the {@link Select}.
	 *
	 * @return The available in the {@link Select}.
	 */
	ImmutableMap<Integer, O> getOptions();
}
