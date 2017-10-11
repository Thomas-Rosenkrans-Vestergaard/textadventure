package textadventure.scenario;

import textadventure.actions.Action;
import textadventure.rooms.Room;

import java.util.List;

public class LockedDoorScenario implements Scenario
{

	/**
	 * Returns the {@link Room} the {@link Scenario} plays out in.
	 *
	 * @return The {@link Room} the {@link Scenario} plays out in.
	 */
	@Override public Room getRoom()
	{
		return null;
	}

	/**
	 * Returns the description of the {@link Scenario}.
	 *
	 * @return The description of the {@link Scenario}.
	 */
	@Override public String getDescription()
	{
		return null;
	}

	/**
	 * Returns a {@link List} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link List} of the actions that can be performed in the {@link Scenario}.
	 */
	@Override public List<Action> getActions()
	{
		return null;
	}
}
