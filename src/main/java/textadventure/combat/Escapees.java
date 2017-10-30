package textadventure.combat;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.Room;

public class Escapees extends Faction
{

	/**
	 * Creates a new {@link Escapees} {@link Faction}.
	 *
	 * @param player       The leader of the {@link Escapees}.
	 * @param startingRoom The {@link Room} where the {@link Escapees} start.
	 */
	public Escapees(Player player, Room startingRoom)
	{
		super(player, startingRoom);
	}

	/**
	 * Checks if the {@link Faction} win condition has been fulfilled.
	 *
	 * @param game The {@link Game} instance.
	 * @return True when the win condition of the {@link Faction} has been fulfilled.
	 */
	@Override boolean hasWon(Game game)
	{
		return false;
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getStartingMessage()
	{

		return "You're being hunted by the State Security Department of North Korea after having escaped from the " +
				"Hoeryong concentration camp. \nWith you is your wife and two kids. If you get caught, you will " +
				"surely be" +
				" executed. The only acceptable outcome is escape.\n" +
				"Miraculously you've made it to the Demilitarized Zone without being caught. But crossing the " +
				"Demilitarized Zone is a much greater challenge. \nLuckily you spot the entrance to a tunnel " +
				"apparently" +
				" " +
				"leading under the Demilitarized Zone. \nYou decide that the tunnel provides a better chance of " +
				"escape" +
				" " +
				"and heads down the tunnel with your family.\n";
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getEndingMessageOnWin()
	{
		return "After a challenging journey through a North Korean hell, you finally " +
				"see the end of the tunnel, and the light of day shines upon you.";
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getEndingMessageOnLoss()
	{
		return "You lost";
	}
}
