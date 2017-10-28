package textadventure.doors;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to gather information about a {@link Door}.
 */
public class InspectDoorAction extends DoorAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link InspectDoorAction}.
	 */
	private ActionPerformCallback<InspectDoorAction> callback;

	/**
	 * Creates a new {@link InspectDoorAction}.
	 *
	 * @param door     The {@link Door} to inspect.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectDoorAction}.
	 */
	public InspectDoorAction(Door door, ActionPerformCallback<InspectDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectDoorAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link InspectDoorAction}.
	 * @param arguments The arguments provided to the {@link InspectDoorAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		callback.send(game, character, this);
	}
}
