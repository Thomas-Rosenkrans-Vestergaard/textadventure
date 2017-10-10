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
		RoomTracker roomTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomTracker.hasConnection(currentLocation, Direction.WEST)) {
			playerLocationTracker.setLocation(player, roomTracker.getRoom(currentLocation, Direction.WEST));
			return;
		}

		throw new MoveActionException();
	}
}
