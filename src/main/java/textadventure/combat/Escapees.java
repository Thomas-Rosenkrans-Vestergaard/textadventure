package textadventure.combat;

import textadventure.Game;
import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@link Escapees} must attempt to escape the {@link Game}. They must escape the {@link Game} without being
 * killed by the {@link SecretPolice}.
 */
public class Escapees extends AbstractFaction
{

	/**
	 * The {@link Room} from where {@link Escapees} can escape the {@link Game}.
	 */
	private Room endingRoom;

	/**
	 * The {@link Set} of {@link Character} that have escaped.
	 */
	private Set<Character> escapedCharacters;

	/**
	 * Creates a new {@link Escapees} {@link Faction}.
	 *
	 * @param player       The leader of the {@link Escapees}.
	 * @param startingRoom The {@link Room} where the {@link Escapees} start.
	 * @param endingRoom   The {@link Room} from where {@link Escapees} can escape the {@link Game}.
	 */
	public Escapees(Player player, Room startingRoom, Room endingRoom)
	{
		super(player, startingRoom);

		this.endingRoom = endingRoom;
		this.escapedCharacters = new HashSet<>();
	}

	/**
	 * Returns the name of the {@link Faction}.
	 *
	 * @return The name of the {@link Faction}.
	 */
	@Override public String getName()
	{
		return "Escapees";
	}

	/**
	 * Returns the {@link Room} from where {@link Escapees} can escape the {@link Game}.
	 *
	 * @return The {@link Room} from where {@link Escapees} can escape the {@link Game}.
	 */
	public Room getEndingRoom()
	{
		return this.endingRoom;
	}

	/**
	 * Called when a {@link Character} escapes.
	 *
	 * @param character The {@link Character} who have escaped the {@link Game}.
	 */
	public void addEscapee(Character character)
	{
		this.escapedCharacters.add(character);
	}

	/**
	 * Checks if the {@link Faction} has won the {@link Game}.
	 *
	 * @param state The {@link Game} instance.
	 * @return The method returns <code>true</code> if the {@link Faction} has won. Returns <code>false</code> if the
	 * {@link Faction} has not won yet.
	 */
	@Override public boolean hasWon(Game state)
	{
		return characters.size() == escapedCharacters.size();
	}

	/**
	 * Checks if the {@link Faction} has lost the {@link Game}.
	 *
	 * @param state The {@link Game} instance.
	 * @return The method returns <code>true</code> if the {@link Faction} has lost. Returns <code>false</code> if the
	 * {@link Faction} has not lost yet.
	 */
	@Override public boolean hasLost(Game state)
	{
		int dead = 0;
		for (Character character : characters)
			if (character.getStatus() == Character.Status.DEAD)
				dead++;

		return dead == characters.size();
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getStartingMessage()
	{

		return "You're being hunted by the State Security Department of North Korea after having escaped from the Hoeryong concentration camp. With you is your wife and two kids. If you get caught, you will surely be executed. The only acceptable outcome is escape. Miraculously you've made it to the Demilitarized Zone without being caught. But crossing the Demilitarized Zone is a much greater challenge. Luckily you spot the entrance to a tunnel apparently leading under the Demilitarized Zone. You decide that the tunnel provides a better chance of escape and heads down the tunnel with your family.";
	}

	/**
	 * Returns the message given to the {@link Player} controlling the {@link Faction}.
	 *
	 * @return The message given to the {@link Player} controlling the {@link Faction}.
	 */
	@Override public String getEndingMessageOnWin()
	{
		return "After a challenging journey through a North Korean hell, you finally see the end of the tunnel, and the light of day shines upon you.";
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
