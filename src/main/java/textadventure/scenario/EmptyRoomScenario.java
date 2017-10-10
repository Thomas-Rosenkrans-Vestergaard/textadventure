package textadventure.scenario;

import textadventure.rooms.Room;

public class EmptyRoomScenario extends AbstractScenario
{
	public EmptyRoomScenario(Room room)
	{
		super(room, "The room you're standing in is completely empty.");
	}
}
