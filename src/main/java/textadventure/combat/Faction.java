package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

public interface Faction
{

	/**
	 * Returns the name of the {@link Faction}.
	 *
	 * @return The name of the {@link Faction}.
	 */
	String getName();

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

	/**
	 * Returns an {@link ImmutableList} of the {@link Character}s with the provided
	 * {@link textadventure.characters.Character.Status}.
	 *
	 * @param status The {@link textadventure.characters.Character.Status} of the {@link Character}s to return.
	 * @return The {@link Character}s with the provided {@link textadventure.characters.Character.Status}.
	 */
	ImmutableList<Character> getCharacters(Character.Status status);

	/**
	 * Returns the {@link Character}s that can still be played.
	 *
	 * @return The {@link Character}s that can still be played.
	 */
	ImmutableList<Character> getActiveCharacters();

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
