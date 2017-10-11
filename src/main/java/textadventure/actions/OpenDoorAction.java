package textadventure.actions;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.doors.Lock;
import textadventure.scenario.movement.MovedToClosedDoorScenario;
import textadventure.scenario.movement.MovedToLockedDoorScenario;
import textadventure.scenario.OpenedDoorScenario;
import textadventure.scenario.Scenario;

/**
 * {@link Action} that opens a closed {@link Door}. The {@link Door} can only be opened when the {@link Door} has
 * {@link textadventure.rooms.features.doors.Door.State} CLOSED and the {@link Lock} has state
 * {@link textadventure.rooms.features.doors.Lock.State} UNLOCKED.
 */
public class OpenDoorAction implements Action
{

	/**
	 * The {@link Door} to open.
	 */
	private Door door;

	/**
	 * Creates a new {@link OpenDoorAction}.
	 *
	 * @param door The {@link Door} to open.
	 */
	public OpenDoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the name of the {@link OpenDoorAction}.
	 *
	 * @return The name of the {@link OpenDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "open_door";
	}

	/**
	 * Returns the description of the {@link OpenDoorAction}.
	 *
	 * @return The description of the {@link OpenDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Attempt to open the door.";
	}

	/**
	 * Performs the {@link OpenDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link OpenDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link OpenDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		if (scenario instanceof MovedToClosedDoorScenario) {
			MovedToClosedDoorScenario castScenario = (MovedToClosedDoorScenario) scenario;
			Door                      door         = castScenario.getDoor();
			Lock.State                state        = door.getLock().getState();

			if (state == Lock.State.LOCKED) {
				throw new NewScenarioException(scenario, this, player, player.getCurrentLocation(),
											   new MovedToLockedDoorScenario(player.getCurrentLocation(), door)
				);
			}

			if (state == Lock.State.UNLOCKED) {
				door.setState(Door.State.OPEN);
				throw new NewScenarioException(scenario, this, player, player.getCurrentLocation(),
											   new OpenedDoorScenario(player.getCurrentLocation(), door)
				);
			}
		}

		throw new UnknownActionException(scenario, this, player);
	}
}
