package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;

/**
 * Represents an {@link Action} that moves the {@link Player} one {@link textadventure.rooms.Room} north.
 */
public class MoveNorthAction extends MoveAction
{

	@Override public String getName()
	{
		return "north";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the north.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param controller The {@link GameController}.
	 * @param player     The {@link Player} performing the {@link Action}.
	 *
	 * @throws ActionException When something goes wrong while performing the {@link Action}.
	 */
	@Override public void perform(GameController controller, Player player) throws ActionException
	{
		move(controller, player, Direction.NORTH);
	}
}
