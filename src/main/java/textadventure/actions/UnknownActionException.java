package textadventure.actions;

import textadventure.scenario.Scenario;

/**
 * Thrown when some {@link textadventure.actions.Action} could not be performed on some
 * {@link textadventure.scenario.Scenario}.
 */
public class UnknownActionException extends ActionException
{

	/**
	 * The {@link Action} that was attempted to be performed.
	 */
	private Action action;

	/**
	 * The {@link Scenario} that the {@link Action} was attempted to be performed on.
	 */
	private Scenario scenario;

	/**
	 * Creates a new {@link UnknownActionException}.
	 *
	 * @param action   The {@link Action} that was attempted to be performed.
	 * @param scenario The {@link Scenario} that the {@link Action} was attempted to be performed on.
	 */
	public UnknownActionException(Action action, Scenario scenario)
	{
		this.action = action;
		this.scenario = scenario;
	}

	/**
	 * Returns The {@link Action} that was attempted to be performed.
	 *
	 * @return The {@link Action} that was attempted to be performed.
	 */
	public Action getAction()
	{
		return this.action;
	}

	/**
	 * Returns The {@link Scenario} that the {@link Action} was attempted to be performed on.
	 *
	 * @return The {@link Scenario} that the {@link Action} was attempted to be performed on.
	 */
	public Scenario getScenario()
	{
		return this.scenario;
	}
}
