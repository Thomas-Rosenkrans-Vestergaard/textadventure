package textadventure.scenario.movement;

import textadventure.actions.OpenDoorAction;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.Scenario;

public class MovedToClosedDoorScenario extends MovedToDoorScenario
{

	/**
	 * Creates a new {@link MovedToClosedDoorScenario}.
	 *
	 * @param room The {@link Room} where the {@link Scenario} takes place.
	 * @param door The {@link Door} that was moved to.
	 */
	public MovedToClosedDoorScenario(Room room, Door door)
	{
		super(room, door);
		this.addAction(new OpenDoorAction(door));
	}

	/**
	 * Returns the description of the {@link MovedToClosedDoorScenario}.
	 *
	 * @return The description of the {@link MovedToClosedDoorScenario}.
	 */
	@Override public String getDescription()
	{
		return String.format(
				"You move to the closed door in the %sern part of the room.",
				door.getDirection(room).name().toLowerCase()
		);
	}
}
