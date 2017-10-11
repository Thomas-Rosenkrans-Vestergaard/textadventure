package textadventure.scenario;

import textadventure.actions.ActionException;
import textadventure.rooms.Room;
import textadventure.actions.Action;

import java.util.stream.Stream;

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
	 * Returns a {@link Stream} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link Stream} of the actions that can be performed in the {@link Scenario}.
	 */
	Stream<Action> getActions();
}
