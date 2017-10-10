package textadventure.actions;

import textadventure.*;

public class MoveSouthAction extends MoveAction
{

	@Override public String getName()
	{
		return "south";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the south.";
	}

	@Override public void perform(GameController controller, Player player) throws ActionException
	{
		move(controller, player, Direction.SOUTH);
	}
}
