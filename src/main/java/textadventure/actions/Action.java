package textadventure.actions;

import textadventure.GameController;
import textadventure.Player;
import textadventure.scenario.Scenario;

public interface Action
{

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	String getName();

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	String getDescription();

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param scenario The {@link Scenario} that the {@link Action} is a response to.
	 * @param player   The {@link Player} performing the {@link Action}.
	 */
	void perform(Scenario scenario, Player player) throws ActionException;
}
