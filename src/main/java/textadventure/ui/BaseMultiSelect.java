package textadventure.ui;

import com.google.common.collect.ImmutableMap;

public class BaseMultiSelect<T extends Option> implements MultiSelect<T>
{

	/**
	 * The {@link Option}s that can be selected.
	 */
	private final ImmutableMap<Integer, T> options;

	/**
	 * The minimum amount of {@link Option}s that must be selected.
	 */
	private final int minimumOptions;

	/**
	 * The maximum amount of {@link Option}s that can be selected.
	 */
	private final int maximumOptions;

	/**
	 * Creates a new {@link BaseMultiSelect}.
	 *
	 * @param options        The {@link Option}s that can be selected.
	 * @param minimumOptions The minimum amount of {@link Option}s that must be selected.
	 * @param maximumOptions The maximum amount of {@link Option}s that can be selected.
	 */
	public BaseMultiSelect(ImmutableMap<Integer, T> options, int minimumOptions, int maximumOptions)
	{
		if (minimumOptions < -1)
			throw new IllegalArgumentException("Minimum options can not be less than -1.");

		if (maximumOptions < -1)
			throw new IllegalArgumentException("Maximum options can not be less than -1.");

		this.options = options;
		this.minimumOptions = minimumOptions;
		this.maximumOptions = maximumOptions;
	}

	/**
	 * Returns the minimum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 *
	 * @return The minimum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 */
	@Override public int getMinOptions()
	{
		return minimumOptions;
	}

	/**
	 * Returns the maximum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 *
	 * @return The maximum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 */
	@Override public int getMaxOptions()
	{
		return maximumOptions;
	}

	/**
	 * Returns the {@link T}s available in the {@link Select}.
	 *
	 * @return The {@link T}s available in the {@link Select}.
	 */
	@Override public ImmutableMap<Integer, T> getOptions()
	{
		return options;
	}
}
