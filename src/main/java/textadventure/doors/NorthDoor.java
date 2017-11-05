package textadventure.doors;

public class NorthDoor extends PropertyDoor
{

	/**
	 * Creates a new {@link PropertyDoor} facing {@link Direction#NORTH}.
	 *
	 * @param door The internal instance of {@link Door}.
	 */
	public NorthDoor(Door door)
	{
		super(door, Direction.NORTH);
	}
}
