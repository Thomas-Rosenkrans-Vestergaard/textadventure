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
	public InspectLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link InspectLockAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link InspectLockAction}.
	 * @param arguments The arguments provided to the {@link InspectLockAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		outcome = Outcome.SUCCESS;
		game.getGameInterface().onLockInspect(game, player, this);
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
}
