package textadventure.doors;

import textadventure.actions.Action;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.NamedAction;

public class CloseDoorAction extends DoorAction implements NamedAction
{

	/**
	 * The possible {@link Outcome}s of the {@link CloseDoorAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Door} was closed.
		 */
		SUCCESS,

		/**
		 * The {@link Door} could not be closed, since the {@link Door} is locked.
		 */
		LOCKED,

		/**
		 * The {@link Door} was already closed.
		 */
		ALREADY_CLOSED,
	}

	/**
	 * The {@link Outcome} of the {@link CloseDoorAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link CloseDoorAction}.
	 *
	 * @param door The {@link Door} to be closed.
	 */
	public CloseDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} executing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player)
	{
		try {
			getDoor().close();
			this.outcome = Outcome.SUCCESS;
			game.getUserInterface().onDoorClose(game, player, this);
		} catch (DoorAlreadyClosedException e) {
			this.outcome = Outcome.ALREADY_CLOSED;
			game.getUserInterface().onDoorClose(game, player, this);
		} catch (DoorLockedException e) {
			this.outcome = Outcome.LOCKED;
			game.getUserInterface().onDoorClose(game, player, this);
		}
	}

	/**
	 * Returns the name of the {@link CloseDoorAction}.
	 *
	 * @return The name of the {@link CloseDoorAction}.
	 */
	@Override public String getActionName()
	{
		return "close";
	}

	/**
	 * Returns the description of the {@link CloseDoorAction}.
	 *
	 * @return The description of the {@link CloseDoorAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Attempt to close the door.";
	}

	/**
	 * Returns the {@link Outcome} of the {@link CloseDoorAction}.
	 *
	 * @return The {@link Outcome} of the {@link CloseDoorAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
