package textadventure.actions;

import textadventure.*;

public class MoveEastAction implements Action
{

	@Override public String getName()
	{
		return "east";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the east.";
	}

	@Override public void perform(GameController controller, Player player, Room currentLocation) throws ActionException
	{
		RoomTracker roomTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomTracker.hasConnection(currentLocation, Direction.EAST)) {
			playerLocationTracker.setLocation(player, roomTracker.getRoom(currentLocation, Direction.EAST));
			return;
		}

		throw new MoveActionException();
	}
}
