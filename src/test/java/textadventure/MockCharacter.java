package textadventure;

import textadventure.items.backpack.Backpack;
import textadventure.rooms.Room;

public class MockCharacter extends BaseCharacter
{

	public MockCharacter(Room currentLocation)
	{
		super(null, null, currentLocation);
	}

	public MockCharacter(String name, Backpack backpack, Room currentLocation)
	{
		super(name, backpack, currentLocation);
	}
}
