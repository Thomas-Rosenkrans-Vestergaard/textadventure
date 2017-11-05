package textadventure.lock;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.select.BaseSelect;
import textadventure.select.Option;
import textadventure.select.Select;
import textadventure.select.SelectResponseException;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to {@link Lock#unlock(Key)} a {@link Lock}.
 */
public class UnlockLockAction extends LockAction
{

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
	 * Performs the {@link UnlockLockAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link UnlockLockAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link UnlockLockAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Lock.State state = lock.getState();
		if (state == Lock.State.UNLOCKED) {
			setException(new LockAlreadyUnlockedException(lock));
			responses.onUnlockLockAction(character, this);
			return;
		}

		if (state == Lock.State.LOCKED) {

			Backpack                  backpack = character.getBackpack();
			ImmutableSet<Option<Key>> options  = backpack.asOptions(Key.class);

			try {

				Select<Key> select = new BaseSelect<>(options, 1, selection -> {
					lock.unlock(selection.get(0).getT());
				});

				responses.select(select);

			} catch (SelectResponseException e) {
				setException(e.getCause());
			} catch (Exception e) {
				setException(e);
			} finally {
				responses.onUnlockLockAction(character, this);
			}
		}
	}
}
