package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

public interface Faction
{

	/**
	 * Checks if the {@link Faction} has won the {@link Game}.
	 *
	 * @param state The {@link Game} instance.
	 * @return The method returns <code>true</code> if the {@link Faction} has won. Returns <code>false</code> if the
	 * {@link Faction} has not won yet.
	 */
	boolean hasWon(Game state);

	/**
	 * Checks if the {@link Faction} has lost the {@link Game}.
	 *
	 * @param state The {@link Game} instance.
	 * @return The method returns <code>true</code> if the {@link Faction} has lost. Returns <code>false</code> if the
	 * {@link Faction} has not lost yet.
	 */
	boolean hasLost(Game state);

	ImmutableList<Character> getCharacters(Character.Status status);

	/**
	 * Returns the {@link Player} leading the {@link Faction}.
	 * s
	 *
	 * @return The {@link Player} leading the {@link Faction}.
	 */
	Player getLeader();

	/**
	 * Returns the {@link Room} where the {@link Faction} starts.
	 *
	 * @return The {@link Room} where the {@link Faction} starts.
	 */
	Room getStartingRoom();

	/**
	 * Returns the message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 */
	String getStartingMessage();

	/**
	 * Returns the message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 */
	String getEndingMessageOnWin();

	/**
	 * Returns the message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 */
	String getEndingMessageOnLoss();

	/**
	 * Adds a new {@link Character} to the {@link Faction}.
	 *
	 * @param character The {@link Character} to add to the {@link Faction}.
	 */
	void addCharacter(Character character);

	/**
	 * Returns the {@link Character}s in the {@link Faction}.
	 *
	 * @return The {@link Character}s in the {@link Faction}.
	 */
	ImmutableList<Character> getCharacters();
}
