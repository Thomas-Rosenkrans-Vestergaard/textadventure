package textadventure;

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
		return "ending message";
	}
}
