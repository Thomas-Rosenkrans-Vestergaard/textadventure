package textadventure.ui;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Default implementation of the {@link MultiSelect} interface.
 */
public class BaseMultiSelect implements MultiSelect
{

	/**
	 * The {@link Option}s that can be selected.
	 */
	private final Map<Integer, Option> options;

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
	 * @param options The {@link Option}s.
	 */
	public BaseMultiSelect(Map<Integer, Option> options)
	{
		this(options, 0, options.size());
	}

	/**
	 * Creates a new {@link BaseMultiSelect}.
	 *
	 * @param options        The {@link Option}s that can be selected.
	 * @param minimumOptions The minimum amount of {@link Option}s that must be selected.
	 * @param maximumOptions The maximum amount of {@link Option}s that can be selected.
	 */
	public BaseMultiSelect(Map<Integer, Option> options, int minimumOptions, int maximumOptions)
	{
		if (minimumOptions < -1)
			throw new IllegalArgumentException("Minimum options can not be less than -1.");

		if (maximumOptions < -1)
			throw new IllegalArgumentException("Maximum options can not be less than -1.");

		if (maximumOptions > options.size())
			throw new IllegalArgumentException("Maximum options can not be greater than number of options.");

		if (minimumOptions > maximumOptions || minimumOptions == maximumOptions)
			throw new IllegalArgumentException("Maximum options must be greater than minimum options.");

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
	 * Returns an immutable map of the {@link Option}s in the {@link MultiSelect}.
	 *
	 * @return The immutable map of the {@link Option}s in the {@link MultiSelect}.
	 */
	@Override public ImmutableMap<Integer, Option> getOptions()
	{
		return new ImmutableMap.Builder<Integer, Option>().putAll(options).build();
	}
}
