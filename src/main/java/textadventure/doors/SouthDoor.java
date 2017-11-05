package textadventure.doors;

public class SouthDoor extends PropertyDoor
{

	/**
	 * Creates a new {@link PropertyDoor} facing {@link Direction#SOUTH}.
	 *
	 * @param door The internal instance of {@link Door}.
	 */
	public SouthDoor(Door door)
	{
		super(door, Direction.SOUTH);
	}
}
