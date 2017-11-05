package textadventure.items;

import textadventure.GameException;
import textadventure.characters.Character;

/**
 * Thrown when an exception occurs while calling the {@link UsableItem#use(Character)} method.
 */
public class UseTimeException extends GameException
{

	/**
	 * The cause of the {@link UseTimeException}.
	 */
	private Exception cause;

	/**
	 * Creates a new {@link UseTimeException}.
	 *
	 * @param cause The cause of the {@link UseTimeException}.
	 */
	public UseTimeException(Exception cause)
	{
		this.cause = cause;
	}

	/**
	 * Returns the cause of the {@link UseTimeException}.
	 *
	 * @return The cause of the {@link UseTimeException}.
	 */
	@Override public Exception getCause()
	{
		return this.cause;
	}
}
