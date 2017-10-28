package textadventure.doors;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.Action;
import textadventure.actions.ActionPerformCallback;

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
	CloseDoorAction(Door door, ActionPerformCallback<CloseDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link Action} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link Action}.
	 * @param arguments The arguments provided to the {@link Action}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		try {
			getDoor().close();
		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(game, character, this);
		}
	}
}
