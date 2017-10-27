package textadventure.ui;

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
	 * @param response The event called when the selected {@link Option}(s) have been validated.
	 **/
	public BaseSelect(Set<? extends Option<T>> options, SelectResponse<T> response)
	{
		this(options, 0, options.size(), response);
	}

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options      The {@link Option}s that can be selected.
	 * @param exactOptions The number of {@link Option}s that must be selected.
	 * @param response     The event called when the selected {@link Option}(s) have been validated.
	 */
	public BaseSelect(Set<? extends Option<T>> options, int exactOptions, SelectResponse<T> response)
	{
		this(options, exactOptions, exactOptions, response);
	}

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options       The {@link Option}s that can be selected.
	 * @param minimumNumber The minimum number of {@link Option}s that must be selected.
	 * @param maximumNumber The maximum number of {@link Option}s that can be selected.
	 * @param response      The event called when the selected {@link Option}(s) have been validated.
	 */
	public BaseSelect(Set<? extends Option<T>> options, int minimumNumber, int maximumNumber, SelectResponse<T> response)
	{
		if (minimumNumber < -1)
			throw new IllegalArgumentException("Minimum number of options can not be less than -1.");

		if (maximumNumber < -1)
			throw new IllegalArgumentException("Maximum number of options can not be less than -1.");

		if (maximumNumber > options.size())
			throw new IllegalArgumentException("Maximum number of options can not be greater than number of options.");

		if (minimumNumber > maximumNumber)
			throw new IllegalArgumentException("Maximum number of options must be greater than minimum options.");

		this.options = options;
		options.forEach(option -> this.optionsMap.put(option.getOptionIndex(), option));
		this.minimumNumber = minimumNumber;
		this.maximumNumber = maximumNumber;
		this.response = response;
	}

	/**
	 * Select the {@link Option}(s) mapped to the provided index.
	 *
	 * @param index The index of the {@link Option} to select.
	 * @throws SelectionAmountOutOfBounds When the number of selections are outside the legal number defined by the
	 *                                    {@link Select}.
	 * @throws UnknownIndexException      When the selected indices aren't contained in the set of possible
	 *                                    {@link Option}s.
	 */
	@Override public void selectIndex(Integer index) throws SelectionAmountOutOfBounds, UnknownIndexException
	{
		ArrayList<Integer> indices = new ArrayList<>();
		indices.add(index);
		selectIndices(indices);
	}

	/**
	 * Select the {@link Option}(s) mapped to the provided indices.
	 *
	 * @param indices The key(s) of the selected {@link Option}(s).
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownIndexException      When one of the selected indices aren't contained in the set of possible
	 *                                    {@link Option}s.
	 */
	@Override
	public void selectIndices(List<Integer> indices) throws SelectionAmountOutOfBounds, UnknownIndexException
	{
		int size = options.size();
		if (size < minimumNumber || size > maximumNumber)
			throw new SelectionAmountOutOfBounds(this, minimumNumber, maximumNumber, size);

		for (Integer index : indices)
			if (!optionsMap.containsKey(index))
				throw new UnknownIndexException(this, ImmutableSet.copyOf(indices), index);


		response.response(indices.stream().map(index -> optionsMap.get(index)).collect(Collectors.toList()));
	}

	/**
	 * Select the provided {@link Option}.
	 *
	 * @param option The selected {@link Option}.
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownOptionException     When one of the selected {@link Option}s is not contained in the possible
	 *                                    {@link Option}s.
	 */
	@Override public void selectValue(Option<T> option) throws SelectionAmountOutOfBounds, UnknownOptionException
	{
		ArrayList<Option<T>> options = new ArrayList<>();
		options.add(option);
		selectValues(options);
	}

	/**
	 * Select the provided {@link Option}(s).
	 *
	 * @param options The selected {@link Option}(s).
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownOptionException     When one of the selected {@link Option}s is not contained in the possible
	 *                                    {@link Option}s.
	 */
	@Override
	public void selectValues(List<Option<T>> options) throws SelectionAmountOutOfBounds, UnknownOptionException
	{
		int size = options.size();
		if (size < minimumNumber || size > maximumNumber)
			throw new SelectionAmountOutOfBounds(this, minimumNumber, maximumNumber, size);

		for (Option<T> option : options)
			if (!options.contains(option))
				throw new UnknownOptionException(this, ImmutableSet.copyOf(this.options), option);

		response.response(options);
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