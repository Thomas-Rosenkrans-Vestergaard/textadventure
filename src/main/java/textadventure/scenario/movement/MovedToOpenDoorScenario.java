package textadventure.scenario.movement;

import textadventure.actions.MoveThroughDoorAction;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.Scenario;

public class MovedToOpenDoorScenario extends MovedToDoorScenario
{

	/**
	 * Creates a new {@link MovedToOpenDoorScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} takes place in.
	 * @param door The locked door that was moved to {@link Door}.
	 */
	public MovedToOpenDoorScenario(Room room, Door door)
	{
		super(room, door);

		this.addAction(new MoveThroughDoorAction(door));
	}

	/**
	 * Returns the description of the {@link MovedToOpenDoorScenario}.
	 *
	 * @return The description of the {@link MovedToOpenDoorScenario}.
	 */
	@Override public String getDescription()
	{
		return String.format(
				"You move to the open door in the %sern part of the room.",
				door.getDirection(room).name().toLowerCase()
		);
	}
}
