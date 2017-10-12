package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.rooms.features.doors.Lock;
import textadventure.ui.UI;
import textadventure.ui.UIMessage;

/**
 * Inspects the {@link textadventure.rooms.features.doors.Lock} on a {@link textadventure.rooms.features.doors.Door}
 * revealing its <code>code</code>.
 */
public class InspectLockAction implements Action
{

	/**
	 * The {@link Lock} to inspect.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link InspectLockAction}.
	 *
	 * @param lock The {@link Lock} to inspect.
	 */
	public InspectLockAction(Lock lock)
	{
		this.lock = lock;
	}

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
		UI ui = game.getUI();
		String message = String.format(
				"You notice that the lock is %s. On the lock is written the number %d.",
				lock.getState().name().toLowerCase(),
				lock.getCode()
		);

		ui.onMessage(message, UIMessage.INFORMATION, player);
	}
}
