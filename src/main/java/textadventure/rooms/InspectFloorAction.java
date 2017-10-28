package textadventure.rooms;

import textadventure.Character;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to inspect the {@link Floor} revealing its
 * {@link textadventure.items.Inventory}.
 */
public class InspectFloorAction extends AbstractAction
{

	/**
	 * The {@link Floor} being inspected.
	 */
	private Floor floor;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link InspectFloorAction}.
	 */
	private ActionPerformCallback<InspectFloorAction> callback;

	/**
	 * Creates a new {@link InspectFloorAction}.
	 *
	 * @param floor    The {@link Floor} being inspected.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectFloorAction}.
	 */
	public InspectFloorAction(Floor floor, ActionPerformCallback<InspectFloorAction> callback)
	{
		this.floor = floor;
		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectFloorAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link InspectFloorAction}.
	 * @param arguments     The arguments provided to the {@link InspectFloorAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		callback.send(character, this);
	}

	/**
	 * Returns the {@link Floor} that was acted upon.
	 *
	 * @return The {@link Floor} that was acted upon.
	 */
	public Floor getFloor()
	{
		return this.floor;
	}
}
