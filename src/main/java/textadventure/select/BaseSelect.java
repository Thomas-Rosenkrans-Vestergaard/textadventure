package textadventure.select;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Default implementation of the {@link Select} interface.
 */
public class BaseSelect<T> implements Select<T>
{

	/**
	 * The {@link Set} of {@link Option}s that can be selected.
	 */
	private final Set<? extends Option<T>> options;

	/**
	 * The {@link Map} of {@link Option<T>}s that can be selected.
	 */
	private final Map<Integer, Option<T>> optionsMap = new HashMap<>();

	/**
	 * The minimum number of {@link Option}s that must be selected.
	 */
	private final int minimumNumber;

	/**
	 * The maximum number of {@link Option}s that can be selected.
	 */
	private final int maximumNumber;

	/**
	 * Response event called when the selected {@link Option}(s) have been validated.
	 */
	private SelectResponse<T> response;

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options  The {@link Option}s that can be selected.
	 * @param response The event invoked when the selected {@link Option}(s) have been validated.
	 * @throws NoOptionsException        When the {@link Set} of {@link Option}s is empty.
	 * @throws NotEnoughOptionsException When the number of {@link Option}s provided doesn't fulfill the minimum
	 *                                   amount of {@link Option}s.
	 */
	public BaseSelect(Set<? extends Option<T>> options, SelectResponse<T> response) throws NoOptionsException, NotEnoughOptionsException
	{
		this(options, 1, options.size(), response);
	}

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options      The {@link Option}s that can be selected.
	 * @param exactOptions The number of {@link Option}s that must be selected.
	 * @param response     The event invoked when the selected {@link Option}(s) have been validated.
	 * @throws NoOptionsException        When the {@link Set} of {@link Option}s is empty.
	 * @throws NotEnoughOptionsException When the number of {@link Option}s provided doesn't fulfill the minimum
	 *                                   amount of {@link Option}s.
	 */
	public BaseSelect(Set<? extends Option<T>> options, int exactOptions, SelectResponse<T> response) throws NoOptionsException, NotEnoughOptionsException
	{
		this(options, exactOptions, exactOptions, response);
	}

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options       The {@link Option}s that can be selected.
	 * @param minimumNumber The minimum number of {@link Option}s that must be selected.
	 * @param maximumNumber The maximum number of {@link Option}s that can be selected.
	 * @param response      The event invoked when the selected {@link Option}(s) have been validated.
	 * @throws NoOptionsException        When the {@link Set} of {@link Option}s is empty.
	 * @throws NotEnoughOptionsException When the number of {@link Option}s provided doesn't fulfill the minimum
	 *                                   amount of {@link Option}s.
	 */
	public BaseSelect(Set<? extends Option<T>> options, int minimumNumber, int maximumNumber, SelectResponse<T>
			response) throws NoOptionsException, NotEnoughOptionsException
	{
		if (options.size() == 0)
			throw new NoOptionsException(this);

		if (maximumNumber > options.size())
			throw new NotEnoughOptionsException(this, minimumNumber, options.size());

		if (minimumNumber < 1)
			throw new IllegalArgumentException("Minimum number of options can not be less than 1.");

		if (maximumNumber < 1)
			throw new IllegalArgumentException("Maximum number of options can not be less than 1.");

		this.options = options;
		options.forEach(option -> this.optionsMap.put(option.getOptionIndex(), option));
		this.minimumNumber = minimumNumber;
		this.maximumNumber = maximumNumber;
		this.response = response;
	}

	/**
	 * Select the {@link Option}(s) mapped to the provided index.
	 *
	 * @param selection The index of the {@link Option} to select.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	@Override
	public void selectIndex(Integer selection) throws SelectionAmountException, UnknownIndexException, SelectResponseException
	{
		ArrayList<Integer> indices = new ArrayList<>();
		indices.add(selection);
		selectIndices(indices);
	}

	/**
	 * Select the {@link Option}s mapped to the provided indices.
	 *
	 * @param selection The indices of the  {@link Option}s to select.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	@Override
	public void selectIndices(List<Integer> selection) throws SelectionAmountException, UnknownIndexException, SelectResponseException
	{
		int size = selection.size();
		if (size < minimumNumber || size > maximumNumber)
			throw new SelectionAmountException(this, minimumNumber, maximumNumber, size);

		for (Integer index : selection)
			if (!optionsMap.containsKey(index))
				throw new UnknownIndexException(this, ImmutableSet.copyOf(selection), index);

		try {
			response.response(selection.stream().map(optionsMap::get).collect(Collectors.toList()));
		} catch (Exception e) {
			throw new SelectResponseException(this, e);
		}
	}

	/**
	 * Select the provided {@link Option}.
	 *
	 * @param selection The selected {@link Option}.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	@Override
	public void selectOption(Option<T> selection) throws SelectionAmountException, UnknownOptionException, SelectResponseException
	{
		ArrayList<Option<T>> options = new ArrayList<>();
		options.add(selection);
		selectOptions(options);
	}

	/**
	 * Select the provided {@link Option}s.
	 *
	 * @param selection The selected {@link Option}s.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponse}.
	 */
	@Override
	public void selectOptions(List<Option<T>> selection) throws SelectionAmountException, UnknownOptionException, SelectResponseException
	{
		int size = selection.size();
		if (size < minimumNumber || size > maximumNumber)
			throw new SelectionAmountException(this, minimumNumber, maximumNumber, size);

		for (Option<T> option : selection)
			if (!this.options.contains(option))
				throw new UnknownOptionException(this, ImmutableSet.copyOf(this.options), option);

		try {
			response.response(selection);
		} catch (Exception e) {
			throw new SelectResponseException(this, e);
		}
	}

	/**
	 * Returns the minimum number of {@link Option}s that must be selected.
	 *
	 * @return The minimum number of {@link Option}s that must be selected.
	 */
	@Override public int getMinimumNumberOfOptions()
	{
		return minimumNumber;
	}

	/**
	 * Returns the maximum number of {@link Option}s that must be selected.
	 *
	 * @return The maximum number of {@link Option}s that must be selected.
	 */
	@Override public int getMaximumNumberOfOptions()
	{
		return maximumNumber;
	}

	/**
	 * Returns the available {@link Option}s that can be selected.
	 *
	 * @return The available {@link Option}s that can be selected.
	 */
	@Override public ImmutableMap<Integer, Option<T>> getOptions()
	{
		ImmutableMap.Builder<Integer, Option<T>> builder = new ImmutableMap.Builder<>();
		for (Option<T> option : options) {
			builder.put(option.getOptionIndex(), option);
		}

		return builder.build();
	}
}