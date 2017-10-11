package textadventure.actions.move;

import textadventure.Direction;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.Scenario;

/**
 * Thrown when a {@link textadventure.Player} attempts to interact with a {@link Door} that doesn't exist.
 */
public class NoDoorException extends MoveActionException
{

	/**
	 * Creates a new {@link MoveActionException}.
	 *
	 * @param scenario  The {@link Scenario} the {@link Action} is a response to.
	 * @param action    The {@link Action} that was attempted.
	 * @param player    The {@link Player} who attempted the {@link Action}.
	 * @param from      The starting {@link Room}.
	 * @param direction The {@link Direction} the direction that was attempted to move in.
	 */
	public NoDoorException(Scenario scenario, Action action, Player player, Room from, Direction direction)
	{
		super(scenario, action, player, from, direction);
	}
}
