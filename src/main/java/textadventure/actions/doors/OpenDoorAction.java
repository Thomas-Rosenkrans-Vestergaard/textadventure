package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.actions.UnknownActionException;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.lock.Lock;
import textadventure.ui.UIMessage;

/**
 * {@link Action} that opens a closed {@link Door}. The {@link Door} can only be opened when the {@link Door} has
 * {@link textadventure.rooms.features.doors.Door.State} CLOSED and the {@link Lock} has state
 * {@link Lock.State} UNLOCKED.
 */
public class OpenDoorAction implements Action
{

	/**
	 * Returns the identifier of the {@link OpenDoorAction}.
	 *
	 * @return The identifier of the {@link OpenDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "open_door";
	}

	/**
	 * Returns the description of the {@link OpenDoorAction}.
	 *
	 * @return The description of the {@link OpenDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Opens the focused door.";
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link OpenDoorAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		if (focus instanceof Door) {
			Door       door  = (Door) focus;
			Lock.State state = door.getLock().getState();

			if (door.getState() == Door.State.OPEN) {
				game.getUI().onMessage("The door is already open.", UIMessage.INFORMATION, player);
				return;
			}

			if (state == Lock.State.LOCKED) {
				String message = "The door you attempted to open is locked. You must first unlock it with a " +
								 "matching key.";
				game.getUI().onMessage(message, UIMessage.INFORMATION, player);
				return;
			}

			if (state == Lock.State.UNLOCKED) {
				door.setState(Door.State.OPEN);
				game.getUI().onMessage("The door is now open.", UIMessage.INFORMATION, player);
				return;
			}

			throw new IllegalStateException();
		}

		throw new UnknownActionException(focus, this, player);
	}
}
