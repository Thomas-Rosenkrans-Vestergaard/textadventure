package textadventure.doors;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.rooms.Room;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to use a {@link Door} to move to another
 * {@link Room}.
 * <p>
 * The {@link UseDoorAction} can signal the following exceptions:
 * - {@link DoorClosedException}.
 */
public class UseDoorAction extends DoorAction
{

	/**
	 * Creates a new {@link UseDoorAction}.
	 *
	 * @param door The {@link Door} to be used.
	 */
	public UseDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link UseDoorAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link UseDoorAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link UseDoorAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Door       door        = getDoor();
		Room       currentRoom = character.getCurrentLocation();
		Room       targetRoom  = door.getInverseRoom(currentRoom);
		Door.State state       = door.getState();

		if (!currentRoom.hasCharacter(character))
			throw new IllegalStateException();

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		if (state == Door.State.CLOSED) {
			setException(new DoorClosedException(door));
		}

		if (state == Door.State.OPEN) {
			currentRoom.removeCharacter(character);
			character.setCurrentLocation(targetRoom);
			targetRoom.addCharacter(character);
		}

		responses.onUseDoorAction(character, this);
	}
}
