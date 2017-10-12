package textadventure.scenario;

import textadventure.rooms.Room;
import textadventure.actions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractScenario implements Scenario
{

	/**
	 * The {@link Room} the {@link Scenario} plays out in.
	 */
	protected Room room;

	/**
	 * The description of the {@link Scenario}.
	 */
	protected String description;

	/**
	 * The {@link List} of {@link Action}s that can be performed on the {@link Scenario}.
	 */
	private List<Action> actions = new ArrayList<>();

	/**
	 * Creates a new instance of {@link AbstractScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} plays out in.
	 */
	public AbstractScenario(Room room)
	{
		this.room = room;
		this.description = "INIT_ABSTRACT_DESCRIPTION";
	}

	/**
	 * Creates a new instance of {@link AbstractScenario}.
	 *
	 * @param room        The {@link Room} the {@link Scenario} plays out in.
	 * @param description The description of the {@link Scenario}.
	 */
	public AbstractScenario(Room room, String description)
	{
		this.room = room;
		this.description = description;
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
	 * Returns a {@link Stream} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link Stream} of the actions that can be performed in the {@link Scenario}.
	 */
	@Override public Stream<Action> getActions()
	{
		return actions.stream();
	}
}
