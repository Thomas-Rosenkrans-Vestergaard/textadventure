package textadventure.doors;

import textadventure.ActionException;
import textadventure.Game;
import textadventure.Player;

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
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link InspectDoorAction}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		outcome = Outcome.SUCCESS;
		game.getUserInterface().onDoorInspect(game, player, this);
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
