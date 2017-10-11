package textadventure.actions;

import textadventure.Player;
import textadventure.scenario.LockedDoorScenario;
import textadventure.scenario.Scenario;

/**
 * Inspects the {@link textadventure.rooms.features.doors.Lock} on a {@link textadventure.rooms.features.doors.Door}
 * revealing its <code>code</code>.
 */
public class InspectLockAction implements Action
{
	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getName()
	{
		return "inspect_lock";
	}

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getDescription()
	{
		return "Inspects the lock of the door.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param scenario The {@link Scenario} that the {@link Action} is a response to.
	 * @param player   The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Scenario scenario, Player player) throws ActionException
	{
		if (scenario instanceof LockedDoorScenario) {

		}

		throw new UnknownActionException(scenario, this, player);
	}
}
