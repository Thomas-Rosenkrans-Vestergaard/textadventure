package textadventure.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link Action} that allows a player to close a {@link Door}.
 */
public class CloseDoorAction extends DoorAction
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
	 * {@link ActionPerformCallback} to use as a send after performing the {@link CloseDoorAction}.
	 */
	private ActionPerformCallback<CloseDoorAction> callback;

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door     The {@link Door} to be closed.
	 * @param callback The {@link ActionPerformCallback} to use as a send after performing the {@link CloseDoorAction}.
	 */
	CloseDoorAction(Door door, ActionPerformCallback<CloseDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link CloseDoorAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link CloseDoorAction}.
	 * @param arguments The arguments provided to the {@link CloseDoorAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		try {
			getDoor().close();
			this.outcome = Outcome.SUCCESS;
			callback.send(game, player, this);
		} catch (DoorAlreadyClosedException e) {
			this.outcome = Outcome.ALREADY_CLOSED;
			callback.send(game, player, this);
		} catch (DoorLockedException e) {
			this.outcome = Outcome.LOCKED;
			callback.send(game, player, this);
		}
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
