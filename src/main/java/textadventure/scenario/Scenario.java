package textadventure.scenario;

import com.sun.javafx.collections.UnmodifiableListSet;
import textadventure.Room;
import textadventure.actions.Action;

import java.util.List;
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
	 * Checks if the provided {@link Action} can be performed in the {@link Scenario}.
	 *
	 * @param action The {@link Action}.
	 * @return <code>True</code> if the {@link Action} can be performed on the {@link Scenario}, otherwise
	 * <code>false</code>.
	 */
	boolean canPerform(Action action);

	/**
	 * Returns a {@link List} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link List} of the actions that can be performed in the {@link Scenario}.
	 */
	List<Action> getActions();
}
