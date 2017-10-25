package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.ui.GameInterface;

public class LockLockAction extends LockAction
{

	/**
	 * The possible {@link Outcome}s of the {@link LockLockAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Player} successfully locked the {@link Lock}.
		 */
		SUCCESS,

		/**
		 * The {@link Lock} was already {@link Lock.State#LOCKED}.
		 */
		ALREADY_LOCKED,

		/**
		 * The {@link Item} selected from the {@link Player} is not a {@link Key}.
		 */
		SELECTED_NOT_KEY,

		/**
		 * The {@link Key} selected from the {@link Player} does not fit the {@link Lock}.
		 */
		INCORRECT_KEY,
	}

	/**
	 * The {@link Outcome} of the {@link LockLockAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link LockLockAction}.
	 *
	 * @param lock The {@link Lock} to execute the {@link LockLockAction} on.
	 */
	public LockLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link LockLockAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link LockLockAction}.
	 * @param arguments The arguments provided to the {@link LockLockAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		GameInterface userInterface = game.getGameInterface();
		Lock.State    state         = lock.getState();

		if (state == Lock.State.LOCKED) {
			outcome = Outcome.ALREADY_LOCKED;
			userInterface.onLockLock(game, player, this);
			return;
		}

		if (state == Lock.State.UNLOCKED) {
			String   message  = "Select the key to use to lock the lock.";
			Backpack backpack = player.getCharacter().getBackpack();
			/*userInterface.select(message, backpack, player, item -> {

				if (!(item instanceof Key)) {
					outcome = Outcome.SELECTED_NOT_KEY;
					userInterface.onLockLock(game, player, this);
					return;
				}

				try {
					Key key = (Key) item;
					lock.lock(key);
					outcome = Outcome.SUCCESS;
					userInterface.onLockLock(game, player, this);
				} catch (AlreadyLockedException e) {
					outcome = Outcome.ALREADY_LOCKED;
					userInterface.onLockLock(game, player, this);
				} catch (IncorrectKeyException e) {
					outcome = Outcome.INCORRECT_KEY;
					userInterface.onLockLock(game, player, this);
				}
			});*/

			throw new UnsupportedOperationException();
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link LockLockAction}.
	 *
	 * @return The {@link Outcome} of the {@link LockLockAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
