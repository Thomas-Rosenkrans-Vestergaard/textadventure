package textadventure.actions;

import com.google.common.collect.ImmutableMap;

/**
 * Represents something that can be focused by a {@link textadventure.Player}. Specialized commands can then be
 * called on an {@link Focusable} object.
 */
public interface Focusable
{

	/**
	 * Returns the identifier use the identify the {@link Focusable} object. This identifier used to focus on the
	 * {@link Focusable} object using the <code>focus</code> command.
	 *
	 * @return The identifier use the identify the {@link Focusable} object. This identifier used to focus on the
	 * {@link Focusable} object using the <code>focus</code> command.
	 */
	String getIdentifier();

	/**
	 * Adds some {@link Action} to the {@link Focusable} object.
	 *
	 * @param action The {@link Action} to add to the {@link Focusable} object.
	 */
	void addAction(Action action);

	/**
	 * Returns a complete {@link ImmutableMap} of the {@link Action}s that can be performed on the
	 * {@link Focusable} object.
	 *
	 * @return The complete {@link ImmutableMap} of the {@link Action}s that can be performed on the
	 * {@link Focusable} object.
	 */
	ImmutableMap<String, Action> getActions();
}
