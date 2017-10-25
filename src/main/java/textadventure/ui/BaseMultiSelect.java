package textadventure.ui;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link MultiSelect} interface.
 *
 * @param <O> The type of {@link Option} selectable in the {@link MultiSelect}.
 */
public class BaseMultiSelect<O extends Option> implements MultiSelect<O>
{

	/**
	 * The {@link Option}s that can be selected.
	 */
	private final Map<Integer, O> options;

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
	public BaseMultiSelect(Map<Integer, O> options, int minimumOptions, int maximumOptions)
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
	 * Creates a new empty {@link BaseMultiSelect}.
	 *
	 * @param minimumOptions The minimum amount of {@link Option}s that must be selected.
	 * @param maximumOptions The maximum amount of {@link Option}s that can be selected.
	 */
	public BaseMultiSelect(int minimumOptions, int maximumOptions)
	{
		this(new HashMap<>(), minimumOptions, maximumOptions);
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
	@Override public ImmutableMap<Integer, O> getOptions()
	{
		return new ImmutableMap.Builder<Integer, O>().putAll(options).build();
	}
}
