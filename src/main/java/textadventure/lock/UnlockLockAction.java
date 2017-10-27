package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.SlotOutOfRangeException;
import textadventure.items.backpack.Backpack;
import textadventure.ui.BaseSelect;
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

		/**
		 * Argument is not an int
		 */
		ARGUMENT_NOT_INT,
	}

	/**
	 * The {@link Outcome} of the {@link UnlockLockAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link UnlockLockAction}.
	 */
	private ActionPerformCallback<UnlockLockAction> callback;

	/**
	 * Creates a new {@link UnlockLockAction}.
	 *
	 * @param lock     The {@link Lock} to execute the {@link UnlockLockAction} on.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link UnlockLockAction}.
	 */
	public UnlockLockAction(Lock lock, ActionPerformCallback<UnlockLockAction> callback)
	{
		super(lock);

		this.callback = callback;
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
		GameInterface gameInterface = game.getGameInterface();
		Lock.State    state         = lock.getState();

		if (state == Lock.State.UNLOCKED) {
			outcome = Outcome.ALREADY_UNLOCKED;
			callback.send(game, player, this);
			return;
		}

		if (state == Lock.State.LOCKED) {
			Backpack backpack = player.getCharacter().getBackpack();

			if (arguments.length == 1) {
				withArguments(game, player, backpack, arguments[0]);
				return;
			}

			gameInterface.select(game, player, new BaseSelect<>(backpack.asOptions(), 1, selection -> {
				try {
					Item item = backpack.getItem(selection.get(0).getOptionIndex());
					if (!(item instanceof Key)) {
						outcome = Outcome.SELECTED_NOT_KEY;
						callback.send(game, player, this);
						return;
					}
					Key key = (Key) item;
					lock.unlock(key);
					outcome = Outcome.SUCCESS;
					callback.send(game, player, this);

				} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
					throw new IllegalStateException(e);
				} catch (AlreadyUnlockedException e) {
					outcome = Outcome.ALREADY_UNLOCKED;
					callback.send(game, player, this);
				} catch (IncorrectKeyException e) {
					outcome = Outcome.INCORRECT_KEY;
					callback.send(game, player, this);
				}
			}));

			return;
		}

		throw new UnsupportedOperationException();
	}


	/**
	 * Performs the {@link UnlockLockAction} using the provided argument
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} performing the {@link UnlockLockAction}.
	 * @param backpack The {@link Backpack} the key is used from.
	 * @param argument The arguments provided to the {@link UnlockLockAction}.
	 */
	private void withArguments(Game game, Player player, Backpack backpack, String argument)
	{
		try {
			Item item = backpack.getItem(Integer.parseInt(argument));
			if (!(item instanceof Key)) {
				outcome = Outcome.SELECTED_NOT_KEY;
				callback.send(game, player, this);
				return;
			}
			Key key = (Key) item;
			lock.unlock(key);
			outcome = Outcome.SUCCESS;
			callback.send(game, player, this);

		} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
			throw new IllegalStateException(e);
		} catch (AlreadyUnlockedException e) {
			outcome = Outcome.ALREADY_UNLOCKED;
			callback.send(game, player, this);
		} catch (IncorrectKeyException e) {
			outcome = Outcome.INCORRECT_KEY;
			callback.send(game, player, this);
		} catch (NumberFormatException e) {
			outcome = UnlockLockAction.Outcome.ARGUMENT_NOT_INT;
			callback.send(game, player, this);
		}
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
