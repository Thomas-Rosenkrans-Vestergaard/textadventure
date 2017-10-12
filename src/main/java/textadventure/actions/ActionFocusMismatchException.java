package textadventure.actions;

import textadventure.Player;

/**
 * Thrown when some {@link textadventure.actions.Action} could not be performed on some
 * {@link Focusable} object.
 */
public class ActionFocusMismatchException extends ActionException
{

	/**
	 * Creates a new {@link ActionFocusMismatchException}.
	 *
	 * @param focus  The {@link Focusable} object that the {@link Action} could not be performed on.
	 * @param action The {@link Action} that was attempted.
	 * @param player The {@link Player} who attempted the {@link Action}.
	 */
	public ActionFocusMismatchException(Focusable focus, Action action, Player player)
	{
		super(focus, action, player, player.getCurrentLocation());
	}
}
