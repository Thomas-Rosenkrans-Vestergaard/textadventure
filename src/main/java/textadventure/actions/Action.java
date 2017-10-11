package textadventure.actions;

import textadventure.Game;
import textadventure.Player;
import textadventure.scenario.Scenario;

public interface Action
{

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	String getIdentifier();

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	String getDescription();

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link Action} responds to.
	 * @param player   The {@link Player} performing the {@link Action}.
	 */
	void perform(Game game, Scenario scenario, Player player) throws ActionException;
}
