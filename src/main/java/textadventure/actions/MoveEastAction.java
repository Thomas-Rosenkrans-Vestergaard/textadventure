package textadventure.actions;

import textadventure.*;

public class MoveEastAction extends MoveAction
{

	@Override public String getName()
	{
		return "east";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the east.";
	}

	@Override public void perform(GameController controller, Player player) throws ActionException
	{
		move(controller, player, Direction.EAST);
	}
}
