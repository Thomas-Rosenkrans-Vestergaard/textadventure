package textadventure.ui;

/**
 * Defines an amount of {@link Option}s. Using the {@link GameInterface}, the player can be prompted to select
 * between the provided options in the {@link Select}. {@link MultiSelect} allows the player to select multiple
 * {@link Option}s at once.
 */

public interface MultiSelect extends Select
{

	/**
	 * Returns the minimum amount of {@link Option}s that can be selected.
	 *
	 * @return The minimum amount of {@link Option}s that can be selected.
	 */
	int getMinOptions();

	/**
	 * Returns the maximum amount of {@link Option}s that can be selected.
	 *
	 * @return The maximum amount of {@link Option}s that can be selected.
	 */
	int getMaxOptions();
}
