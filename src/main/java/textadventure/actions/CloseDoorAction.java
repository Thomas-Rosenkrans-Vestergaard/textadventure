package textadventure.actions;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.Scenario;

public class CloseDoorAction implements Action
{

	/**
	 * The {@link Door} to open.
	 */
	private Door door;

	/**
	 * Creates a new {@link CloseDoorAction}.
	 *
	 * @param door The {@link Door} to open.
	 */
	public CloseDoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the name of the {@link CloseDoorAction}.
	 *
	 * @return The name of the {@link CloseDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "close_door";
	}

	/**
	 * Returns the description of the {@link CloseDoorAction}.
	 *
	 * @return The description of the {@link CloseDoorAction}.
	 */
	@Override public String getDescription()
	{
		return null;
	}

	/**
	 * Performs the {@link CloseDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link CloseDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link CloseDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{

	}
}
