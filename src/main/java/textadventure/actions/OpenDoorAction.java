package textadventure.actions;

import textadventure.Player;
import textadventure.rooms.features.doors.Door;
import textadventure.rooms.features.doors.Lock;
import textadventure.scenario.ClosedDoorScenario;
import textadventure.scenario.LockedDoorScenario;
import textadventure.scenario.Scenario;

public class OpenDoorAction implements Action
{
	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getName()
	{
		return "open_door";
	}

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getDescription()
	{
		return "Attempt to open the door.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param scenario The {@link Scenario} that the {@link Action} is a response to.
	 * @param player   The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Scenario scenario, Player player) throws ActionException
	{
		if (scenario instanceof ClosedDoorScenario) {
			ClosedDoorScenario closedDoorScenario = (ClosedDoorScenario) scenario;
			Door               door               = closedDoorScenario.getDoor();
			Lock.State         state              = door.getLock().getState();

			if (state == Lock.State.LOCKED) {
				throw new NewScenarioException(scenario, this, player, new LockedDoorScenario());
			}

			if (state == Lock.State.UNLOCKED) {
				door.
			}
		}

		throw new UnknownActionException(scenario, this, player);
	}
}
