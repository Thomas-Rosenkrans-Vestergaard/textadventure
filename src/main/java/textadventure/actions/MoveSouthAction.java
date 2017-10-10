package textadventure.actions;

import textadventure.*;

public class MoveSouthAction implements Action
{

	@Override public String getName()
	{
		return "south";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the south.";
	}

	@Override public void perform(GameController controller, Player player, Room currentLocation) throws ActionException
	{
		RoomConnectionTracker roomConnectionTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomConnectionTracker.hasConnection(currentLocation, Direction.SOUTH)) {
			playerLocationTracker.setLocation(player, roomConnectionTracker.getRoom(currentLocation, Direction.SOUTH));
			return;
		}

		throw new MoveActionException();
	}
}
