package textadventure.actions.movement;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

/**
 * {@link Action} that moves the {@link Player} to the western {@link textadventure.rooms.features.doors.Door} in
 * the {@link textadventure.rooms.Room}.
 */
public class MoveToWesternDoorAction extends MoveToDoorAction
{

	/**
	 * Returns the name of the {@link MoveToWesternDoorAction}.
	 *
	 * @return The name of the {@link MoveToWesternDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "move_to_western_door";
	}

	/**
	 * Returns the description of the {@link MoveToWesternDoorAction}.
	 *
	 * @return The description of the {@link MoveToWesternDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move to the door at the western part of the room.";
	}

	/**
	 * Performs the {@link MoveToWesternDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link MoveToWesternDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link MoveToWesternDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		move(scenario, player, Direction.WEST);
	}
}
