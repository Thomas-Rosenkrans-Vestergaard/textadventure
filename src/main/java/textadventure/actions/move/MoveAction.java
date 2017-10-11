package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.rooms.Room;
import textadventure.rooms.features.RoomFeature;
import textadventure.rooms.features.doors.Door;

import java.util.List;

/**
 * Represents an {@link Action} that moves the {@link Player} in some {@link Direction}.
 */
public abstract class MoveAction implements Action
{

	/**
	 * Attempts to move the provided {@link Player} in the provided {@link Direction}.
	 *
	 * @param controller The {@link GameController}.
	 * @param player     The {@link Player} to move.
	 * @param direction  The {@link Direction} to move in.
	 *
	 * @throws MoveActionException
	 */
	public void move(GameController controller, Player player, Direction direction) throws MoveActionException
	{
		Room              room     = player.getCurrentLocation();
		List<RoomFeature> features = room.getFeatures();
		for (RoomFeature feature : features) {
			if (feature instanceof Door) {
				Door door = (Door) feature;
				if (door.getDirection(room) == direction) {

					if (door.getState() == Door.State.LOCKED) {
						throw new UnsupportedOperationException("Locked door");
					}

					if (door.getState() == Door.State.CLOSED) {
						throw new UnsupportedOperationException("Closed door");
					}

					if (door.getState() == Door.State.OPEN) {
						player.setCurrentLocation(door.getOtherSide(room));
						return;
					}

					throw new IllegalStateException();
				}
			}
		}
	}
}
