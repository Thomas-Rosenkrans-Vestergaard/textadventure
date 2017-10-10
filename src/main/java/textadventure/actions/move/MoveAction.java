package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.rooms.RoomController;
import textadventure.rooms.features.RoomFeature;
import textadventure.rooms.features.doors.Door;

import java.util.List;

/**
 * Represents an {@link Action} that moves the {@link Player} in some {@link Direction}.
 */
public abstract class MoveAction implements Action
{

	/**
	 * Attempts to move the provided {@link Player} in the provided {@link Direction}.
	 *
	 * @param controller The {@link GameController}.
	 * @param player     The {@link Player} to move.
	 * @param direction  The {@link Direction} to move in.
	 *
	 * @throws MoveActionException
	 */
	public void move(GameController controller, Player player, Direction direction) throws MoveActionException
	{
		RoomController roomTracker = controller.getMaze().getRoomConnections();
		if (roomTracker.hasConnection(player.getCurrentLocation(), direction)) {
			player.setCurrentLocation(roomTracker.getRoom(player.getCurrentLocation(), direction));
			return;
		}

		throw new MoveActionException(player.getCurrentLocation(), direction);
	}
}
