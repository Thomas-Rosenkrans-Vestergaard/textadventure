package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.UnknownItemSlotException;
import textadventure.items.backpack.Backpack;
import textadventure.ui.GameInterface;

public class UnlockLockAction extends LockAction
{

	/**
	 * The possible {@link Outcome}s of the {@link UnlockLockAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Player} successfully unlocked the {@link Lock}.
		 */
		SUCCESS,

		/**
		 * The {@link Lock} was already {@link Lock.State#UNLOCKED}.
		 */
		ALREADY_UNLOCKED,

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
	 * The {@link Outcome} of the {@link UnlockLockAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link UnlockLockAction}.
	 *
	 * @param lock The {@link Lock} to execute the {@link UnlockLockAction} on.
	 */
	public UnlockLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link UnlockLockAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link UnlockLockAction}.
	 * @param arguments The arguments provided to the {@link UnlockLockAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		GameInterface userInterface = game.getGameInterface();
		Lock.State    state         = lock.getState();

		if (state == Lock.State.UNLOCKED) {
			outcome = Outcome.ALREADY_UNLOCKED;
			userInterface.onLockUnlock(game, player, this);
			return;
		}

		if (state == Lock.State.LOCKED) {
			Backpack backpack = player.getCharacter().getBackpack();
			userInterface.select(backpack, player, choice -> {
				try {
					Item item = backpack.getItem(choice);
					if (!(item instanceof Key)) {
						outcome = Outcome.SELECTED_NOT_KEY;
						userInterface.onLockUnlock(game, player, this);
						return;
					}
					Key key = (Key) item;
					lock.unlock(key);
					outcome = Outcome.SUCCESS;
					userInterface.onLockUnlock(game, player, this);
				} catch (UnknownItemSlotException | NotEnoughItemsException e) {
					throw new IllegalStateException(e);
				} catch (AlreadyUnlockedException e) {
					outcome = Outcome.ALREADY_UNLOCKED;
					userInterface.onLockUnlock(game, player, this);
				} catch (IncorrectKeyException e) {
					outcome = Outcome.INCORRECT_KEY;
					userInterface.onLockUnlock(game, player, this);
				}
			});

			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link UnlockLockAction}.
	 *
	 * @return The {@link Outcome} of the {@link UnlockLockAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
