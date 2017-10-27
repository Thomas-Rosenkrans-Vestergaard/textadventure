package textadventure.lock;

import com.google.common.collect.ImmutableSet;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.SlotOutOfRangeException;
import textadventure.items.backpack.Backpack;
import textadventure.ui.*;

import java.util.List;

public class LockLockAction extends LockAction
{

	/**
	 * The possible {@link Outcome}s of the {@link LockLockAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Lock} was already {@link Lock.State#LOCKED}.
		 */
		ALREADY_LOCKED,

		/**
		 * Argument is not an int
		 */
		ARGUMENT_NOT_INT,

		/**
		 * The {@link Item} selected from the {@link Player} is not a {@link Key}.
		 */
		SELECTED_NOT_KEY,

		/**
		 * The {@link Key} selected from the {@link Player} does not fit the {@link Lock}.
		 */
		INCORRECT_KEY,

		/**
		 * The {@link Player} successfully locked the {@link Lock}.
		 */
		SUCCESS,
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
		GameInterface gameInterface = game.getGameInterface();
		Lock.State    state         = lock.getState();

		if (state == Lock.State.LOCKED) {
			outcome = Outcome.ALREADY_LOCKED;
			callback.send(game, player, this);
			return;
		}

		if (state == Lock.State.UNLOCKED) {

			Backpack backpack = player.getCharacter().getBackpack();

			if (arguments.length == 1) {
				withArguments(game, player, backpack, arguments[0]);
				return;
			}

			ImmutableSet<Option<Key>> options = backpack.asOptions(Key.class);
			gameInterface.select(game, player, new BaseSelect<>(options, 1, selection -> {
				try {
					lock.lock(selection.get(0).getT());
					outcome = Outcome.SUCCESS;
					callback.send(game, player, this);
				} catch (AlreadyLockedException e) {
					outcome = Outcome.ALREADY_LOCKED;
					callback.send(game, player, this);
				} catch (IncorrectKeyException e) {
					outcome = Outcome.INCORRECT_KEY;
					callback.send(game, player, this);
				}
			}));
		}
	}

	/**
	 * Performs the {@link LockLockAction} using the provided argument
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} performing the {@link LockLockAction}.
	 * @param backpack The {@link Backpack} the key is used from.
	 * @param argument The arguments provided to the {@link LockLockAction}.
	 */
	private void withArguments(Game game, Player player, Backpack backpack, String argument)
	{
		try {

			ImmutableSet<Option<Key>> options = backpack.asOptions(Key.class);
			Select<Key> select = new BaseSelect<>(options, 1, selection -> {
				try {
					lock.lock(selection.get(0).getT());
					outcome = Outcome.SUCCESS;
					callback.send(game, player, this);
				} catch (AlreadyLockedException e) {
					outcome = Outcome.ALREADY_LOCKED;
					callback.send(game, player, this);
				} catch (IncorrectKeyException e) {
					outcome = Outcome.INCORRECT_KEY;
					callback.send(game, player, this);
				}
			});

			select.selectIndex(Integer.parseInt(argument));

		} catch (NumberFormatException e) {
			outcome = LockLockAction.Outcome.ARGUMENT_NOT_INT;
			callback.send(game, player, this);
		} catch (SelectionAmountOutOfBounds e) {

		} catch (UnknownIndexException e) {

		}
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
