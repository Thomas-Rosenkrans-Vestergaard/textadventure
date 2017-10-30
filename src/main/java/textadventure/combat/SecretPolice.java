package textadventure.combat;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.Room;

public class SecretPolice extends Faction
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
