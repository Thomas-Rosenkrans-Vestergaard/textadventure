package textadventure.lock;

import textadventure.Action;
import textadventure.ActionException;
import textadventure.Player;
import textadventure.Property;

/**
 * Thrown when a {@link Key} was used on an incompatible {@link Lock}.
 */
public class IncorrectKeyException extends ActionException
{

	/**
	 * The {@link Key} that was used.
	 */
	private Key key;

	public IncorrectKeyException(Property property, Action action, Player player, Key key)
	{
		super(property, action, player);
		this.key = key;
	}

	/**
	 * Returns the {@link Key} that was used.
	 *
	 * @return The {@link Key} that was used.
	 */
	public Key getKey()
	{
		return this.key;
	}
}
