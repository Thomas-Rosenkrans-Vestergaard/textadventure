package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link Faction}s the {@link Character}s can belong to.
 */
public abstract class AbstractFaction implements Faction
{

	/**
	 * The leader of the {@link Faction}.
	 */
	private Player leader;

	/**
	 * The {@link Room} where the {@link Faction} starts.
	 */
	private Room startingRoom;

	/**
	 * The {@link Character}s in the {@link Faction}.
	 */
	protected List<Character> characters = new ArrayList<>();

	/**
	 * Creates a new {@link Faction}.
	 *
	 * @param leader       The {@link Player} leading the {@link Faction}.
	 * @param startingRoom The {@link Room} where the {@link Faction} starts.
	 */
	public AbstractFaction(Player leader, Room startingRoom)
	{
		this.leader = leader;
		this.startingRoom = startingRoom;
	}

	/**
	 * Returns the {@link Player} leading the {@link Faction}.
	 * s
	 *
	 * @return The {@link Player} leading the {@link Faction}.
	 */
	@Override public Player getLeader()
	{
		return leader;
	}

	/**
	 * Returns the {@link Room} where the {@link Faction} starts.
	 *
	 * @return The {@link Room} where the {@link Faction} starts.
	 */
	@Override public Room getStartingRoom()
	{
		return startingRoom;
	}

	/**
	 * Adds a new {@link Character} to the {@link Faction}.
	 *
	 * @param character The {@link Character} to add to the {@link Faction}.
	 */
	@Override public void addCharacter(Character character)
	{
		this.characters.add(character);
	}

	/**
	 * Returns the {@link Character}s in the {@link Faction}.
	 *
	 * @return The {@link Character}s in the {@link Faction}.
	 */
	@Override public ImmutableList<Character> getCharacters()
	{
		return ImmutableList.copyOf(characters);
	}
}
