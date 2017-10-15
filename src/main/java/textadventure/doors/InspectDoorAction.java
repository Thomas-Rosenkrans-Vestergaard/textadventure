package textadventure.doors;

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
	InspectDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link InspectDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} executing the {@link InspectDoorAction}.
	 */
	@Override public void perform(Game game, Player player)
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

	/**
	 * Returns the name of the {@link InspectDoorAction}.
	 *
	 * @return The name of the {@link InspectDoorAction}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns the description of the {@link InspectDoorAction}.
	 *
	 * @return The description of the {@link InspectDoorAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the door to learn new information about the door.";
	}
}
