package textadventure.openable.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;

public class DoorInspectAction implements Action
{

	/**
	 * The {@link Door} to inspect.
	 */
	private Door door;

	/**
	 * Creates a new {@link DoorInspectAction}.
	 *
	 * @param door The {@link Door} to inspect.
	 */
	public DoorInspectAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the door to gather information.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		game.getUserInterface().onDoorInspect(game, door, player);
	}
}
