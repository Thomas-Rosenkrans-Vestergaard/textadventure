package textadventure.actions.movement;

import textadventure.Direction;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.rooms.Room;
import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

/**
 * Exception thrown when an error occurs while moving to a {@link textadventure.rooms.features.doors.Door}.
 */
public class MoveToDoorException extends ActionException
{

	/**
	 * The {@link Direction} the direction of the {@link textadventure.rooms.features.doors.Door} in the {@link Room}.
	 */
	private Direction direction;

	/**
	 * Creates a new {@link MoveToDoorException}.
	 *
	 * @param scenario  The {@link Scenario} the {@link Action} is a response to.
	 * @param action    The {@link Action} that was attempted.
	 * @param player    The {@link Player} who attempted the {@link MoveToDoorAction}.
	 * @param room      The {@link Room} that the {@link MoveToDoorAction} was performed in.
	 * @param direction The {@link Direction} the direction that was attempted to move in.
	 */
	MoveToDoorException(Scenario scenario, Action action, Player player, Room room, Direction direction)
	{
		super(scenario, action, player, room);
		this.direction = direction;
	}

	/**
	 * Returns the {@link Direction} of the {@link textadventure.rooms.features.doors.Door} that was attempted to move
	 * to.
	 *
	 * @return The {@link Direction} of the {@link textadventure.rooms.features.doors.Door} that was attempted to
	 * move to.
	 */
	public Direction getDirection()
	{
		return this.direction;
	}
}
