package textadventure.ui;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Base implementation of the {@link Select} interface.
 *
 * @param <O> The type of {@link Option} to select from.
 */
public class BaseSelect<O extends Option> implements Select<O>
{

	/**
	 * The {@link Option}s in the {@link Select}.
	 */
	private Map<Integer, O> options = new HashMap<>();

	/**
	 * The next free option index.
	 */
	private int currentOptionIndex;

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options The {@link Option}s to select from.
	 */
	public BaseSelect(Map<Integer, O> options)
	{
		this.options = options;
		this.currentOptionIndex = options.size();
	}

	/**
	 * Creates a new empty {@link BaseSelect}.
	 */
	public BaseSelect()
	{
		this(new HashMap<>());
	}

	/**
	 * Adds a new {@link Option} to the {@link Select}.
	 *
	 * @param option The {@link Option} to add.
	 */
	public void addOption(O option)
	{
		this.options.put(currentOptionIndex++, option);
	}

	/**
	 * Returns the available in the {@link Select}.
	 *
	 * @return The available in the {@link Select}.
	 */
	@Override public ImmutableMap<Integer, O> getOptions()
	{
		return new ImmutableMap.Builder<Integer, O>().putAll(options).build();
	}
}
