package textadventure.actions;

import textadventure.*;

public class MoveWestAction implements Action
{

	@Override public String getName()
	{
		return "west";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the west.";
	}

	@Override public void perform(GameController controller, Player player, Room currentLocation) throws ActionException
	{
		RoomConnectionTracker roomConnectionTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomConnectionTracker.hasConnection(currentLocation, Direction.WEST)) {
			playerLocationTracker.setLocation(player, roomConnectionTracker.getRoom(currentLocation, Direction.WEST));
			return;
		}

		throw new MoveActionException();
	}
}
