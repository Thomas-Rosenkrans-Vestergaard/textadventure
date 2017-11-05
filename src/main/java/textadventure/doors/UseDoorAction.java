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
	 * The {@link Room} the {@link Character} started in.
	 */
	private Room initialRoom;

	/**
	 * The {@link Room} the {@link Character} ended in.
	 */
	private Room targetRoom;

	/**
	 * Creates a new {@link UseDoorAction}.
	 *
	 * @param door The {@link PropertyDoor} to be used.
	 */
	public UseDoorAction(PropertyDoor door)
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

		this.initialRoom = currentRoom;
		this.targetRoom = targetRoom;

		if (!currentRoom.hasCharacter(character))
			throw new IllegalStateException();

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		if (state == Door.State.CLOSED) {
			setException(new DoorClosedException(door));
		}

		if (state == Door.State.OPEN) {
			alertOtherPlayers(character.getCurrentLocation(), character.getFaction().getLeader(),
					secondaryResponses -> responses.onUseDoorExit(character, this));
			currentRoom.removeCharacter(character);
			character.setCurrentLocation(targetRoom);
			targetRoom.addCharacter(character);
			alertOtherPlayers(character.getCurrentLocation(), character.getFaction().getLeader(),
					secondaryResponses -> responses.onUseDoorExit(character, this));
		}

		responses.onUseDoorAction(character, this);
	}

	/**
	 * Returns the {@link Room} the {@link Character} started in.
	 *
	 * @return The {@link Room} the {@link Character} started in.
	 */
	public Room getInitialRoom()
	{
		return this.initialRoom;
	}

	/**
	 * Returns the {@link Room} the {@link Character} ended in.
	 *
	 * @return The {@link Room} the {@link Character} ended in.
	 */
	public Room getTargetRoom()
	{
		return this.targetRoom;
	}
}
