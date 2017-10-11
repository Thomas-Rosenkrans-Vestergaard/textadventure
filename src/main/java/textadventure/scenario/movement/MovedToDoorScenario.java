package textadventure.scenario.movement;

import textadventure.actions.MoveAwayFromDoorAction;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.AbstractScenario;
import textadventure.scenario.Scenario;

/**
 * An <code>abstract</code> base for {@link textadventure.actions.Action}s involving moving to {@link Door}s. The
 * {@link MovedToDoorScenario} add {@link textadventure.actions.Action}s shared by it's descendants.
 */
public abstract class MovedToDoorScenario extends AbstractScenario
{

	/**
	 * The {@link Door} that the {@link textadventure.Player} moved to.
	 */
	protected Door door;

	/**
	 * Creates a new instance of {@link MovedToDoorScenario}.
	 *
	 * @param room The {@link Room} the {@link Scenario} plays out in.
	 */
	public MovedToDoorScenario(Room room, Door door)
	{
		super(room);
		this.door = door;
		this.addAction(new MoveAwayFromDoorAction());
	}

	/**
	 * Returns the {@link Door} that the {@link textadventure.Player} moved to.
	 *
	 * @return The {@link Door} that the {@link textadventure.Player} moved to.
	 */
	public Door getDoor()
	{
		return this.door;
	}
}
