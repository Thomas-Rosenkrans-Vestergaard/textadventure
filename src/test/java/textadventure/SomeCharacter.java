package textadventure;

import textadventure.items.backpack.Backpack;
import textadventure.rooms.Room;

public class SomeCharacter extends BaseCharacter
{

	public SomeCharacter()
	{
		super(null, null, null, null, null, null, null, null, null, null, null, 100, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public SomeCharacter(Backpack backpack, Room room){
		super(null, null, null, backpack, null, null, null, null, null, null, room, 100, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public SomeCharacter(Player player)
	{
		super(player, null, null, null, null, null, null, null, null, null, null, 100, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public SomeCharacter(String name)
	{
		super(null, name, null, null, null, null, null, null, null, null, null, 100, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public SomeCharacter(Backpack backpack)
	{
		super(null, null, null, backpack, null, null, null, null, null, null, null, 100, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public SomeCharacter(Room room)
	{
		super(null, null, null, null, null, null, null, null, null, null, room, 100, 0, 0, 0, 0, 0, 0, 0, 0);
	}
}
