package textadventure.scenario;

import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;

public class LockedDoorScenario extends AbstractScenario
{
	public LockedDoorScenario(Room room, Door door)
	{
		super(room, "The door you tried to open is locked. To open the door you first need to unlock the door with a" +
					" key with the code " + door.getLock().getCode() + ".");
	}
}
