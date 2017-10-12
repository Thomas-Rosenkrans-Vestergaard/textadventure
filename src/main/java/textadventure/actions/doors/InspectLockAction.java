package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.actions.UnknownActionException;
import textadventure.rooms.features.lock.Lock;
import textadventure.rooms.features.lock.Lockable;
import textadventure.ui.UI;
import textadventure.ui.UIMessage;

/**
 * Inspects the {@link Lock} on a {@link textadventure.rooms.features.doors.Door}
 * revealing its <code>code</code>.
 */
public class InspectLockAction implements Action
{

	/**
	 * Returns the identifier of the {@link InspectLockAction}.
	 *
	 * @return The identifier of the {@link InspectLockAction}.
	 */
	@Override public String getIdentifier()
	{
		return "inspect_lock";
	}

	/**
	 * Returns the description of the {@link InspectLockAction}.
	 *
	 * @return The description of the {@link InspectLockAction}.
	 */
	@Override public String getDescription()
	{
		return "Inspect the lock of the door.";
	}

	/**
	 * Performs the {@link InspectLockAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link InspectLockAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		if (!(focus instanceof Lockable)) {
			throw new UnknownActionException(focus, this, player);
		}

		UI       ui       = game.getUI();
		Lockable lockable = (Lockable) focus;
		String message = String.format(
				"You notice that the lock is %s. On the lock is written the number %d.",
				lockable.getLock().getState().name().toLowerCase(),
				lockable.getLock().getCode()
		);

		ui.onMessage(message, UIMessage.INFORMATION, player);
	}
}
