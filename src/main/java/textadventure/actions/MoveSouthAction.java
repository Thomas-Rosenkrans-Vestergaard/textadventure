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
		RoomTracker roomTracker = controller.getMaze().getRoomConnections();
		PlayerLocationTracker playerLocationTracker = controller.getLocationTracker();
		if (roomTracker.hasConnection(currentLocation, Direction.SOUTH)) {
			playerLocationTracker.setLocation(player, roomTracker.getRoom(currentLocation, Direction.SOUTH));
			return;
		}

		throw new MoveActionException();
	}
}
