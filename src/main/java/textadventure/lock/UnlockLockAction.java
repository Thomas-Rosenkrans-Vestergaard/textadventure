package textadventure.lock;

import com.google.common.collect.ImmutableSet;
import textadventure.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.backpack.Backpack;
import textadventure.ui.*;

public class UnlockLockAction extends LockAction
{

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
	 * Performs the {@link UnlockLockAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link UnlockLockAction}.
	 * @param arguments     The arguments provided to the {@link UnlockLockAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Lock.State state = lock.getState();
		if (state == Lock.State.UNLOCKED) {
			setException(new LockAlreadyUnlockedException(lock));
			callback.send(character, this);
			return;
		}

		if (state == Lock.State.LOCKED) {

			Backpack                  backpack = character.getBackpack();
			ImmutableSet<Option<Key>> options  = backpack.asOptions(Key.class);
			Select<Key> select = new BaseSelect<>(options, 1, selection -> {

				try {
					lock.unlock(selection.get(0).getT());
				} catch (Exception e) {
					setException(e);
				}
			});

			try {

				if (arguments.length == 1) {
					select.selectIndex(Integer.parseInt(arguments[0]));
					return;
				}

				gameInterface.select(character, select);

			} catch (UnknownIndexException e) {
				setException(new SelectionNotKeyException());
			} catch (Exception e) {
				setException(e);
			} finally {
				callback.send(character, this);
			}
		}
	}
}
