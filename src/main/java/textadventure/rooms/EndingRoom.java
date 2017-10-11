package textadventure.rooms;

public class EndingRoom extends BaseRoom
{

	/**
	 * Creates a new {@link EndingRoom}.
	 */
	public EndingRoom()
	{
		super("Ending Room", "This is the ending room.");
	}

	/**
	 * Returns the message of the {@link EndingRoom}.
	 *
	 * @return The message of the {@link EndingRoom}.
	 */
	public String getEndingMessage()
	{
		return "After a challenging jouney through a North Korean hell, you finally " +
			   "see the end of the tunnel, and the light of day shines upon you.";
	}
}
