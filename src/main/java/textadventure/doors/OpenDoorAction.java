package textadventure.doors;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to open a {@link Door}.
 */
public class OpenDoorAction extends DoorAction
{

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door The {@link Door} to be opened.
	 */
	public OpenDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link OpenDoorAction}.
	 * @param arguments The arguments provided to the {@link OpenDoorAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link OpenDoorAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		try {
			getDoor().open();
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onOpenDoorAction(character, this);
		}
	}
}
