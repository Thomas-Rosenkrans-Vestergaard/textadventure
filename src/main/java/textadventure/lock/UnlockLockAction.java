package textadventure.lock;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.ui.*;

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

				character.getFaction().getLeader().select(select);

			} catch (SelectResponseException e) {
				setException(e.getCause());
			} catch (UnknownIndexException e) {
				setException(new SelectionNotKeyException());
			} catch (Exception e) {
				setException(e);
			} finally {
				responses.onUnlockLockAction(character, this);
			}
		}
	}
}
