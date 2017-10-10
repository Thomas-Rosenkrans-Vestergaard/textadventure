package textadventure.scenario;

import textadventure.rooms.Room;
import textadventure.actions.Action;

import java.util.List;

public interface Scenario
{

	/**
	 * Returns the {@link Room} the {@link Scenario} plays out in.
	 *
	 * @return The {@link Room} the {@link Scenario} plays out in.
	 */
	Room getRoom();

	/**
	 * Returns the description of the {@link Scenario}.
	 *
	 * @return The description of the {@link Scenario}.
	 */
	String getDescription();

	/**
	 * Returns a {@link List} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link List} of the actions that can be performed in the {@link Scenario}.
	 */
	List<Action> getActions();
}
