package textadventure.rooms;

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
		return "You're being hunted by the State Security Department of North Korea after having escaped from the " +
			   "Hoeryong concentration camp. \nWith you is your wife and two kids. If you get caught, you will " +
			   "surely " +
			   "be" +
			   " executed. The only acceptable outcome is escape.\n" +
			   "Miraculously you've made it to the Demilitarized Zone without being caught. But crossing the " +
			   "Demilitarized Zone is a much greater challenge. \nLuckily you spot the entrance to a tunnel " +
			   "apparently" +
			   " " +
			   "leading under the Demilitarized Zone. \nYou decide that the tunnel provides a better chance of escape" +
			   " " +
			   "and heads down the tunnel with your family.\n"
				+ "------------------------------------------------------------\n";
	}
}
