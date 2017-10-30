package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Character;
import textadventure.Game;
import textadventure.rooms.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link Faction}s the {@link textadventure.Character}s can belong to.
 */
public abstract class Faction
{

	/**
	 * The {@link Character}s in the {@link Faction}.
	 */
	private List<Character> characters = new ArrayList<>();

	/**
	 * Checks if the {@link Faction} win condition has been fulfilled.
	 *
	 * @param game The {@link Game} instance.
	 * @return True when the win condition of the {@link Faction} has been fulfilled.
	 */
	abstract boolean hasWon(Game game);

	/**
	 * Adds a new {@link Character} to the {@link Faction}.
	 *
	 * @param character The {@link Character} to add to the {@link Faction}.
	 */
	public void addCharacter(Character character)
	{
		this.characters.add(character);
	}

	/**
	 * Returns the {@link Character}s in the {@link Faction}.
	 *
	 * @return The {@link Character}s in the {@link Faction}.
	 */
	public ImmutableList<Character> getCharacters()
	{
		return ImmutableList.copyOf(characters);
	}

	/**
	 * Returns the {@link Room} the {@link Character} of the {@link Faction} starts in.
	 *
	 * @return The {@link Room} the {@link Character} of the {@link Faction} starts in.
	 */
	public Room getStartingRoom()
	{
		return null;
	}
}
