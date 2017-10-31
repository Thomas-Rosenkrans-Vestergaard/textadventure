package textadventure.rooms;

import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

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
	 * Performs the {@link InspectFloorAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectFloorAction}.
	 * @param arguments The arguments provided to the {@link InspectFloorAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectFloorAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		this.floor = character.getCurrentLocation().getRoomFloor();
		responses.onInspectFloorAction(character, this);
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
