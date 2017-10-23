package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.NamedAction;
import textadventure.ui.GameInterface;

public class OpenChestAction extends ChestAction implements NamedAction
{
	public enum Outcome
	{
		SUCCESS,
		ALREADY_OPEN,
		LOCKED,
	}

	private Outcome outcome;

	public OpenChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link OpenChestAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link OpenChestAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		Chest.State   state         = chest.getState();
		GameInterface userInterface = game.getUserInterface();

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

	/**
	 * Returns the name of the {@link OpenChestAction}.
	 *
	 * @return The name of the {@link OpenChestAction}.
	 */
	@Override public String getActionName()
	{
		return "open";
	}

	/**
	 * Returns the description of the {@link OpenChestAction}.
	 *
	 * @return The description of the {@link OpenChestAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Open the chest.";
	}
}
