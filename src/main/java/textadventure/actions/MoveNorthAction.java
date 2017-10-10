package textadventure.actions;

import textadventure.*;

public class MoveNorthAction extends MoveAction
{

	@Override public String getName()
	{
		return "north";
	}

	@Override public String getDescription()
	{
		return "Moves to the room to the north.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param controller      The {@link GameController}.
	 * @param player          The {@link Player} performing the {@link Action}.
	 * @param currentLocation The {@link Room} the {@link Player} is currently located in.
	 * @throws ActionException When something goes wrong while performing the {@link Action}.
	 */
	@Override public void perform(GameController controller, Player player, Room currentLocation) throws ActionException
	{
		move(controller, player, currentLocation, Direction.NORTH);
	}
}
