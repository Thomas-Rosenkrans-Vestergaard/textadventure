package textadventure.doors;

import textadventure.Game;
import textadventure.Player;

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
	 * Creates a new {@link InspectDoorAction}.
	 *
	 * @param door The {@link Door} to inspect.
	 */
	public InspectDoorAction(Door door)
	{
		super(door);
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
		game.getGameInterface().onDoorInspect(game, player, this);
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
