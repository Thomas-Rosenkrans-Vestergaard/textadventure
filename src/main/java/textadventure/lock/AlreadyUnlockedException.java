package textadventure.lock;

import textadventure.Action;
import textadventure.ActionException;
import textadventure.Player;
import textadventure.Property;

/**
 * Thrown when the {@link Lock} is already unlocked.
 */
public class AlreadyUnlockedException extends ActionException
{

	public AlreadyUnlockedException(Property property, Action action, Player player)
	{
		super(property, action, player);
	}
}
