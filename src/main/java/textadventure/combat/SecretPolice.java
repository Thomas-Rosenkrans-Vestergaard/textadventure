package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.characters.Character;
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
	 * Returns the name of the {@link Faction}.
	 *
	 * @return The name of the {@link Faction}.
	 */
	@Override public String getName()
	{
		return "Secret police";
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
		try {
			Escapees escapees = state.getFaction(Escapees.class);
			return escapees.getCharacters().size() == escapees.getCharacters(Character.Status.DEAD).size();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
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
		return state.getNumberOfActiveFactions() == 1;
	}

	/**
	 * Returns the {@link Character}s that can still be played.
	 *
	 * @return The {@link Character}s that can still be played.
	 */
	@Override public ImmutableList<Character> getActiveCharacters()
	{
		return getCharacters(Character.Status.ALIVE);
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
