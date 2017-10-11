package textadventure.actions;

import textadventure.Player;
import textadventure.rooms.Room;
import textadventure.scenario.Scenario;

/**
 * Thrown when some {@link Action} causes some {@link textadventure.scenario.Scenario} to happen.
 */
public class NewScenarioException extends ActionException
{

	/**
	 * The new {@link Scenario}.
	 */
	private Scenario newScenario;

	/**
	 * Creates a new {@link NewScenarioException}.
	 *
	 * @param scenario    The {@link Scenario} the {@link Action} is a response to.
	 * @param action      The {@link Action} that was attempted.
	 * @param player      The {@link Player} who attempted the {@link Action}.
	 * @param newScenario The new {@link Scenario}.
	 */
	public NewScenarioException(Scenario scenario, Action action, Player player, Room room, Scenario newScenario)
	{
		super(newScenario, action, player, room);
		this.newScenario = newScenario;
	}

	/**
	 * Returns the new {@link Scenario}.
	 *
	 * @return The new {@link Scenario}.
	 */
	public Scenario getNewScenario()
	{
		return this.newScenario;
	}
}
