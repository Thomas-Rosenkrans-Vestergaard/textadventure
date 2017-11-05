package textadventure.doors;

public class EastDoor extends PropertyDoor
{
	/**
	 * Creates a new {@link PropertyDoor} facing {@link Direction#EAST}.
	 *
	 * @param door The internal instance of {@link Door}.
	 */
	public EastDoor(Door door)
	{
		super(door, Direction.EAST);
	}
}
