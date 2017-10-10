package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;

/**
 * Represents an {@link Action} that moves the {@link Player} one {@link textadventure.rooms.Room} west.
 */
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

	@Override public void perform(GameController controller, Player player) throws ActionException
	{
		move(controller, player, Direction.WEST);
	}
}
