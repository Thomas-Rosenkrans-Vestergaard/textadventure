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
		RoomConnectionTracker roomConnectionTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomConnectionTracker.hasConnection(currentLocation, Direction.EAST)) {
			playerLocationTracker.setLocation(player, roomConnectionTracker.getRoom(currentLocation, Direction.EAST));
			return;
		}

		throw new MoveActionException();
	}
}
