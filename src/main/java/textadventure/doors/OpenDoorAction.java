package textadventure.doors;

import textadventure.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to open a {@link Door}.
 */
public class OpenDoorAction extends DoorAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link OpenDoorAction}.
	 */
	private ActionPerformCallback<OpenDoorAction> callback;

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door     The {@link Door} to be opened.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link OpenDoorAction}.
	 */
	public OpenDoorAction(Door door, ActionPerformCallback<OpenDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link OpenDoorAction}.
	 * @param arguments     The arguments provided to the {@link OpenDoorAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		try {
			getDoor().open();
		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(character, this);
		}
	}
}
