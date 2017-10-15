package textadventure.doors;

import textadventure.Game;
import textadventure.Player;

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
	OpenDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} executing the {@link OpenDoorAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		try {
			getDoor().open();
			this.outcome = Outcome.SUCCESS;
			game.getUserInterface().onDoorOpen(game, player, this);
		} catch (DoorAlreadyOpenException e) {
			this.outcome = Outcome.ALREADY_OPEN;
			game.getUserInterface().onDoorOpen(game, player, this);
		} catch (DoorLockedException e) {
			this.outcome = Outcome.LOCKED;
			game.getUserInterface().onDoorOpen(game, player, this);
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

	/**
	 * Returns the name of the {@link OpenDoorAction}.
	 *
	 * @return The name of the {@link OpenDoorAction}.
	 */
	@Override public String getActionName()
	{
		return "open";
	}

	/**
	 * Returns the description of the {@link OpenDoorAction}.
	 *
	 * @return The description of the {@link OpenDoorAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Open the door.";
	}
}
