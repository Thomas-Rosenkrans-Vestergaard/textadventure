package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.ui.GameInterface;

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
	 * Creates a new {@link CloseChestAction}.
	 *
	 * @param chest The {@link Chest} to execute the {@link CloseChestAction} on.
	 */
	public CloseChestAction(Chest chest)
	{
		super(chest);
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
		Chest.State   state         = chest.getState();
		GameInterface userInterface = game.getUserInterface();

		if (state == Chest.State.CLOSED) {
			outcome = Outcome.ALREADY_CLOSED;
			userInterface.onChestClose(game, player, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			try {
				chest.close();
				outcome = Outcome.SUCCESS;
				userInterface.onChestClose(game, player, this);
				return;
			} catch (ChestAlreadyClosedException e) {
				outcome = Outcome.ALREADY_CLOSED;
				userInterface.onChestClose(game, player, this);
				return;
			} catch (ChestLockedException e) {
				outcome = Outcome.LOCKED;
				userInterface.onChestClose(game, player, this);
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
