package textadventure.ui;

import com.google.common.collect.ImmutableMap;

public interface Select<T>
{

	/**
	 * Returns the {@link T}s available in the {@link Select}.
	 *
	 * @return The {@link T}s available in the {@link Select}.
	 */
	ImmutableMap<String, T> getOptions();
}
