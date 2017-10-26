package textadventure.ui;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of the {@link Select} interface.
 */
public class BaseSelect implements Select
{

	/**
	 * The {@link Option}s in the {@link Select}.
	 */
	private Map<Integer, Option> options = new HashMap<>();

	/**
	 * The next free option index.
	 */
	private int currentOptionIndex;

	/**
	 * Creates a new {@link BaseSelect}.
	 *
	 * @param options The {@link Option}s to select from.
	 */
	public BaseSelect(Map<Integer, Option> options)
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
	public void addOption(Option option)
	{
		this.options.put(currentOptionIndex++, option);
	}

	/**
	 * Adds an array of {@link Option}s to the {@link Select}.
	 *
	 * @param options The {@link Option}s to add to the {@link Select}.
	 */
	public void addOptions(Option... options)
	{
		for (Option option : options) {
			addOption(option);
		}
	}

	/**
	 * Adds a list of {@link Option}s to the {@link Select}.
	 *
	 * @param options The {@link Option}s to add to the {@link Select}.
	 */
	public void addOptions(List<Option> options)
	{
		for (Option option : options) {
			addOption(option);
		}
	}

	/**
	 * Returns the available in the {@link Select}.
	 *
	 * @return The available in the {@link Select}.
	 */
	@Override public ImmutableMap<Integer, Option> getOptions()
	{
		return new ImmutableMap.Builder<Integer, Option>().putAll(options).build();
	}
}
