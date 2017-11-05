package textadventure.doors;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to open a {@link Door}.
 * <p>
 * The {@link OpenDoorAction} can signal the following exceptions:
 * - {@link DoorAlreadyOpenException}.
 * - {@link DoorLockedException}.
 */
public class OpenDoorAction extends DoorAction
{

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door The {@link Door} to be opened.
	 */
	public OpenDoorAction(PropertyDoor door)
	{
		super(door);
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link OpenDoorAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link OpenDoorAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		try {
			getDoor().open();
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onOpenDoorAction(character, this);
			alertOtherPlayers(character.getCurrentLocation(), character.getFaction().getLeader(),
					secondaryResponses -> responses.onOpenDoorAction(character, this));
		}
	}
}
