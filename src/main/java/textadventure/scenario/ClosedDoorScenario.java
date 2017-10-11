package textadventure.scenario;

import textadventure.actions.Action;
import textadventure.actions.OpenDoorAction;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;

import java.util.ArrayList;
import java.util.List;

public class ClosedDoorScenario implements Scenario
{

	/**
	 * The closed {@link Door}.
	 */
	private Door door;

	/**
	 * The {@link Room} where the {@link Door} resides.
	 */
	private Room room;

	/**
	 * The {@link Action}s that can be performed in the {@link ClosedDoorScenario}.
	 */
	private List<Action> actions;

	/**
	 * Creates a new {@link ClosedDoorScenario}.
	 *
	 * @param door The closed {@link Door}.
	 * @param room The {@link Room} where the {@link Door} resides.
	 */
	public ClosedDoorScenario(Door door, Room room)
	{
		this.door = door;
		this.room = room;
		this.actions = new ArrayList<>();
		this.actions.add(new OpenDoorAction());
	}

	/**
	 * Returns the {@link Room} the {@link Scenario} plays out in.
	 *
	 * @return The {@link Room} the {@link Scenario} plays out in.
	 */
	@Override public Room getRoom()
	{
		return this.room;
	}

	/**
	 * Returns the closed {@link Door}.
	 *
	 * @return The closed {@link Door}.
	 */
	public Door getDoor()
	{
		return this.door;
	}

	/**
	 * Returns the description of the {@link Scenario}.
	 *
	 * @return The description of the {@link Scenario}.
	 */
	@Override public String getDescription()
	{
		return String.format("The door in the %sern section of the room is closed.", door.getDirection(room)
																						 .name()
																						 .toLowerCase());
	}

	/**
	 * Returns a {@link List} of the actions that can be performed in the {@link Scenario}.
	 *
	 * @return The {@link List} of the actions that can be performed in the {@link Scenario}.
	 */
	@Override public List<Action> getActions()
	{
		return actions;
	}
}
