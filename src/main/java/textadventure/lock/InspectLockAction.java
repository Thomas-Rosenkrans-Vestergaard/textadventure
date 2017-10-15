package textadventure.lock;

import textadventure.Game;
import textadventure.Player;

public class InspectLockAction extends LockAction
{

	/**
	 * The possible {@link Outcome}s of the {@link InspectLockAction}.
	 */
	public enum Outcome
	{
		SUCCESS,
	}

	/**
	 * The {@link Outcome} of the {@link InspectLockAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link InspectLockAction}.
	 *
	 * @param lock The {@link Lock} to inspect.
	 */
	InspectLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link InspectLockAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} executing the {@link InspectLockAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		outcome = Outcome.SUCCESS;
		game.getUserInterface().onLockInspect(game, player, this);
	}

	/**
	 * Returns the {@link Outcome} of the {@link InspectLockAction}.
	 *
	 * @return The {@link Outcome} of the {@link InspectLockAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the name of the {@link InspectLockAction}.
	 *
	 * @return The name of the {@link InspectLockAction}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns the description of the {@link InspectLockAction}.
	 *
	 * @return The description of the {@link InspectLockAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the lock to learn new information about the lock.";
	}
}
