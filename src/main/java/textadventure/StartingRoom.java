package textadventure;

public class StartingRoom extends BaseRoom
{

	/**
	 * Creates a new {@link StartingRoom}.
	 */
	public StartingRoom()
	{
		super("Starting room", "This is the starting room.");
	}

	/**
	 * Returns the starting message of the {@link StartingRoom}.
	 *
	 * @return The starting message of the {@link StartingRoom}.
	 */
	public String getStartingMessage()
	{
		return "starting message";
	}
}
