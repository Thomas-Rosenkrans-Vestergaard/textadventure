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
	 * {@link ActionPerformCallback} to invoke after performing the {@link LockLockAction}.
	 */
	private ActionPerformCallback<LockLockAction> callback;

	/**
	 * Creates a new {@link LockLockAction}.
	 *
	 * @param lock     The {@link Lock} to execute the {@link LockLockAction} on.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link LockLockAction}.
	 */
	public LockLockAction(Lock lock, ActionPerformCallback<LockLockAction> callback)
	{
		super(lock);

		this.callback = callback;
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
			callback.send(game, player, this);
			return;
		}

		if (state == Lock.State.UNLOCKED) {
			Backpack backpack = player.getCharacter().getBackpack();
			userInterface.select(game, player, new BaseSelect<>(backpack.asOptions(), 1, selection -> {

				try {
					Item item = backpack.getItem(selection.get(0).getOptionIdentifier());

					if (!(item instanceof Key)) {
						outcome = Outcome.SELECTED_NOT_KEY;
						callback.send(game, player, this);
						return;
					}

					Key key = (Key) item;
					lock.lock(key);
					outcome = Outcome.SUCCESS;
					callback.send(game, player, this);

				} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
					throw new IllegalStateException(e);
				} catch (AlreadyLockedException e) {
					outcome = Outcome.ALREADY_LOCKED;
					callback.send(game, player, this);
				} catch (IncorrectKeyException e) {
					outcome = Outcome.INCORRECT_KEY;
					callback.send(game, player, this);
				}
			}));
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
