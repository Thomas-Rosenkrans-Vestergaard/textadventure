package textadventure.scenario;

import textadventure.rooms.Room;
import textadventure.actions.*;
import textadventure.actions.move.MoveEastAction;
import textadventure.actions.move.MoveNorthAction;
import textadventure.actions.move.MoveSouthAction;
import textadventure.actions.move.MoveWestAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractScenario implements Scenario
{

	/**
	 * The {@link Room} the {@link Scenario} plays out in.
	 */
	private Room room;

	/**
	 * The description of the {@link Scenario}.
	 */
	private String description;

	/**
	 * The {@link List} of {@link Action}s that can be performed on the {@link Scenario}.
	 */
	private List<Action> actions = new ArrayList<>();

	/**
	 * Creates a new instance of {@link AbstractScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} plays out in.
	 */
	public AbstractScenario(Room room, String description)
	{
		this.room = room;
		this.description = description;

		actions.add(new MoveNorthAction());
		actions.add(new MoveSouthAction());
		actions.add(new MoveEastAction());
		actions.add(new MoveWestAction());
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

	/**
	 * Returns the {@link Room} the {@link Scenario} plays out in.
	 *
	 * @return The {@link Room} the {@link Scenario} plays out in.
	 */
	@Override public Room getRoom()
	{
		return room;
	}

	/**
	 * Returns the description of the {@link Scenario}.
	 *
	 * @return The description of the {@link Scenario}.
	 */
	@Override public String getDescription()
	{
		return description;
	}

	/**
	 * Returns a {@link List} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link List} of the actions that can be performed in the {@link Scenario}.
	 */
	@Override public List<Action> getActions()
	{
		return Collections.unmodifiableList(actions);
	}
}
