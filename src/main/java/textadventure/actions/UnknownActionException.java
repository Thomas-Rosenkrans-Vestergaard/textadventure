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
	 * @param focus  The {@link Focusable} object that the {@link Action} was performed on.
	 * @param action The {@link Action} that was attempted.
	 * @param player The {@link Player} who attempted the {@link Action}.
	 */
	public UnknownActionException(Focusable focus, Action action, Player player)
	{
		super(focus, action, player, player.getCurrentLocation());
	}
}
