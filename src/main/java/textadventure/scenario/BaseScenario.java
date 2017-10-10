package textadventure.scenario;


import com.sun.javafx.collections.UnmodifiableListSet;
import textadventure.Room;
import textadventure.actions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseScenario implements Scenario
{

	/**
	 * The {@link Room} the {@link Scenario} plays out in.
	 */
	private Room room;

	/**
	 * The {@link List} of {@link Action}s that can be performed on the {@link Scenario}.
	 */
	private List<Action> actions = new ArrayList<>();

	/**
	 * Creates a new instance of {@link BaseScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} plays out in.
	 */
	public BaseScenario(Room room)
	{
		this.room = room;

		actions.add(new MoveNorthAction());
		actions.add(new MoveSouthAction());
		actions.add(new MoveEastAction());
		actions.add(new MoveWestAction());
	}

	public boolean canPerform(Action action)
	{
		for (Action it : actions) {
			if (it.equals(action)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Adds the provided {@link Action} to the {@link Scenario}.
	 *
	 * @param action The {@link Action} to add to the {@link Scenario}.
	 */
	protected void addAction(Action action)
	{
		actions.add(action);
	}

	@Override public Room getRoom()
	{
		return room;
	}

	@Override public List<Action> getActions()
	{
		return Collections.unmodifiableList(actions);
	}
}
