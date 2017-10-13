package textadventure.ui;

import com.google.common.collect.ImmutableMap;

public interface Select<T extends Option>
{

	/**
	 * Returns the {@link Option}s available in the {@link Select}.
	 *
	 * @return The {@link Option}s available in the {@link Select}.
	 */
	ImmutableMap<String, T> getOptions();
}
