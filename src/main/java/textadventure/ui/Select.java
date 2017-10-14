package textadventure.ui;

import com.google.common.collect.ImmutableMap;

public interface Select<T extends Option>
{

	/**
	 * Returns the {@link T}s available in the {@link Select}.
	 *
	 * @return The {@link T}s available in the {@link Select}.
	 */
	ImmutableMap<Integer, T> getOptions();
}
