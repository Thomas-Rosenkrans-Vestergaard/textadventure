package textadventure;

import textadventure.exception.UnknownPlayerException;

import java.util.HashMap;
import java.util.Map;

public class PlayerLocationTracker
{

	/**
	 * The {@link Player} in the {@link PlayerLocationTracker} along with their current positions.
	 */
	private Map<Player, Room> locations = new HashMap<>();

	/**
	 * Returns the current {@link Room} of the provided {@link Player}.
	 *
	 * @param player The {@link Player} to get the corresponding {@link Room} of.
	 * @return The current {@link Room} location of the provided {@link Player}.
	 * @throws UnknownPlayerException When the provided {@link Player} is not known to the {@link PlayerLocationTracker}.
	 */
	public Room getLocation(Player player) throws UnknownPlayerException
	{
		for (Map.Entry<Player, Room> entry : locations.entrySet()) {
			if (entry.getKey() == player) {
				return entry.getValue();
			}
		}

		throw new UnknownPlayerException(player);
	}

	/**
	 * Sets the location of the provided {@link Player} to be the provided {@link Room}.
	 *
	 * @param player The {@link Player} to set the location of.
	 * @param room   The {@link Room}.
	 */
	public void setLocation(Player player, Room room)
	{
		locations.put(player, room);
	}

	/**
	 * Checks if a {@link Player} in the {@link PlayerLocationTracker} has reached the {@link EndingRoom}.
	 *
	 * @return <code>True</code> if a {@link Player} in the {@link PlayerLocationTracker} has reached the {@link EndingRoom}, otherwise <code>false</code>.
	 */
	public boolean hasEnd()
	{
		for (Map.Entry<Player, Room> entry : locations.entrySet()) {
			if (entry.getValue() instanceof EndingRoom) {
				return true;
			}
		}

		return false;
	}
}
