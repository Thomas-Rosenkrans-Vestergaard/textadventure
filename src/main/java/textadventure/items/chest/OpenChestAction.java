package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.ui.GameInterface;

public class OpenChestAction extends ChestAction
{

	/**
	 * The possible {@link Outcome}s of the {@link OpenChestAction}.
	 */
	public enum Outcome
	{
		/**
		 * The {@link Chest} was successfully opened.
		 */
		SUCCESS,

		/**
		 * The {@link Chest} could not be opened, since the {@link Chest} was locked.
		 */
		LOCKED,

		/**
		 * The {@link Chest} was already closed.
		 */
		ALREADY_OPEN,
	}

	/**
	 * The {@link Outcome} of the {@link OpenChestAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest The {@link Chest} opened by the {@link OpenChestAction}.
	 */
	public OpenChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link OpenChestAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link OpenChestAction}.
	 * @param arguments The arguments provided to the {@link OpenChestAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		Chest.State   state         = chest.getState();
		GameInterface userInterface = game.getGameInterface();

		if (state == Chest.State.OPEN) {
			outcome = Outcome.ALREADY_OPEN;
			userInterface.onChestOpen(game, player, this);
			return;
		}

		if (state == Chest.State.CLOSED) {
			try {
				chest.open();
				outcome = Outcome.SUCCESS;
				userInterface.onChestOpen(game, player, this);
				return;
			} catch (ChestAlreadyOpenException e) {
				outcome = Outcome.ALREADY_OPEN;
				userInterface.onChestOpen(game, player, this);
				return;
			} catch (ChestLockedException e) {
				outcome = Outcome.LOCKED;
				userInterface.onChestOpen(game, player, this);
				return;
			}
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link OpenChestAction}.
	 *
	 * @return The {@link Outcome} of the {@link OpenChestAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
