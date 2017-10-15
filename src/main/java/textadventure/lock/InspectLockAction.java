package textadventure.lock;

import textadventure.ActionException;
import textadventure.Game;
import textadventure.Player;

public class InspectLockAction extends LockAction
{
	public enum Outcome
	{
		SUCCESS,
	}

	private Outcome outcome;

	public InspectLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		outcome = Outcome.SUCCESS;
		game.getUserInterface().onLockInspect(game, player, this);
	}

	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
