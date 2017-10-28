package textadventure.doors;

import textadventure.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to use a {@link Door} to move to another
 * {@link Room}.
 */
public class UseDoorAction extends DoorAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link UseDoorAction}.
	 */
	private ActionPerformCallback<UseDoorAction> callback;

	/**
	 * Creates a new {@link UseDoorAction}.
	 *
	 * @param door     The {@link Door} to be used.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link UseDoorAction}.
	 */
	UseDoorAction(Door door, ActionPerformCallback<UseDoorAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link UseDoorAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link UseDoorAction}.
	 * @param arguments     The arguments provided to the {@link UseDoorAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Door       door        = getDoor();
		Room       currentRoom = character.getCurrentLocation();
		Room       targetRoom  = door.getInverseRoom(currentRoom);
		Door.State state       = door.getState();

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		if (state == Door.State.CLOSED) {
			setException(new DoorClosedException(door));
		}

		if (state == Door.State.OPEN) {
			character.setCurrentLocation(targetRoom);
		}

		callback.send(character, this);
	}
}
