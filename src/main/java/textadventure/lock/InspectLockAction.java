package textadventure.lock;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to gather information about a {@link Lock}.
 */
public class InspectLockAction extends LockAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link InspectLockAction}.
	 */
	private ActionPerformCallback<InspectLockAction> callback;

	/**
	 * Creates a new {@link InspectLockAction}.
	 *
	 * @param lock     The {@link Lock} to execute the {@link InspectLockAction} on.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectLockAction}.
	 */
	public InspectLockAction(Lock lock, ActionPerformCallback<InspectLockAction> callback)
	{
		super(lock);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectLockAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link InspectLockAction}.
	 * @param arguments The arguments provided to the {@link InspectLockAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		callback.send(game, character, this);
	}
}
