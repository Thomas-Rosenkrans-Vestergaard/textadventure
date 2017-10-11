package textadventure.actions.movement;

import textadventure.Direction;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.Scenario;

/**
 * Thrown when a {@link textadventure.Player} attempts to perform {@link MoveToDoorAction} to a {@link Door} that
 * doesn't exist.
 */
public class NoDoorException extends MoveToDoorException
{

	/**
	 * Creates a new {@link MoveToDoorException}.
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
