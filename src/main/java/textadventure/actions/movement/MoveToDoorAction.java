package textadventure.actions.movement;

import textadventure.*;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.NewScenarioException;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.movement.MovedToClosedDoorScenario;
import textadventure.scenario.movement.MovedToOpenDoorScenario;
import textadventure.scenario.Scenario;

/**
 * Represents an {@link Action} that moves the {@link Player} in some {@link Direction}.
 */
public abstract class MoveToDoorAction implements Action
{

	/**
	 * Attempts to move the provided {@link Player} in the provided {@link Direction}.
	 *
	 * @param scenario  The {@link Scenario} that the {@link MoveToDoorAction} is a response to.
	 * @param player    The {@link Player} to move.
	 * @param direction The {@link Direction} of the {@link Door} to move to.
	 *
	 * @throws MoveToDoorException When something goes wrong while moving the {@link Player}.
	 */
	public void move(Scenario scenario, Player player, Direction direction) throws ActionException
	{
		Room       room  = player.getCurrentLocation();
		Door       door  = getDoorFromFeatures(room, direction);
		Door.State state = door.getState();

		if (door == null) {
			throw new NoDoorException(scenario, this, player, room, direction);
		}

		if (state == Door.State.OPEN) {
			throw new NewScenarioException(scenario, this, player, player.getCurrentLocation(), new
					MovedToOpenDoorScenario(room, door));
		}

		if (state == Door.State.CLOSED) {
			throw new NewScenarioException(scenario, this, player, player.getCurrentLocation(), new
					MovedToClosedDoorScenario(room, door));
		}

		throw new IllegalStateException();
	}

	/**
	 * Returns the {@link Door} inside the provided {@link Room} with the provided {@link Direction}.
	 *
	 * @param room      The {@link Room} to find the {@link Door} of.
	 * @param direction The {@link Direction} the {@link Door} is pointed in the {@link Room}.
	 *
	 * @return The {@link Door} inside the provided {@link Room} with the provided {@link Direction}. Returns
	 * <code>null</code> when no {@link Door} exists with the provided {@link Direction} in the provided {@link Room}.
	 */
	private Door getDoorFromFeatures(Room room, Direction direction)
	{
		return room.getFeatures()
				   .filter(Door.class::isInstance)
				   .map(Door.class::cast)
				   .filter(door -> door.getDirection(room) == direction)
				   .findFirst()
				   .orElse(null);
	}
}
