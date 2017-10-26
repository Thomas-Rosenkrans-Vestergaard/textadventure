package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows the player to inspect the {@link Chest}.
 */
public class InspectChestAction extends ChestAction
{

	/**
	 * The possible {@link Outcome}s of the {@link InspectChestAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Chest} was successfully inspected.
		 */
		SUCCESS,

		/**
		 * The {@link Chest} was {@link Chest.State#CLOSED}, and could not be inspected.
		 */
		CLOSED,
	}

	/**
	 * The {@link Outcome} of the {@link InspectChestAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to use as a send after performing the {@link InspectChestAction}.
	 */
	private ActionPerformCallback<InspectChestAction> callback;

	/**
	 * Creates a new {@link InspectChestAction}.
	 *
	 * @param chest    The {@link Chest} to be inspected.
	 * @param callback The {@link ActionPerformCallback} to use as a send after performing the {@link InspectChestAction}.
	 */
	InspectChestAction(Chest chest, ActionPerformCallback<InspectChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectChestAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link InspectChestAction}.
	 * @param arguments The arguments provided to the {@link InspectChestAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		Chest.State   state         = chest.getState();

		if (state == Chest.State.CLOSED) {
			outcome = Outcome.CLOSED;
			callback.send(game, player, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			outcome = Outcome.SUCCESS;
			callback.send(game, player, this);
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link InspectChestAction}.
	 *
	 * @return The {@link Outcome} of the {@link InspectChestAction}
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
