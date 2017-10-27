package textadventure.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows a player to gather information about a {@link Door}.
 */
public class InspectDoorAction extends DoorAction
{

	/**
	 * The possible {@link Outcome}s of the {@link InspectDoorAction}.
	 */
	public enum Outcome
	{
		SUCCESS,
	}

	/**
	 * The {@link Outcome} of the {@link InspectDoorAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link InspectDoorAction}.
	 */
	private ActionPerformCallback<InspectDoorAction> callback;

	/**
	 * Creates a new {@link InspectDoorAction}.
	 *
	 * @param door     The {@link Door} to inspect.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectDoorAction}.
	 */
	InspectDoorAction(Door door, ActionPerformCallback<InspectDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectDoorAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link InspectDoorAction}.
	 * @param arguments The arguments provided to the {@link InspectDoorAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		outcome = Outcome.SUCCESS;
		callback.send(game, player, this);
	}

	/**
	 * Returns the {@link Outcome} of the {@link InspectDoorAction}.
	 *
	 * @return The {@link Outcome} of the {@link InspectDoorAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
