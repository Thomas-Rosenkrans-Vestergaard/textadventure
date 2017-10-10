package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;

/**
 * Represents an {@link Action} that moves the {@link Player} one {@link textadventure.rooms.Room} south.
 */
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
