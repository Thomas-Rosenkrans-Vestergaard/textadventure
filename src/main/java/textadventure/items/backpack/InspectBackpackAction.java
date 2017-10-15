package textadventure.items.backpack;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.NamedAction;
import textadventure.ui.UserInterface;

public class InspectBackpackAction extends BackpackAction implements NamedAction
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
	 * Creates a new {@link InspectBackpackAction}.
	 *
	 * @param backpack The {@link Backpack} to execute {@link InspectBackpackAction} on.
	 */
	public InspectBackpackAction(Backpack backpack)
	{
		super(backpack);
	}

	/**
	 * Performs the {@link InspectBackpackAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link InspectBackpackAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		UserInterface userInterface = game.getUserInterface();
		outcome = Outcome.SUCCESS;
		userInterface.onBackpackInspect(game, player, this);
	}

	/**
	 * Returns the name of the {@link InspectBackpackAction}.
	 *
	 * @return The name of the {@link InspectBackpackAction}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns the description of the {@link InspectBackpackAction}.
	 *
	 * @return The description of the {@link InspectBackpackAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the backpack.";
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
