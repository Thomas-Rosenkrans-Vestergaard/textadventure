package textadventure.doors;

import textadventure.Game;
import textadventure.Player;

/**
 * {@link textadventure.actions.Action} that allows a player to open a {@link Door}.
 */
public class OpenDoorAction extends DoorAction
{

	/**
	 * The possible {@link Outcome}s of the {@link OpenDoorAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Door} was successfully opened.
		 */
		SUCCESS,

		/**
		 * The {@link Door} could not be opened, since the {@link Door} was locked.
		 */
		LOCKED,

		/**
		 * The {@link Door} was already closed.
		 */
		ALREADY_OPEN,
	}

	/**
	 * The {@link Outcome} of the {@link OpenDoorAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door The {@link Door} to be opened.
	 */
	public OpenDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link OpenDoorAction}.
	 * @param arguments The arguments provided to the {@link OpenDoorAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		try {
			getDoor().open();
			this.outcome = Outcome.SUCCESS;
			game.getGameInterface().onDoorOpen(game, player, this);
		} catch (DoorAlreadyOpenException e) {
			this.outcome = Outcome.ALREADY_OPEN;
			game.getGameInterface().onDoorOpen(game, player, this);
		} catch (DoorLockedException e) {
			this.outcome = Outcome.LOCKED;
			game.getGameInterface().onDoorOpen(game, player, this);
		}
	}

	/**
	 * Returns the {@link Outcome} of the {@link OpenDoorAction}.
	 *
	 * @return The {@link Outcome} of the {@link OpenDoorAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
