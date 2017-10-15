package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.NamedAction;
import textadventure.ui.UserInterface;

public class InspectChestAction extends ChestAction implements NamedAction
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
	 * Creates a new {@link InspectChestAction}.
	 *
	 * @param chest The {@link Chest} to execute the {@link InspectChestAction} on.
	 */
	public InspectChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link InspectChestAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link InspectChestAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		Chest.State   state         = chest.getState();
		UserInterface userInterface = game.getUserInterface();

		if (state == Chest.State.CLOSED) {
			outcome = Outcome.CLOSED;
			userInterface.onChestInspect(game, player, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			outcome = Outcome.SUCCESS;
			userInterface.onChestInspect(game, player, this);
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

	/**
	 * Returns the name of the {@link InspectChestAction}.
	 *
	 * @return The name of the {@link InspectChestAction}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns the description of the {@link InspectChestAction}.
	 *
	 * @return The description of the {@link InspectChestAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the contents of the chest.";
	}
}
