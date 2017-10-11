package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.scenario.Scenario;

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

	/**
	 * Attempts to move the provided {@link Player} in the provided {@link Direction}.
	 *
	 * @param scenario  The {@link Scenario} that the {@link Action} is a response to.
	 * @param player    The {@link Player} to move.
	 *
	 * @throws MoveActionException When something goes wrong while moving the {@link Player}.
	 */
	@Override public void perform(Scenario scenario, Player player) throws MoveActionException
	{
		move(scenario, player, Direction.EAST);
	}
}
