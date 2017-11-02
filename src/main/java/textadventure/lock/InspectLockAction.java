package textadventure.lock;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to gather information about a {@link Lock}.
 */
public class InspectLockAction extends LockAction
{

	/**
	 * Creates a new {@link InspectLockAction}.
	 *
	 * @param lock The {@link Lock} to execute the {@link InspectLockAction} on.
	 */
	public InspectLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link InspectLockAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectLockAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectLockAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		responses.onInspectLockAction(character, this);
	}
}
