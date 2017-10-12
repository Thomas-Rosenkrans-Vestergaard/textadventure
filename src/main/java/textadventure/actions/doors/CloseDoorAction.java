package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.actions.ActionFocusMismatchException;
import textadventure.rooms.features.doors.Door;

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
				game.getUI().onMessage("The door is already closed", UIMessage.INFORMATION, player);
				return;
			}

			if (state == Door.State.OPEN) {
<<<<<<< HEAD
				door.setState(Door.State.CLOSED);
				game.getUI().onMessage("You closed the door.", UIMessage.INFORMATION, player);
=======
				door.setState(Door.State.OPEN);
				game.getUI().onCloseDoor(door, player);
>>>>>>> ui
				return;
			}

			throw new IllegalStateException();
		}

		throw new ActionFocusMismatchException(focus, this, player);
	}
}
