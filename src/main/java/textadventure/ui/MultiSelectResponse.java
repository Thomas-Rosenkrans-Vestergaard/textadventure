package textadventure.ui;

import com.google.common.collect.ImmutableMap;

@FunctionalInterface
public interface MultiSelectResponse<T extends Option>
{

	/**
	 * Callback for the {@link MultiSelect} to return the selected {@link Option}(s).
	 *
	 * @param selection The selected {@link Option}(s).
	 */
	void select(ImmutableMap<Integer, T> selection);
}
