package textadventure.actions;

import textadventure.Player;
import textadventure.scenario.Scenario;

/**
 * Thrown when some {@link textadventure.actions.Action} could not be performed on some
 * {@link textadventure.scenario.Scenario}.
 */
public class UnknownActionException extends ActionException
{

	/**
	 * Creates a new {@link UnknownActionException}.
	 *
	 * @param scenario The {@link Scenario} the {@link Action} is a response to.
	 * @param action   The {@link Action} that was attempted.
	 * @param player   The {@link Player} who attempted the {@link Action}.
	 */
	public UnknownActionException(Scenario scenario, Action action, Player player)
	{
		super(scenario, action, player, player.getCurrentLocation());
	}
}
