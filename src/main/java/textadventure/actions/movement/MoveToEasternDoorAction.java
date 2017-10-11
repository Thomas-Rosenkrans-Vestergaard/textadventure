package textadventure.actions.movement;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

/**
 * {@link Action} that moves the {@link Player} to the eastern {@link textadventure.rooms.features.doors.Door} in
 * the {@link textadventure.rooms.Room}.
 */
public class MoveToEasternDoorAction extends MoveToDoorAction
{

	/**
	 * Returns the name of the {@link MoveToEasternDoorAction}.
	 *
	 * @return The name of the {@link MoveToEasternDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "move_to_eastern_door";
	}

	/**
	 * Returns the description of the {@link MoveToEasternDoorAction}.
	 *
	 * @return The description of the {@link MoveToEasternDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move to the door at the eastern part of the room.";
	}

	/**
	 * Performs the {@link MoveToEasternDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link MoveToEasternDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link MoveToEasternDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		move(scenario, player, Direction.EAST);
	}
}
