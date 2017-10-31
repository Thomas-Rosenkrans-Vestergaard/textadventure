package textadventure.doors;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to gather information about a {@link Door}.
 */
public class InspectDoorAction extends DoorAction
{

	/**
	 * Creates a new {@link InspectDoorAction}.
	 *
	 * @param door The {@link Door} to inspect.
	 */
	public InspectDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link InspectDoorAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectDoorAction}.
	 * @param arguments The arguments provided to the {@link InspectDoorAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectDoorAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		responses.onInspectDoorAction(character, this);
	}
}
