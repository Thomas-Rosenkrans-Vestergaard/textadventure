package textadventure.actions;

import textadventure.GameException;
import textadventure.Player;
import textadventure.scenario.Scenario;

public class ActionException extends GameException
{

	/**
	 * The {@link Scenario} the {@link Action} is a response to.
	 */
	private Scenario scenario;

	/**
	 * The {@link Action} that was attempted.
	 */
	private Action action;

	/**
	 * The {@link Player} who attempted the {@link Action}.
	 */
	private Player player;

	/**
	 * Creates a new {@link ActionException}.
	 *
	 * @param scenario The {@link Scenario} the {@link Action} is a response to.
	 * @param action   The {@link Action} that was attempted.
	 * @param player   The {@link Player} who attempted the {@link Action}.
	 */
	public ActionException(Scenario scenario, Action action, Player player)
	{
		this.scenario = scenario;
		this.action = action;
		this.player = player;
	}

	/**
	 * Returns the {@link Scenario} the {@link Action} is a response to.
	 *
	 * @return The {@link Scenario} the {@link Action} is a response to.
	 */
	public Scenario getScenario()
	{
		return this.scenario;
	}

	/**
	 * Returns the {@link Action} that was attempted.
	 *
	 * @return The {@link Action} that was attempted.
	 */
	public Action getAction()
	{
		return this.action;
	}

	/**
	 * Returns the {@link Player} who attempted the {@link Action}.
	 *
	 * @return The {@link Player} who attempted the {@link Action}.
	 */
	public Player getPlayer()
	{
		return this.player;
	}
}
