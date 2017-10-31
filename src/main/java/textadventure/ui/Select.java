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
		 * @throws Exception When an exception occurs while handling the {@link SelectResponse}.
		 */
		void response(List<? extends Option<T>> selection) throws Exception;
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
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	void selectIndex(Integer index) throws SelectionAmountException, UnknownIndexException, SelectResponseException;

	/**
	 * Select the {@link Option}s mapped to the provided indices.
	 *
	 * @param indices The indices of the  {@link Option}s to select.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	void selectIndices(List<Integer> indices) throws SelectionAmountException, UnknownIndexException, SelectResponseException;

	/**
	 * Select the provided {@link Option}.
	 *
	 * @param option The selected {@link Option}.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	void selectOption(Option<T> option) throws SelectionAmountException, UnknownOptionException, SelectResponseException;

	/**
	 * Select the provided {@link Option}s.
	 *
	 * @param selection The selected {@link Option}s.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	void selectOptions(List<Option<T>> selection) throws SelectionAmountException, UnknownOptionException, SelectResponseException;
}
