package textadventure.actions.move;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.NewScenarioException;
import textadventure.rooms.Room;
import textadventure.rooms.features.RoomFeature;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.ClosedDoorScenario;
import textadventure.scenario.Scenario;

import java.util.List;

/**
 * Represents an {@link Action} that moves the {@link Player} in some {@link Direction}.
 */
public abstract class MoveAction implements Action
{

	/**
	 * Attempts to move the provided {@link Player} in the provided {@link Direction}.
	 *
	 * @param scenario  The {@link Scenario} that the {@link Action} is a response to.
	 * @param player    The {@link Player} to move.
	 * @param direction The {@link Direction} to move in.
	 *
	 * @throws MoveActionException When something goes wrong while moving the {@link Player}.
	 */
	public void move(Scenario scenario, Player player, Direction direction) throws ActionException
	{
		Room              room     = player.getCurrentLocation();
		List<RoomFeature> features = room.getFeatures();
		Door              door     = getDoorFromFeatures(features, room, direction);
		Door.State        state    = door.getState();

		if (door == null) {
			throw new NoDoorException(scenario, this, player, room, direction);
		}

		if (state == Door.State.OPEN) {
			player.setCurrentLocation(door.getOtherSide(room));
			return;
		}

		if (state == Door.State.CLOSED) {
			throw new NewScenarioException(scenario, this, player, new ClosedDoorScenario(door, room));
		}

		throw new IllegalStateException();
	}

	private Door getDoorFromFeatures(List<RoomFeature> features, Room room, Direction direction)
	{
		for (RoomFeature feature : features) {
			if (feature instanceof Door) {
				Door door = (Door) feature;
				if (door.getDirection(room) == direction) {
					return door;
				}
			}
		}

		return null;
	}
}
