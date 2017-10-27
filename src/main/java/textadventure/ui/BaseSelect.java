package textadventure.ui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

/**
 * Default implementation of the {@link Select} interface.
 */
public class BaseSelect<T extends Option> implements Select<T>
{

	/**
	 * The {@link Option}s that can be selected.
	 */
	private final Set<T> options;

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
	public BaseSelect(Set<T> options, SelectResponse<T> response)
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
	public BaseSelect(Set<T> options, int exactOptions, SelectResponse<T> response)
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
	public BaseSelect(Set<T> options, int minimumNumber, int maximumNumber, SelectResponse<T> response)
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
		this.minimumNumber = minimumNumber;
		this.maximumNumber = maximumNumber;
		this.response = response;
	}

	/**
	 * Select the provided {@link Option}(s).
	 *
	 * @param selected The selected {@link Option}(s).
	 * @throws SelectionAmountOutOfBounds When the number of selected {@link Option}s are outside the legal number
	 *                                    defined by the {@link Select}.
	 * @throws UnknownOptionException     When one of the selected {@link Option}s is not contained in the possible
	 *                                    {@link Option}s.
	 */
	@Override
	public void select(List<T> selected) throws SelectionAmountOutOfBounds, UnknownOptionException
	{
		int size = selected.size();
		if (size < minimumNumber || size > maximumNumber)
			throw new SelectionAmountOutOfBounds(this, minimumNumber, maximumNumber, size, new ImmutableSet
					.Builder<Option>().addAll(selected).build());

		for (Option option : selected)
			if (!options.contains(option))
				throw new UnknownOptionException(this, new ImmutableSet.Builder<Option>().addAll(options).build(), option);

		response.response(selected);
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
	@Override public ImmutableMap<Integer, T> getOptions()
	{
		ImmutableMap.Builder<Integer, T> builder = new ImmutableMap.Builder<>();
		for (T option : options) {
			builder.put(option.getOptionIdentifier(), option);
		}

		return builder.build();
	}
}
