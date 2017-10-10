package textadventure.actions;

import textadventure.*;

public class MoveNorthAction implements Action
{

	@Override public String getName()
	{
		return "north";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the north.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param controller      The {@link GameController}.
	 * @param player          The {@link Player} performing the {@link Action}.
	 * @param currentLocation The {@link Room} the {@link Player} is currently located in.
	 * @throws ActionException When something goes wrong while performing the {@link Action}.
	 */
	@Override public void perform(GameController controller, Player player, Room currentLocation) throws ActionException
	{
		RoomTracker roomTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomTracker.hasConnection(currentLocation, Direction.NORTH)) {
			playerLocationTracker.setLocation(player, roomTracker.getRoom(currentLocation, Direction.NORTH));
			return;
		}

		throw new MoveActionException();
	}
}
