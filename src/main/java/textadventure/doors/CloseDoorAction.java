package textadventure.doors;

import textadventure.actions.Action;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link Action} that allows a {@link Character} to close a {@link Door}.
 * <p>
 * The {@link CloseDoorAction} can signal the following exceptions:
 * - {@link DoorAlreadyClosedException}.
 * - {@link DoorLockedException}.
 */
public class CloseDoorAction extends DoorAction
{

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door The {@link Door} to be closed.
	 */
	public CloseDoorAction(PropertyDoor door)
	{
		super(door);
	}

	/**
	 * Performs the {@link CloseDoorAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link CloseDoorAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link CloseDoorAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		try {
			getDoor().close();
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onCloseDoorAction(character, this);
			alertOtherPlayers(character.getCurrentLocation(), character.getFaction().getLeader(),
					secondaryResponses -> responses.onCloseDoorAction(character, this));
		}
	}
}
