package textadventure.doors;

import textadventure.Character;
import textadventure.actions.Action;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

/**
 * {@link Action} that allows a {@link Character} to close a {@link Door}.
 */
public class CloseDoorAction extends DoorAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link CloseDoorAction}.
	 */
	private ActionPerformCallback<CloseDoorAction> callback;

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door     The {@link Door} to be closed.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link CloseDoorAction}.
	 */
	public CloseDoorAction(Door door, ActionPerformCallback<CloseDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link CloseDoorAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link CloseDoorAction}.
	 * @param arguments     The arguments provided to the {@link CloseDoorAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		try {
			getDoor().close();
		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(character, this);
		}
	}
}
