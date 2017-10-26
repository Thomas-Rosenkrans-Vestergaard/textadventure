package textadventure.ui;

import com.google.common.collect.ImmutableMap;

import java.util.List;

/**
 * Defines a selectable object. Using the {@link GameInterface}, the player can be prompted to select between the
 * provided options in the {@link Select}.
 */
public interface Select<T extends Option>
{

	/**
	 * Response event called when the selected {@link Option}(s) have been validated.
	 */
	@FunctionalInterface
	interface SelectResponse<T>
	{

		/**
		 * Event invoked when the selected {@link Option}(s) have been validated.
		 *
		 * @param selection The selected {@link Option}(s).
		 */
		void response(List<T> selection);
	}

	/**
	 * Returns the available {@link Option}s that can be selected.
	 *
	 * @return The available {@link Option}s that can be selected.
	 */
	ImmutableMap<Integer, T> getOptions();

	/**
	 * Returns the minimum number of {@link Option}s that must be selected.
	 *
	 * @return The minimum number of {@link Option}s that must be selected.
	 */
	int getMinimumNumberOfOptions();

	/**
	 * Returns the maximum number of {@link Option}s that must be selected.
	 *
	 * @return The maximum number of {@link Option}s that must be selected.
	 */
	int getMaximumNumberOfOptions();

	/**
	 * Select the provided {@link Option}(s).
	 *
	 * @param selected The key(s) of the selected {@link Option}(s).
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownOptionException     When one of the selected {@link Option}s is not contained in the possible
	 *                                    {@link Option}s.
	 */
	void select(List<T> selected) throws SelectionAmountOutOfBounds, UnknownOptionException;
}
