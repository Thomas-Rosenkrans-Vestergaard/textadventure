package textadventure.actions;

import textadventure.Game;
import textadventure.Player;
import textadventure.scenario.Scenario;

/**
 * {@link Action} the moves the {@link Player} away from the {@link textadventure.rooms.features.doors.Door}.
 */
public class MoveAwayFromDoorAction implements Action
{
	/**
	 * Returns the name of the {@link MoveAwayFromDoorAction}.
	 *
	 * @return The name of the {@link MoveAwayFromDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "move_away_from_door";
	}

	/**
	 * Returns the description of the {@link MoveAwayFromDoorAction}.
	 *
	 * @return The description of the {@link MoveAwayFromDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move away from door you're currently at.";
	}

	/**
	 * Performs the {@link MoveAwayFromDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link MoveAwayFromDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link MoveAwayFromDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		throw new UnsupportedOperationException();
	}
}
