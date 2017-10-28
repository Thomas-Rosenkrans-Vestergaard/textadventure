package textadventure.rooms;

public class EndingRoom extends BaseRoom
{

	public EndingRoom(String name, String description)
	{
		super(name, description);
	}

	/**
	 * Returns the message of the {@link EndingRoom}.
	 *
	 * @return The message of the {@link EndingRoom}.
	 */
	public String getEndingMessage()
	{
		return "After a challenging journey through a North Korean hell, you finally " +
				"see the end of the tunnel, and the light of day shines upon you.";
	}
}
