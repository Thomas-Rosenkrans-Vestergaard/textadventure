package textadventure.actions;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.EnteredRoomScenario;
import textadventure.scenario.Scenario;

/**
 * {@link Action} that lets the {@link Player} move through an {@link textadventure.rooms.features.doors.Door}. The
 * {@link textadventure.rooms.features.doors.Door} must have {@link textadventure.rooms.features.doors.Door.State}
 * OPEN before {@link MoveThroughDoorAction} can be performed.
 */
public class MoveThroughDoorAction implements Action
{

	/**
	 * The {@link Door} to move to.
	 */
	private Door door;

	/**
	 * Creates a new {@link MoveThroughDoorAction}.
	 *
	 * @param door The {@link Door} to move through.
	 */
	public MoveThroughDoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the name of the {@link MoveThroughDoorAction}.
	 *
	 * @return The name of the {@link MoveThroughDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "move_through_door";
	}

	/**
	 * Returns the description of the {@link MoveThroughDoorAction}.
	 *
	 * @return The description of the {@link MoveThroughDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Move through the door you're standing at.";
	}

	/**
	 * Performs the {@link MoveThroughDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link MoveThroughDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link MoveThroughDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		Room currentRoom = player.getCurrentLocation();
		Room targetRoom  = door.getOtherSide(currentRoom);

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		player.setCurrentLocation(targetRoom);
		throw new NewScenarioException(scenario, this, player, currentRoom, new EnteredRoomScenario(targetRoom));
	}
}
