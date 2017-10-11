package textadventure.scenario.movement;

import textadventure.actions.InspectLockAction;
import textadventure.actions.UnlockDoorAction;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.doors.Lock;
import textadventure.scenario.Scenario;

public class MovedToLockedDoorScenario extends MovedToDoorScenario
{

	/**
	 * Creates a new {@link MovedToLockedDoorScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} takes place in.
	 * @param door The locked door that was moved to {@link Door}.
	 */
	public MovedToLockedDoorScenario(Room room, Door door)
	{
		super(room, door);

		if (door.getLock().getState() == Lock.State.LOCKED) {
			throw new IllegalArgumentException("Cannot provide an unlocked lock to LockedDoorScenario.");
		}

		this.addAction(new UnlockDoorAction(door));
		this.addAction(new InspectLockAction(door.getLock()));
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
