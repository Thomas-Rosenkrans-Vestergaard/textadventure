package textadventure.actions;

import textadventure.*;

public class MoveWestAction extends MoveAction
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
		move(controller, player, currentLocation, Direction.WEST);
	}
}
