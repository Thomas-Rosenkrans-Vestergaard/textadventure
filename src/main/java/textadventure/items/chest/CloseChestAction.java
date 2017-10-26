package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows the player to close a {@link Chest}.
 */
public class CloseChestAction extends ChestAction
{

	/**
	 * The possible {@link Outcome}s of the {@link CloseChestAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Player} was successful in closing the {@link Chest}.
		 */
		SUCCESS,

		/**
		 * The {@link Chest} was already {@link Chest.State#CLOSED}.
		 */
		ALREADY_CLOSED,

		/**
		 * The {@link Chest} was {@link textadventure.lock.Lock.State#LOCKED}.
		 */
		LOCKED,
	}

	/**
	 * The {@link Outcome} of the {@link CloseChestAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to use as a send after performing the {@link CloseChestAction}.
	 */
	private ActionPerformCallback<CloseChestAction> callback;

	/**
	 * Creates a new {@link CloseChestAction}.
	 *
	 * @param chest    The {@link Chest} to be closed.
	 * @param callback The {@link ActionPerformCallback} to use as a send after performing the {@link CloseChestAction}.
	 */
	CloseChestAction(Chest chest, ActionPerformCallback<CloseChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link CloseChestAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link CloseChestAction}.
	 * @param arguments The arguments provided to the {@link CloseChestAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		Chest.State state = chest.getState();

		if (state == Chest.State.CLOSED) {
			outcome = Outcome.ALREADY_CLOSED;
			callback.send(game, player, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			try {
				chest.close();
				outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
				return;
			} catch (ChestAlreadyClosedException e) {
				outcome = Outcome.ALREADY_CLOSED;
				callback.send(game, player, this);
				return;
			} catch (ChestLockedException e) {
				outcome = Outcome.LOCKED;
				callback.send(game, player, this);
				return;
			}
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link CloseChestAction}.
	 *
	 * @return The {@link Outcome} of the {@link CloseChestAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
