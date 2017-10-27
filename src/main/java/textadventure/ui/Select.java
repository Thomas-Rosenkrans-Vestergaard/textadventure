package textadventure.ui;

import com.google.common.collect.ImmutableMap;

import java.util.List;

/**
 * Defines a selectable object. Using the {@link GameInterface}, the player can be prompted to selectIndices between the
 * provided options in the {@link Select}.
 */
public interface Select<T>
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
		void response(List<? extends Option<T>> selection);
	}

	/**
	 * Returns the available {@link Option}s that can be selected.
	 *
	 * @return The available {@link Option}s that can be selected.
	 */
	ImmutableMap<Integer, Option<T>> getOptions();

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
	 * Select the {@link Option}(s) mapped to the provided index.
	 *
	 * @param index The index of the {@link Option} to select.
	 * @throws SelectionAmountOutOfBounds When the number of selections are outside the legal number defined by the
	 *                                    {@link Select}.
	 * @throws UnknownIndexException      When the selected indices aren't contained in the set of possible
	 *                                    {@link Option}s.
	 */
	void selectIndex(Integer index) throws SelectionAmountOutOfBounds, UnknownIndexException;

	/**
	 * Select the {@link Option}(s) mapped to the provided indices.
	 *
	 * @param indices The indices of the  {@link Option}(s) to select.
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownIndexException      When one of the selected indices aren't contained in the set of possible
	 *                                    {@link Option}s.
	 */
	void selectIndices(List<Integer> indices) throws SelectionAmountOutOfBounds, UnknownIndexException;

	/**
	 * Select the provided {@link Option}.
	 *
	 * @param option The selected {@link Option}.
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownOptionException     When one of the selected {@link Option}s is not contained in the possible
	 *                                    {@link Option}s.
	 */
	void selectValue(Option<T> option) throws SelectionAmountOutOfBounds, UnknownOptionException;

	/**
	 * Select the provided {@link Option}(s).
	 *
	 * @param options The selected {@link Option}(s).
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownOptionException     When one of the selected {@link Option}s is not contained in the possible
	 *                                    {@link Option}s.
	 */
	void selectValues(List<Option<T>> options) throws SelectionAmountOutOfBounds, UnknownOptionException;
}
