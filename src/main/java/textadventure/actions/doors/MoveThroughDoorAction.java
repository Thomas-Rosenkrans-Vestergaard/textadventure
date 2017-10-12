package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.actions.ActionFocusMismatchException;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.ui.UIMessage;
import textio.SysTextIO;
import textio.TextIO;

/**
 * {@link Action} that lets the {@link Player} move through an {@link textadventure.rooms.features.doors.Door}. The
 * {@link textadventure.rooms.features.doors.Door} must have {@link textadventure.rooms.features.doors.Door.State}
 * OPEN before {@link MoveThroughDoorAction} can be performed.
 */
public class MoveThroughDoorAction implements Action
{

	private TextIO io = new TextIO(new SysTextIO());

	/**
	 * Returns the identifier of the {@link MoveThroughDoorAction}.
	 *
	 * @return The identifier of the {@link MoveThroughDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "go_through";
	}

	/**
	 * Returns the description of the {@link MoveThroughDoorAction}.
	 *
	 * @return The description of the {@link MoveThroughDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move through the focused door.";
	}

	/**
	 * Performs the {@link MoveThroughDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link MoveThroughDoorAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		if (!(focus instanceof Door)) {
			throw new ActionFocusMismatchException(focus, this, player);
		}

		Door door = (Door) focus;

		if (door.getState() == Door.State.OPEN) {
			
		}

		Room currentRoom = player.getCurrentLocation();
		Room targetRoom  = door.getOtherSide(currentRoom);

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		if (door.getState() == Door.State.CLOSED) {
			game.getUI().onMessage("You cannot move through a closed door.", UIMessage.INFORMATION, player);
			return;
		}

		player.setCurrentLocation(targetRoom);
		game.getUI().onMessage("You moved through the door.", UIMessage.INFORMATION, player);
		io.put("You are now in " + player.getCurrentLocation().getName() + "\n");
		return;
	}
}
}
