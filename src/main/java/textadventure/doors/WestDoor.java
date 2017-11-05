package textadventure.doors;

public class WestDoor extends PropertyDoor
{

	/**
	 * Creates a new {@link PropertyDoor} facing {@link Direction#WEST}.
	 *
	 * @param door The internal instance of {@link Door}.
	 */
	public WestDoor(Door door)
	{
		super(door, Direction.WEST);
	}
}
