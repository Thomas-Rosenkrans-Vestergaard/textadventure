package textadventure.actions.movement;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

/**
 * {@link Action} that moves the {@link Player} to the northern {@link textadventure.rooms.features.doors.Door} in
 * the {@link textadventure.rooms.Room}.
 */
public class MoveToNorthernDoorAction extends MoveToDoorAction
{

	/**
	 * Returns the name of the {@link MoveToNorthernDoorAction}.
	 *
	 * @return The name of the {@link MoveToNorthernDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "move_to_northern_door";
	}

	/**
	 * Returns the description of the {@link MoveToNorthernDoorAction}.
	 *
	 * @return The description of the {@link MoveToNorthernDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move to the door at the northern part of the room.";
	}

	/**
	 * Performs the {@link MoveToNorthernDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link MoveToNorthernDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link MoveToNorthernDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		move(scenario, player, Direction.NORTH);
	}
}
