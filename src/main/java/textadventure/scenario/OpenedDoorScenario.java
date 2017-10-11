package textadventure.scenario;

import textadventure.actions.MoveThroughDoorAction;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.doors.Lock;
import textadventure.scenario.movement.MovedToLockedDoorScenario;

public class OpenedDoorScenario extends AbstractScenario
{

	/**
	 * The locked {@link Door} that was moved to.
	 */
	private Door door;

	/**
	 * Creates a new {@link MovedToLockedDoorScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} takes place in.
	 * @param door The locked door that was moved to {@link Door}.
	 */
	public OpenedDoorScenario(Room room, Door door)
	{
		super(room);

		if (door.getLock().getState() == Lock.State.LOCKED) {
			throw new IllegalArgumentException("Cannot provide an unlocked lock to LockedDoorScenario.");
		}

		this.door = door;
		this.addAction(new MoveThroughDoorAction(door));
	}

	/**
	 * Returns the description of the {@link MovedToLockedDoorScenario}.
	 *
	 * @return The description of the {@link MovedToLockedDoorScenario}.
	 */
	@Override public String getDescription()
	{
		return String.format(
				"You move to the closed door in the %sern part of the room, but discover that the door is locked.",
				door.getDirection(room).name().toLowerCase()
		);
	}
}
