package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;

/**
 * Represents an {@link Action} that moves the {@link Player} one {@link textadventure.rooms.Room} east.
 */
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
