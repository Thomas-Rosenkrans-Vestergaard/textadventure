package textadventure.lock;

import com.google.common.collect.ImmutableSet;
import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.backpack.Backpack;
import textadventure.ui.*;

/**
 * {@link textadventure.actions.Action} that allows a {@link textadventure.Character} to {@link Lock#lock(Key)} a
 * {@link Lock}.
 */
public class LockLockAction extends LockAction
{

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
	 * Performs the {@link LockLockAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link LockLockAction}.
	 * @param arguments The arguments provided to the {@link LockLockAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		GameInterface gameInterface = game.getGameInterface();
		Lock.State    state         = lock.getState();

		if (state == Lock.State.LOCKED) {
			setException(new LockAlreadyLockedException(lock));
			callback.send(game, character, this);
			return;
		}

		if (state == Lock.State.UNLOCKED) {

			Backpack                  backpack = character.getBackpack();
			ImmutableSet<Option<Key>> options  = backpack.asOptions(Key.class);
			Select<Key> select = new BaseSelect<>(options, 1, selection -> {

				try {
					lock.lock(selection.get(0).getT());
				} catch (Exception e) {
					setException(e);
				}
			});

			try {

				if (arguments.length == 1) {
					select.selectIndex(Integer.parseInt(arguments[0]));
					return;
				}

				gameInterface.select(game, character, select);

			} catch (UnknownIndexException e) {
				setException(new SelectionNotKeyException());
			} catch (Exception e) {
				setException(e);
			} finally {
				callback.send(game, character, this);
			}
		}
	}
}
