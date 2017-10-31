package textadventure.combat;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.Room;

/**
 * The {@link SecretPolice} must prevent the {@link Escapees} from escaping the {@link Game}.
 */
public class SecretPolice extends AbstractFaction
{

	/**
	 * Creates a new {@link SecretPolice} {@link Faction}.
	 *
	 * @param player       The leader of the {@link SecretPolice}.
	 * @param startingRoom The {@link Room} where the {@link SecretPolice} start.
	 */
	public SecretPolice(Player player, Room startingRoom)
	{
		super(player, startingRoom);
	}

	/**
	 * Checks if the {@link Faction} has won the {@link Game}.
	 *
	 * @param game The {@link Game} instance.
	 * @return The method returns <code>true</code> if the {@link Faction} has won. Returns <code>false</code> if the
	 * {@link Faction} has not won yet.
	 */
	@Override public boolean hasWon(Game game)
	{
		return false;
	}

	/**
	 * Checks if the {@link Faction} has lost the {@link Game}.
	 *
	 * @param game The {@link Game} instance.
	 * @return The method returns <code>true</code> if the {@link Faction} has lost. Returns <code>false</code> if the
	 * {@link Faction} has not lost yet.
	 */
	@Override public boolean hasLost(Game game)
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
		return "Secret police starting message";
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getEndingMessageOnWin()
	{
		return "Secret police win";
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getEndingMessageOnLoss()
	{
		return "Secret police win";
	}
}
