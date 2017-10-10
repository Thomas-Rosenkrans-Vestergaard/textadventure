package textadventure.actions;

import textadventure.GameController;
import textadventure.Maze;
import textadventure.Player;
import textadventure.Room;

public interface Action
{

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	String getName();

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	String getDescription();

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param controller      The {@link GameController}.
	 * @param player          The {@link Player} performing the {@link Action}.
	 * @param currentLocation The {@link Room} the {@link Player} is currently located in.
	 * @throws ActionException When something goes wrong while performing the {@link Action}.
	 */
	void perform(GameController controller, Player player, Room currentLocation) throws ActionException;
}
