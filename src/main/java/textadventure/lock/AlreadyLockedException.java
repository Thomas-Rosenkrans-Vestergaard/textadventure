package textadventure.lock;

import textadventure.Action;
import textadventure.ActionException;
import textadventure.Player;
import textadventure.Property;

/**
 * Thrown when the {@link Lock} is already locked.
 */
public class AlreadyLockedException extends ActionException
{

	public AlreadyLockedException(Property property, Action action, Player player)
	{
		super(property, action, player);
	}
}
