package textadventure.lock;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.ui.BaseSelect;
import textadventure.ui.Option;
import textadventure.ui.Select;
import textadventure.ui.SelectResponseException;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to {@link Lock#lock(Key)} a
 * {@link Lock}.
 */
public class LockLockAction extends LockAction
{

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
	 * Performs the {@link LockLockAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link LockLockAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link LockLockAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		try {

			Lock.State state = lock.getState();

			if (state == Lock.State.LOCKED) {
				setException(new LockAlreadyLockedException(lock));
				responses.onLockLockAction(character, this);
				return;
			}

			if (state == Lock.State.UNLOCKED) {

				Backpack                  backpack = character.getBackpack();
				ImmutableSet<Option<Key>> options  = backpack.asOptions(Key.class);
				Select<Key> select = new BaseSelect<>(options, 1, selection -> {
					lock.lock(selection.get(0).getT());
					responses.onLockLockAction(character, this);
				});

				responses.select(select);
			}

		} catch (SelectResponseException e) {
			setException(e.getCause());
			responses.onLockLockAction(character, this);
		} catch (Exception e) {
			setException(e);
			responses.onLockLockAction(character, this);
		}
	}
}
