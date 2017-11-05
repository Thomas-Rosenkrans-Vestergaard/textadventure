package textadventure;

import textadventure.actions.Action;

/**
 * Thrown when a {@link Property} is missing a requested type of {@link Action}.
 */
public class UnknownActionException extends GameException
{

	/**
	 * The {@link Property} that is missing the provided type of {@link Action}.
	 */
	private Property property;

	/**
	 * The type of {@link Action} that was missing from the {@link Property}.
	 */
	private Class<? extends Action> action;

	/**
	 * Creates a new {@link UnknownActionException}.
	 *
	 * @param property The {@link Property} that is missing the provided type of {@link Action}.
	 * @param action   The type of {@link Action} that was missing from the {@link Property}.
	 */
	public UnknownActionException(Property property, Class<? extends Action> action)
	{
		this.property = property;
		this.action = action;
	}

	/**
	 * Returns the {@link Property} that is missing the provided type of {@link Action}.
	 *
	 * @return The {@link Property} that is missing the provided type of {@link Action}.
	 */
	public Property getProperty()
	{
		return this.property;
	}

	/**
	 * Returns the type of {@link Action} that was missing from the {@link Property}.
	 *
	 * @return The type of {@link Action} that was missing from the {@link Property}.
	 */
	public Class<? extends Action> getAction()
	{
		return this.action;
	}
}
