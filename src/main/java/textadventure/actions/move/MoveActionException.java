package textadventure.actions.move;

import textadventure.Direction;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.rooms.Room;
import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

public class MoveActionException extends ActionException
{

	/**
	 * The starting {@link Room}.
	 */
	private Room from;

	/**
	 * The {@link Direction} the direction that was attempted to move in.
	 */
	private Direction direction;

	/**
	 * Creates a new {@link MoveActionException}.
	 *
	 * @param scenario  The {@link Scenario} the {@link Action} is a response to.
	 * @param action    The {@link Action} that was attempted.
	 * @param player    The {@link Player} who attempted the {@link Action}.
	 * @param from      The starting {@link Room}.
	 * @param direction The {@link Direction} the direction that was attempted to move in.
	 */
	MoveActionException(Scenario scenario, Action action, Player player, Room from, Direction direction)
	{
		super(scenario, action, player);
		this.from = from;
		this.direction = direction;
	}

	/**
	 * Returns the starting {@link Room}.
	 *
	 * @return The starting {@link Room}.
	 */
	public Room getRoom()
	{
		return this.from;
	}

	/**
	 * Returns the {@link Direction} the direction that was attempted to move in.
	 *
	 * @return The {@link Direction} the direction that was attempted to move in.
	 */
	public Direction getDirection()
	{
		return this.direction;
	}
}
