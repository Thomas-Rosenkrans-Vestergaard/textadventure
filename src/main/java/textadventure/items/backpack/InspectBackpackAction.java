package textadventure.items.backpack;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

public class InspectBackpackAction extends BackpackAction
{

	/**
	 * The possible {@link Outcome}s of the {@link InspectBackpackAction}.
	 */
	public enum Outcome
	{
		SUCCESS,
	}

	/**
	 * The {@link Outcome} of the {@link InspectBackpackAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to use as a send after performing the {@link InspectBackpackAction}.
	 */
	private ActionPerformCallback<InspectBackpackAction> callback;

	/**
	 * Creates a new {@link InspectBackpackAction}.
	 *
	 * @param door     The {@link Backpack} to be inspected.
	 * @param callback The {@link ActionPerformCallback} to use as a send after performing the {@link InspectBackpackAction}.
	 */
	InspectBackpackAction(Backpack door, ActionPerformCallback<InspectBackpackAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectBackpackAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link InspectBackpackAction}.
	 * @param arguments The arguments provided to the {@link InspectBackpackAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		outcome = Outcome.SUCCESS;
		callback.send(game, player, this);
	}

	/**
	 * Returns the {@link Outcome} of the {@link InspectBackpackAction}.
	 *
	 * @return The {@link Outcome} of the {@link InspectBackpackAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
