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

/**
 * {@link Action} that lets the {@link Player} move through an {@link textadventure.rooms.features.doors.Door}. The
 * {@link textadventure.rooms.features.doors.Door} must have {@link textadventure.rooms.features.doors.Door.State}
 * OPEN before {@link MoveThroughDoorAction} can be performed.
 */
public class MoveThroughDoorAction implements Action
{

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
		if (focus instanceof Door) {
			Door door        = (Door) focus;
			Room currentRoom = player.getCurrentLocation();
			Room targetRoom  = door.getOtherSide(currentRoom);

			if (targetRoom == null) {
				throw new IllegalStateException();
			}

			player.setCurrentLocation(targetRoom);
			game.getUI().onMessage("You moved through the door.", UIMessage.INFORMATION, player);
		}

		throw new ActionFocusMismatchException(focus, this, player);
	}
}
