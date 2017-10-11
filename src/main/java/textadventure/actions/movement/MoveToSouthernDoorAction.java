package textadventure.actions.movement;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

/**
 * {@link Action} that moves the {@link Player} to the southern {@link textadventure.rooms.features.doors.Door} in
 * the {@link textadventure.rooms.Room}.
 */
public class MoveToSouthernDoorAction extends MoveToDoorAction
{

	/**
	 * Returns the name of the {@link MoveToSouthernDoorAction}.
	 *
	 * @return The name of the {@link MoveToSouthernDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "move_to_southern_door";
	}

	/**
	 * Returns the description of the {@link MoveToSouthernDoorAction}.
	 *
	 * @return The description of the {@link MoveToSouthernDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move to the door at the southern part of the room.";
	}

	/**
	 * Performs the {@link MoveToSouthernDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link MoveToSouthernDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link MoveToSouthernDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		move(scenario, player, Direction.SOUTH);
	}
}
