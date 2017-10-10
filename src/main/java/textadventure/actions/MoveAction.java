package textadventure.actions;

import textadventure.*;

public abstract class MoveAction implements Action
{

	/**
	 * Attempts to move the provided {@link Player} in the provided {@link Direction}.
	 *
	 * @param controller      The {@link GameController}.
	 * @param player          The {@link Player} to move.
	 * @param currentLocation The current location of the {@link Player}.
	 * @param direction       The {@link Direction} to move in.
	 * @throws MoveActionException
	 */
	public void move(GameController controller, Player player, Room currentLocation, Direction direction) throws MoveActionException
	{
		RoomTracker           roomTracker           = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomTracker.hasConnection(currentLocation, direction)) {
			playerLocationTracker.setLocation(player, roomTracker.getRoom(currentLocation, direction));
			return;
		}

		throw new MoveActionException(currentLocation, direction);
	}
}
