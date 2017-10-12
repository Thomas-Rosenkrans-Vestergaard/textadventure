package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.actions.UnknownActionException;
import textadventure.rooms.features.doors.Door;
import textadventure.ui.UIMessage;

public class CloseDoorAction implements Action
{

	/**
	 * Returns the identifier of the {@link CloseDoorAction}.
	 *
	 * @return The identifier of the {@link CloseDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "close_door";
	}

	/**
	 * Returns the description of the {@link CloseDoorAction}.
	 *
	 * @return The description of the {@link CloseDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Closes the focused door.";
	}

	/**
	 * Performs the {@link CloseDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link CloseDoorAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		if (focus instanceof Door) {
			Door       door  = (Door) focus;
			Door.State state = door.getState();

			if (state == Door.State.CLOSED) {
				return;
			}

			if (state == Door.State.OPEN) {
				door.setState(Door.State.CLOSED);
				game.getUI().onMessage("You closed the door.", UIMessage.INFORMATION, player);
				return;
			}

			throw new IllegalStateException();
		}

		throw new UnknownActionException(focus, this, player);
	}
}
