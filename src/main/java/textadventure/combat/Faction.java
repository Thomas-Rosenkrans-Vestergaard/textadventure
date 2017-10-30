package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Character;
import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.Room;
import textadventure.rooms.RoomController;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link Faction}s the {@link textadventure.Character}s can belong to.
 */
public abstract class Faction
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
	private List<Character> characters = new ArrayList<>();

	/**
	 * Creates a new {@link Faction}.
	 *
	 * @param leader       The {@link Player} leading the {@link Faction}.
	 * @param startingRoom The {@link Room} where the {@link Faction} starts.
	 */
	public Faction(Player leader, Room startingRoom)
	{
		this.leader = leader;
		this.startingRoom = startingRoom;
	}

	/**
	 * Checks if the {@link Faction} win condition has been fulfilled.
	 *
	 * @param game The {@link Game} instance.
	 * @return True when the win condition of the {@link Faction} has been fulfilled.
	 */
	abstract boolean hasWon(Game game);

	/**
	 * Returns the {@link Room} where the {@link Faction} starts.
	 *
	 * @return The {@link Room} where the {@link Faction} starts.
	 */
	public Room getStartingRoom()
	{
		return this.startingRoom;
	}

	/**
	 * Returns the message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 */
	public abstract String getStartingMessage();

	/**
	 * Returns the message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 */
	public abstract String getEndingMessageOnWin();

	/**
	 * Returns the message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link textadventure.Player} controlling the {@link Faction}.
	 */
	public abstract String getEndingMessageOnLoss();

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
	 * @param roomController The {@link RoomController} from where to find the {@link Room} where the {@link Faction}
	 *                       starts.
	 * @return The {@link Room} the {@link Character} of the {@link Faction} starts in.
	 */
	public Room getStartingRoom(RoomController roomController)
	{
		return startingRoom;
	}

	/**
	 * Returns the {@link Player} leading the {@link Faction}.
	 *s
	 * @return The {@link Player} leading the {@link Faction}.
	 */
	public Player getLeader()
	{
		return this.leader;
	}
}
